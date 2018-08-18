package edu.cofc.japanesestudytool.Pages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.cofc.japanesestudytool.R;
import edu.cofc.japanesestudytool.Term;

import android.content.Intent;
import java.util.ArrayList;

public class FlashCardPage extends AppCompatActivity
{
    private ArrayList<Term> termsList;
    private boolean useJapaneseFirst;
    private boolean useKanji;
    private boolean useLessonKanjiOnly;
    private boolean useKanjiFirst;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card_page);
        gatherInformation(getIntent());
    }

    private void gatherInformation(Intent intent)
    {
        useJapaneseFirst= intent.getBooleanExtra("displayJapaneseFirst",true);
        useKanji = intent.getBooleanExtra("kanji",false);
        useLessonKanjiOnly = intent.getBooleanExtra("lessonKanji",false);
        useKanjiFirst = intent.getBooleanExtra("displayKanjiFirst",false);
        termsList = intent.getParcelableArrayListExtra("termsList");

    }
}
