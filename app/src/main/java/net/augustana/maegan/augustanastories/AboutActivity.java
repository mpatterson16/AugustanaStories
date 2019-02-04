package net.augustana.maegan.augustanastories;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {
    private Button sourcesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView text = (TextView) findViewById(R.id.text);
        text.setMovementMethod(new ScrollingMovementMethod());

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
