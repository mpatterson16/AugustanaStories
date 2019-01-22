package net.augustana.maegan.augustanastories;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class StoryActivity extends AppCompatActivity {
    public static final String URL_EXTRA = "url";
    WebView webViewStory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent startIntent = this.getIntent();
        String url = startIntent.getStringExtra(URL_EXTRA);
        webViewStory = (WebView) findViewById(R.id.webViewStory);
        webViewStory.getSettings().setBuiltInZoomControls(true);
        webViewStory.getSettings().setDisplayZoomControls(false);

        //webViewStory.clearCache(true);
        webViewStory.loadUrl("https://lovelace.augustana.edu/AugustanaStories/" + url);

        //https://stackoverflow.com/questions/7308904/link-should-be-open-in-same-web-view-in-android
        webViewStory.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });


    }

    @Override
    public void onBackPressed() {
        if (webViewStory.canGoBack()) {
            webViewStory.goBack();
        } else {
            super.onBackPressed();
        }
    }


}
