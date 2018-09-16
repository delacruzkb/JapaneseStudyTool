package edu.cofc.japanesestudytool.Pages;

import edu.cofc.japanesestudytool.R;
import edu.cofc.japanesestudytool.Term;
import edu.cofc.japanesestudytool.Adapters.TermListAdapter;
import edu.cofc.japanesestudytool.TermMenuMetrics;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
        //Remove keyboard for viewing
        InputMethodManager imm = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(this);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        //populate
        TermListAdapter termListAdapter = new TermListAdapter(this.getApplicationContext(),terms,metrics);
        termListView.setAdapter(termListAdapter);
    }

    private void gatherInformation(Intent intent)
    {
        metrics = (TermMenuMetrics)intent.getSerializableExtra("metrics");
        nounList= (ArrayList<Term>) intent.getSerializableExtra("nounList");
        verbList=(ArrayList<Term>) intent.getSerializableExtra("verbList");
        adjectiveList=(ArrayList<Term>) intent.getSerializableExtra("adjectiveList");
        grammarList=(ArrayList<Term>) intent.getSerializableExtra("grammarList");
        otherList=(ArrayList<Term>) intent.getSerializableExtra("otherList");


    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(browser.getContext());
        builder.setTitle(getResources().getString(R.string.warningTitle));
        builder.setMessage(getResources().getString(R.string.onBackPressedMessage));
        builder.setNegativeButton(getResources().getString(R.string.cancelLabel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(getResources().getString(R.string.proceedLabel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                StoryPage.super.onBackPressed();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
