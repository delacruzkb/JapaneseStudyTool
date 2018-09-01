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
import android.widget.Switch;

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

    private Switch displayLanguageToggle,kanjiToggle, lessonKanjiToggle,displayKanjiToggle;

    private CheckBox lesson1, lesson2, lesson3, lesson4, lesson5,lesson6,lesson7, lesson8, lesson9, lesson10, lesson11, lesson12;
    private CheckBox lesson13, lesson14, lesson15, lesson16, lesson17, lesson18, lesson19, lesson20, lesson21, lesson22, lesson23;
    private CheckBox allLessons, extraTerms;
    private String whichMode;
    private final int minCountLimit=0;
    private final int maxCountLimit=50;

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
        lessonKanjiToggle.setVisibility(View.INVISIBLE);
        displayKanjiToggle.setVisibility(View.INVISIBLE);

        //Instantiate checkboxes
        lesson1 = findViewById(R.id.lessonCheckBox1);
        lesson2 = findViewById(R.id.lessonCheckBox2);
        lesson3 = findViewById(R.id.lessonCheckBox3);
        lesson4 = findViewById(R.id.lessonCheckBox4);
        lesson5 = findViewById(R.id.lessonCheckBox5);
        lesson6 = findViewById(R.id.lessonCheckBox6);
        lesson7 = findViewById(R.id.lessonCheckBox7);
        lesson8 = findViewById(R.id.lessonCheckBox8);
        lesson9 = findViewById(R.id.lessonCheckBox9);
        lesson10 = findViewById(R.id.lessonCheckBox10);
        lesson11 = findViewById(R.id.lessonCheckBox11);
        lesson12 = findViewById(R.id.lessonCheckBox12);
        lesson13 = findViewById(R.id.lessonCheckBox13);
        lesson14 = findViewById(R.id.lessonCheckBox14);
        lesson15 = findViewById(R.id.lessonCheckBox15);
        lesson16 = findViewById(R.id.lessonCheckBox16);
        lesson17 = findViewById(R.id.lessonCheckBox17);
        lesson18 = findViewById(R.id.lessonCheckBox18);
        lesson19 = findViewById(R.id.lessonCheckBox19);
        lesson20 = findViewById(R.id.lessonCheckBox20);
        lesson21 = findViewById(R.id.lessonCheckBox21);
        lesson22 = findViewById(R.id.lessonCheckBox22);
        lesson23 = findViewById(R.id.lessonCheckBox23);
        allLessons = findViewById(R.id.allLessonsCheckBox);
        extraTerms = findViewById(R.id.extraTerms);
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
        displayLanguageToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!displayLanguageToggle.isChecked())
                {
                    displayKanjiToggle.setChecked(false);
                }
            }
        });
        kanjiToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!kanjiToggle.isChecked())
                {
                    lessonKanjiToggle.setChecked(false);
                    displayKanjiToggle.setChecked(false);
                    lessonKanjiToggle.setVisibility(View.INVISIBLE);
                    displayKanjiToggle.setVisibility(View.INVISIBLE);
                }
                else
                {
                    lessonKanjiToggle.setVisibility(View.VISIBLE);
                    displayKanjiToggle.setVisibility(View.VISIBLE);
                }
            }
        });
        displayKanjiToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!displayLanguageToggle.isChecked())
                {
                    displayKanjiToggle.setChecked(false);
                }
            }
        });
    }

    private void setCheckBoxOnClickListener()
    {
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allLessons.setChecked(false);
            }
        };
        lesson1.setOnClickListener(listener);
        lesson2.setOnClickListener(listener);
        lesson3.setOnClickListener(listener);
        lesson4.setOnClickListener(listener);
        lesson5.setOnClickListener(listener);
        lesson6.setOnClickListener(listener);
        lesson7.setOnClickListener(listener);
        lesson8.setOnClickListener(listener);
        lesson9.setOnClickListener(listener);
        lesson10.setOnClickListener(listener);
        lesson11.setOnClickListener(listener);
        lesson12.setOnClickListener(listener);
        lesson13.setOnClickListener(listener);
        lesson14.setOnClickListener(listener);
        lesson15.setOnClickListener(listener);
        lesson16.setOnClickListener(listener);
        lesson17.setOnClickListener(listener);
        lesson18.setOnClickListener(listener);
        lesson19.setOnClickListener(listener);
        lesson20.setOnClickListener(listener);
        lesson21.setOnClickListener(listener);
        lesson22.setOnClickListener(listener);
        lesson23.setOnClickListener(listener);
        extraTerms.setOnClickListener(listener);

        allLessons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(allLessons.isChecked())
                {
                    lesson1.setChecked(false);
                    lesson2.setChecked(false);
                    lesson3.setChecked(false);
                    lesson4.setChecked(false);
                    lesson5.setChecked(false);
                    lesson6.setChecked(false);
                    lesson7.setChecked(false);
                    lesson8.setChecked(false);
                    lesson9.setChecked(false);
                    lesson10.setChecked(false);
                    lesson11.setChecked(false);
                    lesson12.setChecked(false);
                    lesson13.setChecked(false);
                    lesson14.setChecked(false);
                    lesson15.setChecked(false);
                    lesson16.setChecked(false);
                    lesson17.setChecked(false);
                    lesson18.setChecked(false);
                    lesson19.setChecked(false);
                    lesson20.setChecked(false);
                    lesson21.setChecked(false);
                    lesson22.setChecked(false);
                    lesson23.setChecked(false);
                    extraTerms.setChecked(false);
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
                metrics.setAllTerms(allLessons.isChecked());
                //Gather all lessons selected
                int[] lessons=null;
                if(!allLessons.isChecked())
                {
                    //Gather Status of all checkboxes
                    boolean[] temp = new boolean[24];
                    temp[0]= lesson1.isChecked();
                    temp[1]= lesson2.isChecked();
                    temp[2]= lesson3.isChecked();
                    temp[3]= lesson4.isChecked();
                    temp[4]= lesson5.isChecked();
                    temp[5]= lesson6.isChecked();
                    temp[6]= lesson7.isChecked();
                    temp[7]= lesson8.isChecked();
                    temp[8]= lesson9.isChecked();
                    temp[9]= lesson10.isChecked();
                    temp[10]= lesson11.isChecked();
                    temp[11]= lesson12.isChecked();
                    temp[12]= lesson13.isChecked();
                    temp[13]= lesson14.isChecked();
                    temp[14]= lesson15.isChecked();
                    temp[15]= lesson16.isChecked();
                    temp[16]= lesson17.isChecked();
                    temp[17]= lesson18.isChecked();
                    temp[18]= lesson19.isChecked();
                    temp[19]= lesson20.isChecked();
                    temp[20]= lesson21.isChecked();
                    temp[21]= lesson22.isChecked();
                    temp[22]= lesson23.isChecked();
                    temp[23] = extraTerms.isChecked();

                    //Count how many lessons are selected
                    int lessonCounter=0;
                    for(int i = 0; i<temp.length; i++)
                    {
                        if(temp[i])
                        {
                            lessonCounter++;
                        }
                    }
                    //Make a new array only containing the selected lessons
                    lessons = new int[lessonCounter];
                    int placeCounter= 0;
                    for(int i = 0; i<temp.length; i++)
                    {
                        if(temp[i])
                        {
                            lessons[placeCounter] = i+1;
                            placeCounter++;
                        }
                    }
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
