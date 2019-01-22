package net.augustana.maegan.augustanastories;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AboutActivity extends AppCompatActivity {
    private Button sourcesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        sourcesButton = (Button) findViewById(R.id.sourcesButton);
        sourcesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), StoryActivity.class);
                intent.putExtra(StoryActivity.URL_EXTRA, "sources.html");
                startActivity(intent);
            }
        });
    }
}
