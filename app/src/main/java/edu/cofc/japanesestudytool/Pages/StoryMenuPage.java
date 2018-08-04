package edu.cofc.japanesestudytool.Pages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_menu_page);

        instantiateViews();


    }

    private void instantiateViews()
    {
        //Instantiate Counters
        nounCountText = findViewById(R.id.nounCountLabel);
        adjectiveCountText = findViewById(R.id.adjectiveCountLabel);
        verbCountText = findViewById(R.id.verbCountLabel);
        grammarCountText = findViewById(R.id.grammarCountLabel);
        otherCountText = findViewById(R.id.otherCountLabel);

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

    }
}
