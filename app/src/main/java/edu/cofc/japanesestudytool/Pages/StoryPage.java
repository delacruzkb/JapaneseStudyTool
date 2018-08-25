package edu.cofc.japanesestudytool.Pages;

import edu.cofc.japanesestudytool.R;
import edu.cofc.japanesestudytool.Term;
import edu.cofc.japanesestudytool.TermListAdapter;
import edu.cofc.japanesestudytool.TermMenuMetrics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class StoryPage extends AppCompatActivity
{
    private Button nounListButton,verbListButton, adjectiveListButton, grammarListButton, otherListButton;
    private WebView browser;
    private ListView termListView;
    private ArrayList<Term> nounList, verbList, adjectiveList, grammarList, otherList;
    private boolean useJapaneseFirst, useKanji,useLessonKanjiOnly, useKanjiFirst;
    private TermMenuMetrics metrics;

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

        termListView = findViewById(R.id.termListView);

        browser = (WebView) findViewById(R.id.googleTranslate);
        browser.setWebViewClient(new WebViewClient());
        browser.loadUrl("https://translate.google.com/#ja/en");
        browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setLoadWithOverviewMode(true);
        browser.getSettings().setUseWideViewPort(true);

        browser.getSettings().setSupportZoom(true);
        browser.getSettings().setBuiltInZoomControls(true);
        browser.getSettings().setDisplayZoomControls(false);

        browser.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        browser.setScrollbarFadingEnabled(false);
    }

    private void rePopulateListView(ArrayList<Term> terms)
    {
        TermListAdapter termListAdapter = new TermListAdapter(this.getApplicationContext(),terms,useJapaneseFirst,useKanji,useLessonKanjiOnly,useKanjiFirst);
        termListView.setAdapter(termListAdapter);
    }

    private void gatherInformation(Intent intent)
    {
        metrics = intent.getParcelableExtra("metrics");
        nounList=intent.getParcelableArrayListExtra("nounList");
        verbList=intent.getParcelableArrayListExtra("verbList");
        adjectiveList=intent.getParcelableArrayListExtra("adjectiveList");
        grammarList=intent.getParcelableArrayListExtra("grammarList");
        otherList=intent.getParcelableArrayListExtra("otherList");

        useJapaneseFirst= metrics.isJapaneseFirst();
        useKanji = metrics.isKanji();
        useLessonKanjiOnly = metrics.isLessonKanjiOnly();
        useKanjiFirst = metrics.isKanjiFirst();
    }
}
