package edu.cofc.japanesestudytool.Pages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Switch;
import android.widget.TextView;

import edu.cofc.japanesestudytool.R;


public class StoryMenuPage extends AppCompatActivity
{
    TextView nounCountText, adjectiveCountText, verbCountText, grammarCountText, otherCountText;

    Button nounCountDecreaseButton, nounCountIncreaseButton;
    Button adjectiveCountDecreaseButton, adjectiveCountIncreaseButton;
    Button verbCountDecreaseButton, verbCountIncreaseButton;
    Button grammarCountDecreaseButton, grammarCountIncreaseButton;
    Button otherCountDecreaseButton, otherCountIncreaseButton;
    Button confirmButton;

    Switch kanjiToggle, lessonKanjiToggle;

    CheckBox lesson1, lesson2, lesson3, lesson4, lesson5,lesson6;
    CheckBox lesson7, lesson8, lesson9, lesson10, lesson11, lesson12;
    CheckBox lesson13, lesson14, lesson15, lesson16, lesson17, lesson18;
    CheckBox lesson19, lesson20, lesson21, lesson22, lesson23;
    CheckBox allLessons;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_menu_page);

        // segregated into private methods to ease debugging
        instantiateViews();
        setCountButtonOnClickListeners();
        setConfirmButtonOnClickListener();

        lessonKanjiToggle.setVisibility(View.INVISIBLE);
        kanjiToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!kanjiToggle.isChecked())
                {
                    lessonKanjiToggle.setChecked(false);
                    lessonKanjiToggle.setVisibility(View.INVISIBLE);
                }
                else
                {
                    lessonKanjiToggle.setVisibility(View.VISIBLE);
                }
            }
        });

        allLessons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(allLessons.isChecked())
                {
                    unCheckOthers();
                }
            }
        });

    }

    private void instantiateViews()
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
        kanjiToggle = findViewById(R.id.kanjiSwitch);
        lessonKanjiToggle = findViewById(R.id.lessonKanjiSwitch);

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
    }

    private void setCountButtonOnClickListeners()
    {
        nounCountDecreaseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int count = Integer.parseInt(nounCountText.getText().toString());
                if(count > 0)
                {
                    nounCountText.setText((count-1) + "");
                }
            }
        });

        nounCountIncreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int count = Integer.parseInt(nounCountText.getText().toString());
                if(count < 15)
                {
                    nounCountText.setText((count+1) + "");
                }
            }
        });

        adjectiveCountDecreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(adjectiveCountText.getText().toString());
                if(count > 0)
                {
                    adjectiveCountText.setText((count-1) + "");
                }
            }
        });

        adjectiveCountIncreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(adjectiveCountText.getText().toString());
                if(count < 15)
                {
                    adjectiveCountText.setText((count+1) + "");
                }
            }
        });

        verbCountDecreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(verbCountText.getText().toString());
                if(count > 0)
                {
                    verbCountText.setText((count-1) + "");
                }
            }
        });

        verbCountIncreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(verbCountText.getText().toString());
                if(count < 15)
                {
                    verbCountText.setText((count+1) + "");
                }
            }
        });

        grammarCountDecreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(grammarCountText.getText().toString());
                if(count > 0)
                {
                    grammarCountText.setText((count-1) + "");
                }
            }
        });

        grammarCountIncreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(grammarCountText.getText().toString());
                if(count < 15)
                {
                    grammarCountText.setText((count+1) + "");
                }
            }
        });

        otherCountDecreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(otherCountText.getText().toString());
                if(count > 0)
                {
                    otherCountText.setText((count-1) + "");
                }
            }
        });

        otherCountIncreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = Integer.parseInt(otherCountText.getText().toString());
                if(count <15 )
                {
                    otherCountText.setText((count+1) + "");
                }
            }
        });
    }

    private void setConfirmButtonOnClickListener()
    {
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**TODO: configure confirmButton
                 * 1) gather information from all views
                 * 2) store information in Intent
                 * 3) start StoryPage activity
                 * */

                Intent intent = new Intent(confirmButton.getContext(), StoryPage.class);

                int nounCount = Integer.parseInt(nounCountText.getText().toString());
                int adjectiveCount = Integer.parseInt(adjectiveCountText.getText().toString());
                int verbCount = Integer.parseInt(verbCountText.getText().toString());
                int grammarCount = Integer.parseInt(grammarCountText.getText().toString());
                int otherCount = Integer.parseInt(otherCountText.getText().toString());
                boolean kanji = kanjiToggle.isChecked();
                boolean lessonKanji = lessonKanjiToggle.isChecked();
                intent.putExtra("nounCount",nounCount);
                intent.putExtra("adjectiveCount",adjectiveCount);
                intent.putExtra("verbCount",verbCount);
                intent.putExtra("grammarCount",grammarCount);
                intent.putExtra("otherCount",otherCount);
                intent.putExtra("kanji",kanji);
                intent.putExtra("lessonKanji",lessonKanji);

                if(allLessons.isChecked())
                {
                    boolean all = true;
                    intent.putExtra("all",all);
                }
                else
                {
                    boolean l1 = lesson1.isChecked();
                    boolean l2 = lesson2.isChecked();
                    boolean l3 = lesson3.isChecked();
                    boolean l4 = lesson4.isChecked();
                    boolean l5 = lesson5.isChecked();
                    boolean l6 = lesson6.isChecked();
                    boolean l7 = lesson7.isChecked();
                    boolean l8 = lesson8.isChecked();
                    boolean l9 = lesson9.isChecked();
                    boolean l10 = lesson10.isChecked();
                    boolean l11 = lesson11.isChecked();
                    boolean l12 = lesson12.isChecked();
                    boolean l13 = lesson13.isChecked();
                    boolean l14 = lesson14.isChecked();
                    boolean l15 = lesson15.isChecked();
                    boolean l16 = lesson16.isChecked();
                    boolean l17 = lesson17.isChecked();
                    boolean l18 = lesson18.isChecked();
                    boolean l19 = lesson19.isChecked();
                    boolean l20 = lesson20.isChecked();
                    boolean l21 = lesson21.isChecked();
                    boolean l22 = lesson22.isChecked();
                    boolean l23 = lesson23.isChecked();
                    intent.putExtra("l1",l1);
                    intent.putExtra("l2",l2);
                    intent.putExtra("l3",l3);
                    intent.putExtra("l4",l4);
                    intent.putExtra("l5",l5);
                    intent.putExtra("l6",l6);
                    intent.putExtra("l7",l7);
                    intent.putExtra("l8",l8);
                    intent.putExtra("l9",l9);
                    intent.putExtra("l10",l10);
                    intent.putExtra("l11",l11);
                    intent.putExtra("l12",l12);
                    intent.putExtra("l13",l13);
                    intent.putExtra("l14",l14);
                    intent.putExtra("l15",l15);
                    intent.putExtra("l16",l16);
                    intent.putExtra("l17",l17);
                    intent.putExtra("l18",l18);
                    intent.putExtra("l19",l19);
                    intent.putExtra("l20",l20);
                    intent.putExtra("l21",l21);
                    intent.putExtra("l22",l22);
                    intent.putExtra("l23",l23);
                }
                startActivity(intent);
            }
        });
    }

    private void unCheckOthers()
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
    }
}
