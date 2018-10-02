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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class StoryPage extends AppCompatActivity
{
    private Spinner typeDropDown;
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

        typeDropDown = findViewById(R.id.typeDropDown);
        final String[] items = {getResources().getString(R.string.nounLabel),getResources().getString(R.string.verbLabel),
                getResources().getString(R.string.adjectiveLabel),getResources().getString(R.string.grammarLabel),getResources().getString(R.string.otherTermLabel)};
        final ArrayAdapter<String> modeAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,items);
        typeDropDown.setAdapter(modeAdapter);
        typeDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String type = parent.getItemAtPosition(position).toString();
                if( type.equalsIgnoreCase(getResources().getString(R.string.nounLabel)))
                {
                    rePopulateListView(nounList);
                }
                else if (type.equalsIgnoreCase(getResources().getString(R.string.verbLabel)))
                {
                    rePopulateListView(verbList);
                }
                else if (type.equalsIgnoreCase(getResources().getString(R.string.adjectiveLabel)))
                {
                    rePopulateListView(adjectiveList);
                }
                else if (type.equalsIgnoreCase(getResources().getString(R.string.grammarLabel)))
                {
                    rePopulateListView(grammarList);
                }
                else if (type.equalsIgnoreCase(getResources().getString(R.string.otherTermLabel)))
                {
                    rePopulateListView(otherList);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        termListView = findViewById(R.id.termListView);

        browser = findViewById(R.id.googleTranslate);
        browser.setWebViewClient(new WebViewClient());


        browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setLoadWithOverviewMode(true);
        browser.getSettings().setUseWideViewPort(true);
        browser.getSettings().setSupportZoom(true);
        browser.getSettings().setBuiltInZoomControls(true);
        browser.getSettings().setDisplayZoomControls(false);
        browser.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        browser.setScrollbarFadingEnabled(false);
        browser.loadUrl("https://translate.google.com/#ja/en");
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
