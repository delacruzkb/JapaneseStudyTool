package edu.cofc.japanesestudytool.Pages;


import edu.cofc.japanesestudytool.R;
import edu.cofc.japanesestudytool.Term;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import java.util.ArrayList;



public class StoryPage extends AppCompatActivity
{
    Button nounListButton,verbListButton, adjectiveListButton, grammarListButton, otherListButton;
    WebView browser;

    ArrayList<Term> nounList, verbList, adjectiveList, grammarList, otherList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_page);

        //Initializing Views
        initializeViews();

        //Gather information and store them into the array lists
        gatherInformation(getIntent());
    }

    private void initializeViews()
    {
        nounList = new ArrayList<>();
        verbList = new ArrayList<>();
        adjectiveList = new ArrayList<>();
        grammarList = new ArrayList<>();
        otherList = new ArrayList<>();
        nounListButton = findViewById(R.id.nounListButton);
        nounListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                rePopulateListView(nounList);
            }
        });
        verbListButton = findViewById(R.id.verbListButton);
        verbListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                rePopulateListView(verbList);
            }
        });
        adjectiveListButton = findViewById(R.id.adjectiveListButton);
        adjectiveListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                rePopulateListView(adjectiveList);
            }
        });
        grammarListButton = findViewById(R.id.grammarlistButton);
        grammarListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                rePopulateListView(grammarList);
            }
        });
        otherListButton = findViewById(R.id.otherListButton);
        otherListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                rePopulateListView(otherList);
            }
        });

        browser = (WebView) findViewById(R.id.googleTranslate);
        browser.setWebViewClient(new WebViewClient());
        browser.getSettings().setBuiltInZoomControls(true);
        browser.loadUrl("https://translate.google.com/m?hl=en&sl=ja&tl=en&ie=UTF-8&prev=_m&q=");
    }

    private void rePopulateListView(ArrayList<Term> terms)
    {
        /**TODO: update listview with appropriate arraylist
         * */
    }

    private void gatherInformation(Intent intent)
    {
        int nounCount = intent.getIntExtra("nounCount",0);
        int verbCount = intent.getIntExtra("verbCount",0);
        int adjectiveCount = intent.getIntExtra("adjectiveCount",0);
        int grammarCount = intent.getIntExtra("grammarCount",0);
        int otherCount = intent.getIntExtra("otherCount",0);

        boolean useKanji = intent.getBooleanExtra("kanji",false);
        boolean useLessonKanjiOnly = intent.getBooleanExtra("lessonKanji",false);
        boolean allLessons = intent.getBooleanExtra("all",false);
        if(!allLessons)
        {
            boolean l1 = intent.getBooleanExtra("l1",false);
            boolean l2 = intent.getBooleanExtra("l2",false);
            boolean l3 = intent.getBooleanExtra("l3",false);
            boolean l4 = intent.getBooleanExtra("l4",false);
            boolean l5 = intent.getBooleanExtra("l5",false);
            boolean l6 = intent.getBooleanExtra("l6",false);
            boolean l7 = intent.getBooleanExtra("l7",false);
            boolean l8 = intent.getBooleanExtra("l8",false);
            boolean l9 = intent.getBooleanExtra("l9",false);
            boolean l10 = intent.getBooleanExtra("l10",false);
            boolean l11 = intent.getBooleanExtra("l11",false);
            boolean l12 = intent.getBooleanExtra("l12",false);
            boolean l13 = intent.getBooleanExtra("l13",false);
            boolean l14 = intent.getBooleanExtra("l14",false);
            boolean l15 = intent.getBooleanExtra("l15",false);
            boolean l16 = intent.getBooleanExtra("l16",false);
            boolean l17 = intent.getBooleanExtra("l17",false);
            boolean l18 = intent.getBooleanExtra("l18",false);
            boolean l19 = intent.getBooleanExtra("l19",false);
            boolean l20 = intent.getBooleanExtra("l20",false);
            boolean l21 = intent.getBooleanExtra("l21",false);
            boolean l22 = intent.getBooleanExtra("l22",false);
            boolean l23 = intent.getBooleanExtra("l23",false);
        }
        /**TODO: Pull from database using the proper parameters and store into each respective arrayList
         * */
    }
}
