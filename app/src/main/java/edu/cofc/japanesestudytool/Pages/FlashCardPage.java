package edu.cofc.japanesestudytool.Pages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.cofc.japanesestudytool.R;
import edu.cofc.japanesestudytool.Term;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class FlashCardPage extends AppCompatActivity
{
    private ArrayList<Term> termsList;
    private TextView lessonNumberLabel, typeValueLabel, reqKanjiLabel, cardCountLabel;
    private EditText flashCard;
    private Button flipEng, flipJpns, flipKanji, prevCard,nextCard;
    private boolean useJapaneseFirst;
    private boolean useKanji;
    private boolean useLessonKanjiOnly;
    private boolean useKanjiFirst;
    private int currentCardNumber;
    private int cardCount;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card_page);
        gatherInformation(getIntent());
        initializeViews();
    }

    private void initializeViews()
    {
        currentCardNumber=0;
        lessonNumberLabel = findViewById(R.id.lessonNumberLabel);
        typeValueLabel = findViewById(R.id.typeValueLabel);
        reqKanjiLabel = findViewById(R.id.reqKanjiLabel);
        flashCard = findViewById(R.id.flashCard);
        flipEng = findViewById(R.id.flipEng);
        flipJpns = findViewById(R.id.flipjpns);
        flipKanji = findViewById(R.id.flipKanji);
        cardCountLabel = findViewById(R.id.cardCountLabel);
        prevCard = findViewById(R.id.prevCard);
        nextCard = findViewById(R.id.nextCard);

        prevCard.setVisibility(View.INVISIBLE);
        if(termsList == null || termsList.size()==0)
        {
            flashCard.setText("No Lessons applicable");
        }
        else
        {
            final Term currentCard = termsList.get(0);
            loadFlipCard(currentCard);
            prevCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    if(currentCardNumber == cardCount-1)
                    {
                        nextCard.setVisibility(View.VISIBLE);
                    }
                    if(currentCardNumber>0) {
                        if (currentCardNumber == 1) {
                            prevCard.setVisibility(View.INVISIBLE);
                        }
                        currentCardNumber--;
                        loadFlipCard(termsList.get(currentCardNumber));
                    }
                }
            });

            nextCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    if(currentCardNumber==0)
                    {
                        prevCard.setVisibility(View.VISIBLE);
                    }
                    if(currentCardNumber<cardCount)
                    {
                        if(currentCardNumber == cardCount-2)
                        {
                            nextCard.setVisibility(View.INVISIBLE);
                        }
                        currentCardNumber++;
                        loadFlipCard(termsList.get(currentCardNumber));
                    }
                }
            });
        }

    }

    private void loadFlipCard(Term term) {
        lessonNumberLabel.setText(term.getLesson());
        typeValueLabel.setText(term.getLesson());
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
                if(term.getReqKanji())
                {
                    reqKanjiLabel.setVisibility(View.VISIBLE);
                }
                if(useLessonKanjiOnly&& !term.getReqKanji())
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

        cardCountLabel.setText(termsList.indexOf(term) +"/"+ cardCount);

    }
    private void gatherInformation(Intent intent)
    {
        useJapaneseFirst= intent.getBooleanExtra("displayJapaneseFirst",true);
        useKanji = intent.getBooleanExtra("kanji",false);
        useLessonKanjiOnly = intent.getBooleanExtra("lessonKanji",false);
        useKanjiFirst = intent.getBooleanExtra("displayKanjiFirst",false);
        termsList = intent.getParcelableArrayListExtra("termsList");
        Collections.shuffle(termsList);
        cardCount = termsList.size();
    }
}
