package edu.cofc.japanesestudytool.Pages;

import edu.cofc.japanesestudytool.AsyncTasks.AddNewTerm;
import edu.cofc.japanesestudytool.R;
import edu.cofc.japanesestudytool.Term;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class AddTermsPage extends AppCompatActivity
{
    EditText jpnsTextBox;
    EditText engTextBox;
    EditText kanjiTextBox;
    Spinner typeDropDownBar;
    Spinner lessonDropDownBar;
    private final String[] typeSpecs = new String[]{"noun","u-verb","ru-verb","irregular-verb","adjective","grammar","other"};
    private final String[] lessonSpec = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19",
            "20","21","22","23","extra"};
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

        typeDropDownBar = findViewById(R.id.addTypeDropDownBar);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,typeSpecs);
        typeDropDownBar.setAdapter(adapter);
        lessonDropDownBar = findViewById(R.id.addLessonDropDownBar);
        adapter =  new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,lessonSpec);
        lessonDropDownBar.setAdapter(adapter);
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
        if(engSupplied && jpnsSupplied)
        {
            Term temp = new Term();
            temp.setEng(engTextBox.getText().toString());
            temp.setJpns(jpnsTextBox.getText().toString());
            temp.setKanji(kanjiTextBox.getText().toString());
            temp.setType(typeDropDownBar.getSelectedItem().toString());
            if(lessonDropDownBar.getSelectedItem().toString().equalsIgnoreCase("extra"))
            {
                temp.setLesson(0);
            }
            else
            {
                temp.setLesson(Integer.valueOf(lessonDropDownBar.getSelectedItem().toString()));
            }
            temp.setReqKanji(reqKanjiCheckbox.isChecked());
            resetFields();
            AddNewTerm addNewTerm = new AddNewTerm(getApplicationContext(),temp);
            addNewTerm.execute();
        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(addTermButton.getContext());
            builder.setTitle(getResources().getString(R.string.errorTitle));
            builder.setMessage(getResources().getString(R.string.fillEnglishAndJapanese));
            builder.setPositiveButton(getResources().getString(R.string.okLabel), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(jpnsTextBox.getContext());
        builder.setTitle("Warning");
        builder.setMessage("Return to the menu??");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("Return", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(jpnsTextBox.getContext(), HomePage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
    private void resetFields()
    {
        jpnsTextBox.setText("");
        engTextBox.setText("");
        kanjiTextBox.setText("");
        reqKanjiCheckbox.setChecked(false);
    }
}
