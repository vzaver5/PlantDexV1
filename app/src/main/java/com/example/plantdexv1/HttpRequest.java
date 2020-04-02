package com.example.plantdexv1;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//This class lets me make GET requests and retrieve info back so i can populate the
//app with information.
public class HttpRequest extends AsyncTask<String, Void, Integer> {
    public AsyncResponse delegate = null;
    static HttpURLConnection con;
    int numP = 152;
    @Override
    protected Integer doInBackground(String... url) {
        System.out.println("Executing GET Request");
        try{
            URL obj = new URL(url[0]);
            con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            numP = con.getHeaderFieldInt("total-pages", 151);
            return numP;
        }catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //numberOfPages();
        //delegate.http = con;
        return numP;
    }



    @Override
    protected void onPostExecute(Integer numPages){
        System.out.println("Hello in onPostExec");
        delegate.setMaxPages(numPages);       //Return back the number of max pages for the list
    }
/*



     */
}
