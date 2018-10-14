package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.example.maneeshsagar.presenter.DisplayActivity;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;


public class EndpointAsyncTask extends AsyncTask<Context, Void, String> {
    String JOKE_KEY = "joke_key";

    private static MyApi myApiService = null;
    private Context context;

    @Override
    protected String doInBackground(Context... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new
                    MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    // options for running against local devappserver
                    // ­ 10.0.2.2 is localhost's IP address in Android emulator
                    // ­ turn off compression when running agEndPointAsyncTaskTestainst local devappserver
                    .setRootUrl("http://127.0.0.1:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?>
                                                                                         abstractGoogleClientRequest) throws IOException {
                            abstractGoogleClientRequest.setDisableGZipContent(true);
                        }
                    });
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0];


        try {
            return myApiService.sayHi().execute().getData();
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        Intent intent = new Intent(context, DisplayActivity.class);
        intent.putExtra(JOKE_KEY,result);
        context.startActivity(intent);
    }
}