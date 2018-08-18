package edu.cofc.japanesestudytool.Pages;


import edu.cofc.japanesestudytool.AsyncTasks.QueryTerms;
import edu.cofc.japanesestudytool.R;
import edu.cofc.japanesestudytool.Term;
import edu.cofc.japanesestudytool.TermDatabase;
import edu.cofc.japanesestudytool.TermListAdapter;

import android.arch.persistence.room.Room;
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
    TermDatabase termDatabase;
    Button nounListButton,verbListButton, adjectiveListButton, grammarListButton, otherListButton;
    WebView browser;
    ListView termListView;
    ArrayList<Term> nounList, verbList, adjectiveList, grammarList, otherList;
    boolean useJapaneseFirst;
    boolean useKanji;
    boolean useLessonKanjiOnly;
    boolean useKanjiFirst;
    int[] lessons;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_page);
        termDatabase = Room.databaseBuilder(this,TermDatabase.class,"terms").build();
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
        TermListAdapter listAdapter = new TermListAdapter(this,terms,useKanji,useLessonKanjiOnly);
        termListView.setAdapter(listAdapter);
    }

    private void gatherInformation(Intent intent)
    {
        int nounCount = intent.getIntExtra("nounCount",0);
        int verbCount = intent.getIntExtra("verbCount",0);
        int adjectiveCount = intent.getIntExtra("adjectiveCount",0);
        int grammarCount = intent.getIntExtra("grammarCount",0);
        int otherCount = intent.getIntExtra("otherCount",0);

        useKanji = intent.getBooleanExtra("kanji",false);
        useLessonKanjiOnly = intent.getBooleanExtra("lessonKanji",false);
        boolean allLessons = intent.getBooleanExtra("all",false);

        //Collect all lessons
        if(!allLessons)
        {
            boolean[] temp = new boolean[23];
            int lessonCounter =0;
            for(int i = 0; i<temp.length; i++)
            {
                temp[i]=intent.getBooleanExtra("l"+i,false);
                if(temp[i])
                {
                    lessonCounter++;
                }
            }
            lessons = new int[lessonCounter];
            int placeCounter =0;
            for(int i = 0; i<temp.length; i++)
            {
                if(temp[i])
                {
                    lessons[placeCounter] = i+1;
                    placeCounter++;
                }
            }
        }

        if(allLessons)
        {
            //getAllTerms
            QueryTerms getAllNouns = new QueryTerms(termDatabase,nounList,"noun",null,nounCount);
            QueryTerms getAllVerbs = new QueryTerms(termDatabase,verbList, "verb",null,verbCount);
            QueryTerms getAllAdjectives = new QueryTerms( termDatabase,adjectiveList, "adjectives",null,adjectiveCount);
            QueryTerms getAllOthers = new QueryTerms(termDatabase,otherList,"other",null,otherCount);
            QueryTerms getAllGrammar = new QueryTerms(termDatabase,grammarList, "grammar",null,grammarCount);
            getAllNouns.execute();
            getAllVerbs.execute();
            getAllAdjectives.execute();
            getAllOthers.execute();
            getAllGrammar.execute();
        }
        else
        {
            //getAllLessonTerms
            QueryTerms getAllNouns = new QueryTerms(termDatabase,nounList,"noun",lessons,nounCount);
            QueryTerms getAllVerbs = new QueryTerms(termDatabase,verbList, "verb",lessons,verbCount);
            QueryTerms getAllAdjectives = new QueryTerms( termDatabase,adjectiveList, "adjectives",lessons,adjectiveCount);
            QueryTerms getAllOthers = new QueryTerms(termDatabase,otherList,"other",lessons,otherCount);
            QueryTerms getAllGrammar = new QueryTerms(termDatabase,grammarList, "grammar",lessons,grammarCount);
            getAllNouns.execute();
            getAllVerbs.execute();
            getAllAdjectives.execute();
            getAllOthers.execute();
            getAllGrammar.execute();
        }
    }
}
