package edu.cofc.japanesestudytool.Pages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.cofc.japanesestudytool.R;
import edu.cofc.japanesestudytool.Term;
import edu.cofc.japanesestudytool.TermMenuMetrics;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
//TODO:handle void lessons
public class FlashCardPage extends AppCompatActivity
{
    private ArrayList<Term> termList;
    private TextView lessonNumberLabel, typeValueLabel, reqKanjiLabel, cardCountLabel;
    private EditText flashCard;
    private Button flipEng, flipJpns, flipKanji, prevCard,nextCard;
    private boolean useJapaneseFirst;
    private boolean useKanji;
    private boolean useLessonKanjiOnly;
    private boolean useKanjiFirst;
    private int currentCardNumber;
    private int cardCount;
    private TermMenuMetrics metrics;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card_page);
        initializeViews();
        gatherInformation(getIntent());
    }

    private void initializeViews()
    {
        currentCardNumber=0;
        lessonNumberLabel = findViewById(R.id.lessonNumberLabel);
        typeValueLabel = findViewById(R.id.typeValueLabel);
        reqKanjiLabel = findViewById(R.id.reqKanjiLabel);
        flashCard = findViewById(R.id.flashCard);
        flashCard.setEnabled(false);
        flipEng = findViewById(R.id.flipEng);
        flipEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashCard.setText(termList.get(currentCardNumber).getEng());
            }
        });
        flipJpns = findViewById(R.id.flipjpns);
        flipJpns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashCard.setText(termList.get(currentCardNumber).getJpns());
            }
        });
        flipKanji = findViewById(R.id.flipKanji);
        flipKanji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashCard.setText(termList.get(currentCardNumber).getKanji());
            }
        });
        cardCountLabel = findViewById(R.id.cardCountLabel);
        prevCard = findViewById(R.id.prevCard);
        prevCard.setVisibility(View.INVISIBLE);
        prevCard.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            { if(currentCardNumber == cardCount-1)
            {
                nextCard.setVisibility(View.VISIBLE);
            }
                if (currentCardNumber == 1)
                {
                    prevCard.setVisibility(View.INVISIBLE);
                }
                currentCardNumber--;
                loadFlipCard(termList.get(currentCardNumber));
            }
        });
        nextCard = findViewById(R.id.nextCard);
        nextCard.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(currentCardNumber==0)
                {
                    prevCard.setVisibility(View.VISIBLE);
                }
                if(currentCardNumber == cardCount-2)
                {
                    nextCard.setVisibility(View.INVISIBLE);
                }
                currentCardNumber++;
                loadFlipCard(termList.get(currentCardNumber));
            }
        });


    }

    private void loadFlipCard(Term term) {
        lessonNumberLabel.setText(new Integer(term.getLesson()).toString());
        typeValueLabel.setText(term.getType());
        reqKanjiLabel.setVisibility(View.INVISIBLE);
        flipKanji.setVisibility(View.VISIBLE);
        if(!useKanji)
        {
            flipKanji.setVisibility(View.INVISIBLE);
        }
        if(useJapaneseFirst)
        {
            flashCard.setText(term.getJpns());
            if(useKanjiFirst)
            {
                flashCard.setText(term.getKanji());
                if(term.isReqKanji())
                {
                    reqKanjiLabel.setVisibility(View.VISIBLE);
                }
                if(useLessonKanjiOnly&& !term.isReqKanji())
                {
                    flashCard.setText(term.getJpns());
                    flipKanji.setVisibility(View.INVISIBLE);
                }
            }
        }
        else
        {
            flashCard.setText(term.getEng());
        }
        cardCountLabel.setText((currentCardNumber+1) +"/"+ cardCount);

    }
    private void gatherInformation(Intent intent)
    {
        metrics = (TermMenuMetrics)intent.getSerializableExtra("metrics");
        termList= (ArrayList<Term>) intent.getSerializableExtra("termList");
        useJapaneseFirst= metrics.isJapaneseFirst();
        useKanji = metrics.isKanji();
        useLessonKanjiOnly = metrics.isLessonKanjiOnly();
        useKanjiFirst = metrics.isKanjiFirst();
        cardCount=termList.size();
        currentCardNumber=0;
        loadFlipCard(termList.get(0));
    }
}
