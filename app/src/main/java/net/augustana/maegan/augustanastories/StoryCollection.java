package net.augustana.maegan.augustanastories;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.transform.Result;

public class StoryCollection {
    // Singleton pattern
    private static StoryCollection defaultStoryCollection;
    public static StoryCollection getDefaultStoryCollection(String locations) {
        if (defaultStoryCollection == null) {
            Log.d("myTag", "1");
            defaultStoryCollection = new StoryCollection(locations);
        }
        return defaultStoryCollection;
    }


    private Map<String,StoryLocation> stories = new TreeMap<>();

    private StoryCollection(String locations) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        Gson gson = gsonBuilder.create();
        StoryLocation[] storyLocationArray = gson.fromJson(locations, StoryLocation[].class);
        for(StoryLocation storyLocation : storyLocationArray) {
            stories.put(storyLocation.getName(), storyLocation);
        }


        /*StoryLocation[] storyLocationArray = new StoryLocation[] {
//                LatLng sorenson = new LatLng(41.505237, -90.547219);
//        LatLng centennial = new LatLng(41.505229, -90.548837);
//        LatLng denkmann = new LatLng(41.504644, -90.550507);
//        LatLng evald = new LatLng(41.504803, -90.550008);
//        LatLng oldMain = new LatLng(41.504439, -90.549513);
//        LatLng founders = new LatLng(41.503332, -90.548874);

        new StoryLocation("Sorenson","sorenson.html", 41.505237, -90.547219),
               // new StoryLocation("Olin", "olin.html"),
                new StoryLocation("Centennial", "centennial.html", 41.505229, -90.548837),
                new StoryLocation("Denkmann", "denkmann.html", 41.504644, -90.550507),
                new StoryLocation("Old Main", "oldmain.html", 41.504439, -90.549513),
                new StoryLocation("Evald", "evald.html", 41.504803, -90.550008),
                new StoryLocation("Founders Hall", "founders.html", 41.503332, -90.548874),
                new StoryLocation("Andreen", "andreen.html", 41.501750, -90.548426),
                new StoryLocation("Bell Tower", "belltower.html", 41.503913, -90.549269),
                new StoryLocation("Brunner", "brunner.html", 41.504438, -90.548292),
                new StoryLocation("House on the Hill", "houseonthehill.html", 41.501221, -90.555062)
        };*/
        // convert the current object to JSON, and dump the text to the log
        //Gson gson = new Gson();
        //String json = gson.toJson(storyLocationArray);



    }

    public StoryLocation getStoryByName(String name) {
        return stories.get(name);
    }

    public List<StoryLocation> getStoryList() {
        return new ArrayList<>(stories.values());
    }

}
