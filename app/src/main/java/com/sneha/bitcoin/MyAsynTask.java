package com.sneha.bitcoin;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Sneha on 30-03-2018.
 */

public class MyAsynTask extends AsyncTask<String , Void , ArrayList<String>> {

    MyStr repoDownloadListener ;

    public MyAsynTask(MyStr repoDownloadListener) {
        this.repoDownloadListener = repoDownloadListener;
    }

    @Override
    protected ArrayList<String> doInBackground(String... params) {
        String urlString = params[0];
        try {
            URL url = new URL(urlString);   //convert string to url
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();  //to form the connection
            urlConnection.setRequestMethod("GET");// we wont data from server that is why "get"
            Log.i("Course response", "Connection started: ");
            urlConnection.connect();         //cant be called on main thread so we have to use asyncTask() for multithreading
            Log.i("Courses Response:","Connection Complete");

            InputStream inputStream = urlConnection.getInputStream();      //api gives the input stream
            Scanner scanner = new Scanner(inputStream);
            String response = "" ;
            while (scanner.hasNext()){
                response += scanner.next();
            }
            Log.i("Courses Response:",response);
            ArrayList<String> detail = parseDetails(response);
            return detail ;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> parseDetails(String response) throws JSONException{
        ArrayList<String> info = new ArrayList<>() ;

        JSONObject items = new JSONObject(response) ;
        String ask = items.getString("ask");

        info.add(ask);

        return info ;
    }

    @Override
    protected void onPostExecute(ArrayList<String> list) {
        repoDownloadListener.onDownload(list);
    }

    public interface MyStr{
        void onDownload(ArrayList<String> courses);
    }
}
