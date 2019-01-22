package net.augustana.maegan.augustanastories;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private Button aboutButton;
    private Button mapButton;
    private Button storiesButton;
    //private Gson gson;

    private String storyLocationJson = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(!isNetworkAvailable()) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
            builder1.setMessage("Internet is unavailable or not enabled.");
            builder1.setCancelable(true);

            builder1.setNeutralButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                            finish();
                        }
                    });

            AlertDialog alert = builder1.create();
            alert.show();
        }
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try  {
                    storyLocationJson = handleHTTPRequest();
                    Log.d("myTag", storyLocationJson);

                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        });

        thread.start();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aboutButton=(Button)findViewById(R.id.aboutButton);
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), AboutActivity.class);
                startActivity(intent);
                }
        });

        mapButton=(Button)findViewById(R.id.mapButton);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), MapsActivity.class);
                intent.putExtra("locations", storyLocationJson);
                startActivity(intent);
            }
        });

        storiesButton = (Button) findViewById(R.id.storiesButton);
        storiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), StoryActivity.class);
                intent.putExtra(StoryActivity.URL_EXTRA, "index.html");
                startActivity(intent);
            }
        });

    }


    public String handleHTTPRequest() throws IOException{
        URL url = new URL("https://lovelace.augustana.edu/AugustanaStories/storylocations.json");
        URLConnection request = url.openConnection();
        InputStream in = url.openStream();
        BufferedReader r = new BufferedReader(new InputStreamReader(in));
        StringBuilder builder = new StringBuilder();
        String line = r.readLine();
        while (line != null) {
            builder.append(line);
            line = r.readLine();
        }
        String json = builder.toString();
        return json;
        /*GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        Gson gson = gsonBuilder.create();
        StoryLocation[] locations = gson.fromJson(json, StoryLocation[].class);
        return locations;*/
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}
