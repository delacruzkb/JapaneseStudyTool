package edu.cofc.japanesestudytool.Pages.Study;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import edu.cofc.japanesestudytool.R;
import edu.cofc.japanesestudytool.Database.Entities.Term;
import edu.cofc.japanesestudytool.TermMenuMetrics;

import android.content.Intent;
import android.text.method.ScrollingMovementMethod;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class FlashCardPage extends AppCompatActivity
{
    private ArrayList<Term> termList;
    private TextView  typeValueLabel, reqKanjiLabel, cardCountLabel;
    private EditText lessonNumberLabel,flashCard;
    private Button flipEng, flipJpns, flipKanji, prevCard,nextCard;
    private boolean showJapaneseFirst, showKanjiFirst;
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
        lessonNumberLabel.setEnabled(false);
        typeValueLabel = findViewById(R.id.typeValueLabel);
        reqKanjiLabel = findViewById(R.id.reqKanjiLabel);
        flashCard = findViewById(R.id.flashCard);
        flashCard.setEnabled(false);
        flashCard.setMovementMethod(new ScrollingMovementMethod());
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
                Term term = termList.get(currentCardNumber);
                if(term.getParticle() != null)
                {
                    flashCard.setText(term.getParticle() + "　" + term.getJpns());
                }
                else
                {
                    flashCard.setText(term.getJpns());
                }

            }
        });
        flipKanji = findViewById(R.id.flipKanji);
        flipKanji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Term term = termList.get(currentCardNumber);
                if(term.getParticle()!=null)
                {
                    flashCard.setText(term.getParticle() + "　"+ term.getKanji());
                }
                else
                {
                    flashCard.setText(term.getKanji());
                }

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

    private void loadFlipCard(Term term)
    {
        lessonNumberLabel.setText(term.getLessonString());
        typeValueLabel.setText(term.getType());
        reqKanjiLabel.setVisibility(View.INVISIBLE);
        flipKanji.setVisibility(View.VISIBLE);
        //Decide what to use
        if(showJapaneseFirst)
        {
            if (term.getParticle() == null)
            {
                flashCard.setText(term.getJpns());
            }
            else
            {
                flashCard.setText(term.getParticle()+ "　" + term.getJpns());
            }

        }
        else
        {
            flashCard.setText(term.getEng());
        }
        //Hide Kanji Flip if: null value, don't use kanji, not required kanji when asked
        if(term.getKanji() == null || term.getKanji().equalsIgnoreCase("") || term.getKanji().equalsIgnoreCase("null"))
        {
            flipKanji.setVisibility(View.INVISIBLE);
        }

        //If kanji must be first AND if it is available
        if(showKanjiFirst && flipKanji.getVisibility()==View.VISIBLE)
        {
            flashCard.setText(term.getKanji());
            if (term.getParticle() == null)
            {
                flashCard.setText(term.getKanji());
            }
            else
            {
                flashCard.setText(term.getParticle()+ "　" + term.getKanji());
            }
        }

        if(term.isReqKanji())

        {
            reqKanjiLabel.setVisibility(View.VISIBLE);
        }

        if(term.getType().equalsIgnoreCase("grammar"))
        {
            flashCard.setTextSize(TypedValue.COMPLEX_UNIT_DIP,getResources().getInteger(R.integer.flashCardGrammarTextSize));
        }
        else
        {
            flashCard.setTextSize(TypedValue.COMPLEX_UNIT_DIP,getResources().getInteger(R.integer.flashCardAllTextSize));
        }

        cardCountLabel.setText((currentCardNumber+1) +"/"+ cardCount);

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(flashCard.getContext());
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

                FlashCardPage.super.onBackPressed();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("currentCardNumber",currentCardNumber);
        savedInstanceState.putString("currentCard",flashCard.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        currentCardNumber = savedInstanceState.getInt("currentCardNumber");
        loadFlipCard(termList.get(currentCardNumber));
        flashCard.setText(savedInstanceState.getString("currentCard"));
        if(termList.size()!=1)
        {
            if(currentCardNumber==0)
            {
                prevCard.setVisibility(View.INVISIBLE);
                nextCard.setVisibility(View.VISIBLE);
            }
            else if(currentCardNumber==termList.size()-1)
            {
                prevCard.setVisibility(View.VISIBLE);
                nextCard.setVisibility(View.INVISIBLE);
            }
            else
            {
                prevCard.setVisibility(View.VISIBLE);
                nextCard.setVisibility(View.VISIBLE);
            }
        }
    }

    private void gatherInformation(Intent intent)
    {
        metrics = (TermMenuMetrics)intent.getSerializableExtra("metrics");
        termList= metrics.getTermList();
        showJapaneseFirst= metrics.showJpnsFirst();
        showKanjiFirst = metrics.showKanjiFirst();
        cardCount=termList.size();
        currentCardNumber=0;
        loadFlipCard(termList.get(0));
        if(termList.size()<2)
        {
            nextCard.setVisibility(View.INVISIBLE);
        }
    }
}
