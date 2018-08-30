package edu.cofc.japanesestudytool.Pages;

import edu.cofc.japanesestudytool.AsyncTasks.AddNewTerm;
import edu.cofc.japanesestudytool.R;
import edu.cofc.japanesestudytool.Term;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class AddTermsPage extends AppCompatActivity
{
    EditText jpnsTextBox;
    EditText engTextBox;
    EditText kanjiTextBox;
    TextView verbLabel;
    RadioButton nounRadioButton;
    RadioButton verbRadioButton;
    RadioButton adjectiveRadioButton;
    RadioButton grammarRadioButton;
    RadioButton otherRadioButton;
    RadioButton uRadioButton;
    RadioButton ruRadioButton;
    RadioButton irregularRadioButton;
    EditText lessonTextBox;
    CheckBox reqKanjiCheckbox;
    Button addTermButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_terms_page);
        instantiateViews();
    }

    private void instantiateViews()
    {
        jpnsTextBox = findViewById(R.id.addJapaneseTextBox);
        engTextBox = findViewById(R.id.addEnglishTextBox);
        kanjiTextBox = findViewById(R.id.addKanjiTextBox);
        verbLabel = findViewById(R.id.addTypeSpecialLabel);
        verbLabel.setVisibility(View.INVISIBLE);
        nounRadioButton = findViewById(R.id.nounRadioButton);
        nounRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nounRadioButton.setChecked(true);
                verbRadioButton.setChecked(false);
                adjectiveRadioButton.setChecked(false);
                grammarRadioButton.setChecked(false);
                otherRadioButton.setChecked(false);
                uRadioButton.setVisibility(View.INVISIBLE);
                ruRadioButton.setVisibility(View.INVISIBLE);
                irregularRadioButton.setVisibility(View.INVISIBLE);
                verbLabel.setVisibility(View.INVISIBLE);
            }
        });
        verbRadioButton = findViewById(R.id.verbRadioButton);
        verbRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nounRadioButton.setChecked(false);
                verbRadioButton.setChecked(true);
                adjectiveRadioButton.setChecked(false);
                grammarRadioButton.setChecked(false);

                uRadioButton.setVisibility(View.VISIBLE);
                ruRadioButton.setVisibility(View.VISIBLE);
                irregularRadioButton.setVisibility(View.VISIBLE);
                verbLabel.setVisibility(View.VISIBLE);
            }
        });
        adjectiveRadioButton = findViewById(R.id.adjectiveRadioButton);
        adjectiveRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nounRadioButton.setChecked(false);
                verbRadioButton.setChecked(false);
                adjectiveRadioButton.setChecked(true);
                grammarRadioButton.setChecked(false);
                otherRadioButton.setChecked(false);
                uRadioButton.setVisibility(View.INVISIBLE);
                ruRadioButton.setVisibility(View.INVISIBLE);
                irregularRadioButton.setVisibility(View.INVISIBLE);
                verbLabel.setVisibility(View.INVISIBLE);
            }
        });
        grammarRadioButton = findViewById(R.id.grammarRadioButton);
        grammarRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nounRadioButton.setChecked(false);
                verbRadioButton.setChecked(false);
                adjectiveRadioButton.setChecked(false);
                grammarRadioButton.setChecked(true);
                otherRadioButton.setChecked(false);
                uRadioButton.setVisibility(View.INVISIBLE);
                ruRadioButton.setVisibility(View.INVISIBLE);
                irregularRadioButton.setVisibility(View.INVISIBLE);
                verbLabel.setVisibility(View.INVISIBLE);

            }
        });
        otherRadioButton = findViewById(R.id.otherRadioButton);
        otherRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nounRadioButton.setChecked(false);
                verbRadioButton.setChecked(false);
                adjectiveRadioButton.setChecked(false);
                grammarRadioButton.setChecked(false);
                otherRadioButton.setChecked(true);
                uRadioButton.setVisibility(View.INVISIBLE);
                ruRadioButton.setVisibility(View.INVISIBLE);
                irregularRadioButton.setVisibility(View.INVISIBLE);
                verbLabel.setVisibility(View.INVISIBLE);
            }
        });

        uRadioButton = findViewById(R.id.uRadioButton);
        uRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uRadioButton.setChecked(true);
                ruRadioButton.setChecked(false);
                irregularRadioButton.setChecked(false);
            }
        });
        ruRadioButton = findViewById(R.id.rURadioButton);
        ruRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uRadioButton.setChecked(false);
                ruRadioButton.setChecked(true);
                irregularRadioButton.setChecked(false);
            }
        });
        irregularRadioButton = findViewById(R.id.irregularRadioButton);
        irregularRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uRadioButton.setChecked(false);
                ruRadioButton.setChecked(false);
                irregularRadioButton.setChecked(true);
            }
        });
        uRadioButton.setVisibility(View.INVISIBLE);
        ruRadioButton.setVisibility(View.INVISIBLE);
        irregularRadioButton.setVisibility(View.INVISIBLE);

        lessonTextBox = findViewById(R.id.addLessonTextBox);

        reqKanjiCheckbox = findViewById(R.id.addReqKanjiCheckBox);

        addTermButton = findViewById(R.id.addNewTermButton);
        addTermButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                actionForAddNewTerm();
            }
        });
    }


    private void actionForAddNewTerm()
    {
        boolean engSupplied =!(engTextBox.getText().toString() == null || engTextBox.getText().toString().equalsIgnoreCase(""));
        boolean jpnsSupplied=!(jpnsTextBox.getText().toString() == null || jpnsTextBox.getText().toString().equalsIgnoreCase(""));
        boolean lessonSupplied =!(lessonTextBox.getText().toString() == null || lessonTextBox.getText().toString().equalsIgnoreCase(""));
        if(engSupplied && jpnsSupplied && lessonSupplied)
        {
            Term temp = new Term();
            temp.setEng(engTextBox.getText().toString());
            temp.setJpns(jpnsTextBox.getText().toString());
            temp.setKanji(kanjiTextBox.getText().toString());
            if(nounRadioButton.isChecked())
            {
                temp.setType("noun");
            }
            else if(verbRadioButton.isChecked())
            {
                temp.setType("verb");
                if(uRadioButton.isChecked())
                {
                    temp.setType("u-verb");
                }
                else if(ruRadioButton.isChecked())
                {
                    temp.setType("ru-verb");
                }
                else if(irregularRadioButton.isChecked())
                {
                    temp.setType("irr-verb");
                }
            }
            else if(adjectiveRadioButton.isChecked())
            {
                temp.setType("adjective");
            }
            else if(grammarRadioButton.isChecked())
            {
                temp.setType("grammar");
            }
            else if(otherRadioButton.isChecked())
            {
                temp.setType("other");
            }

            int lesson = Integer.parseInt(lessonTextBox.getText().toString());
            if(lesson>23)
            {
                lesson = 0;
            }
            temp.setLesson(lesson);
            temp.setReqKanji(reqKanjiCheckbox.isChecked());
            resetFields();
            AddNewTerm addNewTerm = new AddNewTerm(getApplicationContext(),temp);
            addNewTerm.execute();
        }
        else
        {
            String message="";
            if(!engSupplied)
            {
                message+= "English\n";
            }
            if(!jpnsSupplied)
            {
                message+="Japanese\n";
            }
            if(!lessonSupplied)
            {
                message+= "Lesson";
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(addTermButton.getContext());
            builder.setTitle("ERROR");
            builder.setMessage("Please fix the following fields\n"+message);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    private void resetFields()
    {
        jpnsTextBox.setText("");
        engTextBox.setText("");
        kanjiTextBox.setText("");
        nounRadioButton.setChecked(true);
        verbRadioButton.setChecked(false);
        adjectiveRadioButton.setChecked(false);
        otherRadioButton.setChecked(false);
        grammarRadioButton.setChecked(false);
        ruRadioButton.setVisibility(View.INVISIBLE);
        uRadioButton.setVisibility(View.INVISIBLE);
        irregularRadioButton.setVisibility(View.INVISIBLE);
        lessonTextBox.setText("");
        reqKanjiCheckbox.setChecked(false);
    }
}
