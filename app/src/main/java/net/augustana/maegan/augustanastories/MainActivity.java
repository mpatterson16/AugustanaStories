package net.augustana.maegan.augustanastories;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button aboutButton;
    private Button mapButton;
    private Button storiesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                startActivity(intent);
            }
        });

        storiesButton = (Button) findViewById(R.id.storiesButton);
        storiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), StoryActivity.class);
                startActivity(intent);
            }
        });
    }
/*
    public void handleAboutButton(View view) {
        aboutButton.setText("new text");
    }
*/
}
