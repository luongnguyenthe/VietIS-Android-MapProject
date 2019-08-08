package com.example.sharelocation.data.remote.request;

import android.os.AsyncTask;
import android.util.Log;
import com.example.sharelocation.data.OnRequestDataListener;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class HttpGetRequest<T> extends AsyncTask<String, Void, String> {
    private final String REQUEST_METHOD = "GET";
    private final int READ_TIMEOUT = 15000;
    private final int CONNECTION_TIMEOUT = 15000;

    private OnRequestDataListener<T> mListener;
    private Exception mException;

    protected HttpGetRequest(OnRequestDataListener<T> listener) {
        mListener = listener;
    }

    @Override
    protected String doInBackground(String... strings) {
        String stringUrl = strings[0];
        Log.d("HttpGetRequest", stringUrl);
        String result;
        String inputLine;
        try {
            //Create a URL object holding our url
            URL myUrl = new URL(stringUrl);
            //Create a connection
            HttpURLConnection connection = (HttpURLConnection)
                    myUrl.openConnection();
            //Set methods and timeouts
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);

            //Connect to our url
            connection.connect();
            //Create a new InputStreamReader
            InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());
            //Create a new buffered reader and String Builder
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();
            //Check if the line we are reading is not null
            while ((inputLine = reader.readLine()) != null) {
                stringBuilder.append(inputLine);
            }
            //Close our InputStream and Buffered reader
            reader.close();
            streamReader.close();
            //Set our result equal to our stringBuilder
            result = stringBuilder.toString();
        } catch (final IOException e) {
            mException = e;
            result = null;
        }

        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (mListener == null) return;

        if (mException != null) {
            mListener.onRequestDataFailure(mException.getMessage());
        } else {
            try {
                T t = parseJSONToDataClass(s);
                mListener.onRequestDataSuccess(t);
            } catch (JSONException e) {
                mListener.onRequestDataFailure(e.getMessage());
            }
        }
    }

    abstract T parseJSONToDataClass(String json) throws JSONException;
}
