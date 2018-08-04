package edu.cofc.japanesestudytool.Pages;

import edu.cofc.japanesestudytool.R;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class StoryPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_page);
        Intent intent = getIntent();
        String test = intent.getStringExtra("id");
    }
}
