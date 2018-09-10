package edu.cofc.japanesestudytool.Pages;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import edu.cofc.japanesestudytool.Adapters.CheckBoxDropDownSpinnerAdapter;
import edu.cofc.japanesestudytool.AsyncTasks.QueryTerms;
import edu.cofc.japanesestudytool.R;
import edu.cofc.japanesestudytool.TermMenuMetrics;

public class TermsMenuPage extends AppCompatActivity
{
    private EditText nounCountText, adjectiveCountText, verbCountText, grammarCountText, otherCountText;
    private Button nounCountDecreaseButton, nounCountIncreaseButton;
    private Button adjectiveCountDecreaseButton, adjectiveCountIncreaseButton;
    private Button verbCountDecreaseButton, verbCountIncreaseButton;
    private Button grammarCountDecreaseButton, grammarCountIncreaseButton;
    private Button otherCountDecreaseButton, otherCountIncreaseButton;
    private Button confirmButton;

    private Switch displayLanguageToggle,kanjiToggle, lessonKanjiToggle,displayKanjiToggle, kanjiOnlyToggle;

    private Spinner lessonDropDown;
    private CheckBox allLessons;
    private String whichMode;
    private final int minCountLimit=0;
    private final int maxCountLimit=100;
    private final String[] lessonSpec = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19",
            "20","21","22","23","extra"};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_menu_page);
        whichMode= getIntent().getStringExtra("mode");
        // segregated into private methods to ease debugging
        initializeViews();
        setCountButtonOnClickListeners();
        setCountTextOnFocusChangeListeners();
        setConfirmButtonOnClickListener();
        setSwitchOnClickListener();
        setCheckBoxOnClickListener();
    }

    private void initializeViews()
    {
        //Instantiate Counters
        nounCountText = findViewById(R.id.nounCountInput);
        adjectiveCountText = findViewById(R.id.adjectiveCountInput);
        verbCountText = findViewById(R.id.verbCountInput);
        grammarCountText = findViewById(R.id.grammarCountInput);
        otherCountText = findViewById(R.id.otherCountInput);

        //Instantiate buttons
        nounCountDecreaseButton = findViewById(R.id.nounCountDecreaseButton);
        nounCountIncreaseButton = findViewById(R.id.nounCountIncreaseButton);
        adjectiveCountDecreaseButton = findViewById(R.id.adjectiveCountDecreaseButton);
        adjectiveCountIncreaseButton = findViewById(R.id.adjectiveCountIncreaseButton);
        verbCountDecreaseButton = findViewById(R.id.verbCountDecreaseButton);
        verbCountIncreaseButton = findViewById(R.id.verbCountIncreaseButton);
        grammarCountDecreaseButton = findViewById(R.id.grammarCountDecreaseButton);
        grammarCountIncreaseButton = findViewById(R.id.grammarCountIncreaseButton);
        otherCountDecreaseButton = findViewById(R.id.otherCountDecreaseButton);
        otherCountIncreaseButton = findViewById(R.id.otherCountIncreaseButton);
        confirmButton = findViewById(R.id.confirmButton);

        //Instantiate switches
        displayLanguageToggle = findViewById(R.id.displayLanguageSwitch);
        kanjiToggle = findViewById(R.id.kanjiSwitch);
        lessonKanjiToggle = findViewById(R.id.lessonKanjiSwitch);
        displayKanjiToggle= findViewById(R.id.displayKanjiSwitch);
        kanjiOnlyToggle = findViewById(R.id.kanjiOnlySwitch);
        lessonKanjiToggle.setVisibility(View.INVISIBLE);
        displayKanjiToggle.setVisibility(View.INVISIBLE);
        kanjiOnlyToggle.setVisibility(View.INVISIBLE);


        //Instantiate checkboxes
        allLessons = findViewById(R.id.allLessonsCheckBox);

        //Instantiate dropdown checkboxes
        lessonDropDown = findViewById(R.id.termLessonsCheckDropDown);
        CheckBoxDropDownSpinnerAdapter adapter = new CheckBoxDropDownSpinnerAdapter(this.getApplicationContext(),0,lessonSpec);
        lessonDropDown.setAdapter(adapter);
    }

    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(confirmButton.getContext());
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

                Intent intent = new Intent(confirmButton.getContext(), HomePage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void setCountButtonOnClickListeners()
    {
        nounCountDecreaseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                makeCountsValid();
                int count = Integer.parseInt(nounCountText.getText().toString());
                if(count > minCountLimit)
                {
                    nounCountText.setText((count-1) + "");
                }
            }
        });

        nounCountIncreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                makeCountsValid();
                int count = Integer.parseInt(nounCountText.getText().toString());
                if(count < maxCountLimit)
                {
                    nounCountText.setText((count+1) + "");
                }
            }
        });

        adjectiveCountDecreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                makeCountsValid();
                int count = Integer.parseInt(adjectiveCountText.getText().toString());
                if(count > minCountLimit)
                {
                    adjectiveCountText.setText((count-1) + "");
                }
            }
        });

        adjectiveCountIncreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                makeCountsValid();
                int count = Integer.parseInt(adjectiveCountText.getText().toString());
                if(count < maxCountLimit)
                {
                    adjectiveCountText.setText((count+1) + "");
                }
            }
        });

        verbCountDecreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCountsValid();
                int count = Integer.parseInt(verbCountText.getText().toString());
                if(count > minCountLimit)
                {
                    verbCountText.setText((count-1) + "");
                }
            }
        });

        verbCountIncreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCountsValid();
                int count = Integer.parseInt(verbCountText.getText().toString());
                if(count < maxCountLimit)
                {
                    verbCountText.setText((count+1) + "");
                }
            }
        });

        grammarCountDecreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCountsValid();
                int count = Integer.parseInt(grammarCountText.getText().toString());
                if(count > minCountLimit)
                {
                    grammarCountText.setText((count-1) + "");
                }
            }
        });

        grammarCountIncreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCountsValid();
                int count = Integer.parseInt(grammarCountText.getText().toString());
                if(count < maxCountLimit)
                {
                    grammarCountText.setText((count+1) + "");
                }
            }
        });

        otherCountDecreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCountsValid();
                int count = Integer.parseInt(otherCountText.getText().toString());
                if(count > minCountLimit)
                {
                    otherCountText.setText((count-1) + "");
                }
            }
        });

        otherCountIncreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCountsValid();
                int count = Integer.parseInt(otherCountText.getText().toString());
                if(count <maxCountLimit )
                {
                    otherCountText.setText((count+1) + "");
                }
            }
        });
    }

    private void setCountTextOnFocusChangeListeners()
    {
        View.OnFocusChangeListener listener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                makeCountsValid();
            }
        };
        nounCountText.setOnFocusChangeListener(listener);

        adjectiveCountText.setOnFocusChangeListener(listener);

        verbCountText.setOnFocusChangeListener(listener);

        grammarCountText.setOnFocusChangeListener(listener);

        otherCountText.setOnFocusChangeListener(listener);
    }

    private void setSwitchOnClickListener()
    {
        kanjiToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!kanjiToggle.isChecked())
                {
                    lessonKanjiToggle.setChecked(false);
                    displayKanjiToggle.setChecked(false);
                    kanjiOnlyToggle.setChecked(false);
                    kanjiOnlyToggle.setVisibility(View.INVISIBLE);
                    lessonKanjiToggle.setVisibility(View.INVISIBLE);
                    displayKanjiToggle.setVisibility(View.INVISIBLE);
                }
                else
                {
                    lessonKanjiToggle.setVisibility(View.VISIBLE);
                    displayKanjiToggle.setVisibility(View.VISIBLE);
                    kanjiOnlyToggle.setVisibility(View.VISIBLE);
                }
            }
        });

        displayLanguageToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!displayLanguageToggle.isChecked())
                {
                    displayKanjiToggle.setChecked(false);
                }
            }
        });

        displayKanjiToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!displayLanguageToggle.isChecked()&& displayKanjiToggle.isChecked())
                {
                    displayLanguageToggle.setChecked(true);
                    displayKanjiToggle.setChecked(true);
                }
            }
        });

    }

    private void setCheckBoxOnClickListener()
    {

        allLessons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(allLessons.isChecked())
                {
                    lessonDropDown.setVisibility(View.INVISIBLE);
                }
                else
                {
                    lessonDropDown.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void setConfirmButtonOnClickListener()
    {
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TermMenuMetrics metrics = new TermMenuMetrics();
                metrics.setMode(whichMode);
                makeCountsValid();
                metrics.setNounCount(Integer.parseInt(nounCountText.getText().toString()));
                metrics.setAdjectiveCount(Integer.parseInt(adjectiveCountText.getText().toString()));
                metrics.setVerbCount( Integer.parseInt(verbCountText.getText().toString()));
                metrics.setGrammarCount(Integer.parseInt(grammarCountText.getText().toString()));
                metrics.setOtherCount(Integer.parseInt(otherCountText.getText().toString()));
                metrics.setJapaneseFirst(displayLanguageToggle.isChecked());
                metrics.setKanji(kanjiToggle.isChecked());
                metrics.setLessonKanjiOnly(lessonKanjiToggle.isChecked());
                metrics.setKanjiFirst(displayKanjiToggle.isChecked());
                metrics.setKanjiOnly(kanjiOnlyToggle.isChecked());
                metrics.setAllTerms(allLessons.isChecked());
                //Gather all lessons selected
                int[] lessons=null;
                if(!allLessons.isChecked())
                {
                    //TODO:Figure out Lessons
                }
                metrics.setLessons(lessons);
                QueryTerms queryTerms = new QueryTerms(metrics, confirmButton.getContext());
                queryTerms.execute();

            }
        });
    }

    private void makeCountsValid()
    {
        if(nounCountText.getText().toString().equalsIgnoreCase(""))
        {
            nounCountText.setText("0");
        }
        else if(nounCountText.getText().toString().length() >6)
        {
            nounCountText.setText(Integer.toString(maxCountLimit));
        }
        else if(Integer.parseInt(nounCountText.getText().toString()) >maxCountLimit)
        {
            nounCountText.setText(Integer.toString(maxCountLimit));
        }

        if((adjectiveCountText.getText().toString().equalsIgnoreCase("")))
        {
            adjectiveCountText.setText("0");
        }
        else if(adjectiveCountText.getText().toString().length() >6)
        {
            adjectiveCountText.setText(Integer.toString(maxCountLimit));
        }
        else if(Integer.parseInt(adjectiveCountText.getText().toString()) >maxCountLimit)
        {
            adjectiveCountText.setText(Integer.toString(maxCountLimit));
        }

        if((verbCountText.getText().toString().equalsIgnoreCase("")))
        {
            verbCountText.setText("0");
        }
        else if(verbCountText.getText().toString().length() >6)
        {
            verbCountText.setText(Integer.toString(maxCountLimit));
        }
        else if(Integer.parseInt( verbCountText.getText().toString()) >maxCountLimit)
        {
            verbCountText.setText(Integer.toString(maxCountLimit));
        }

        if((grammarCountText.getText().toString().equalsIgnoreCase("")))
        {
            grammarCountText.setText("0");
        }
        else if(grammarCountText.getText().toString().length() >6)
        {
            grammarCountText.setText(Integer.toString(maxCountLimit));
        }
        else if(Integer.parseInt(grammarCountText.getText().toString()) >maxCountLimit)
        {
            grammarCountText.setText(Integer.toString(maxCountLimit));
        }

        if((otherCountText.getText().toString().equalsIgnoreCase("")))
        {
            otherCountText.setText("0");
        }
        else if(otherCountText.getText().toString().length() >6)
        {
            otherCountText.setText(Integer.toString(maxCountLimit));
        }
        else if(Integer.parseInt(otherCountText.getText().toString()) >maxCountLimit)
        {
            otherCountText.setText(Integer.toString(maxCountLimit));
        }
    }
}
