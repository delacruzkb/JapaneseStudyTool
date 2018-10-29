package edu.cofc.japanesestudytool.Pages;

import edu.cofc.japanesestudytool.Adapters.CheckBoxDropDownSpinnerAdapter;
import edu.cofc.japanesestudytool.AsyncTasks.AddNewTerm;
import edu.cofc.japanesestudytool.Lessons;
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
public class AddTermsPage extends AppCompatActivity
{
    private EditText jpnsTextBox,engTextBox,kanjiTextBox;
    private Spinner typeDropDownBar,lessonDropDown;
    private final String[] typeSpecs = new String[]{"noun","u-verb","ru-verb","irregular-verb","adjective","grammar","other"};
    private CheckBox reqKanjiCheckbox;
    private Button addTermButton;
    private CheckBoxDropDownSpinnerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_terms_page);
        instantiateViews();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putIntegerArrayList("lessonDropDownAdapter",adapter.getLessonsArrayList());
        savedInstanceState.putInt("type",typeDropDownBar.getSelectedItemPosition());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        typeDropDownBar.setSelection(savedInstanceState.getInt("type"));
        adapter.setCheckedFromArrayList(savedInstanceState.getIntegerArrayList("lessonDropDownAdapter"));
        lessonDropDown.setAdapter(adapter);
    }

    private void instantiateViews()
    {
        jpnsTextBox = findViewById(R.id.addJapaneseTextBox);
        engTextBox = findViewById(R.id.addEnglishTextBox);
        kanjiTextBox = findViewById(R.id.addKanjiTextBox);

        typeDropDownBar = findViewById(R.id.addTypeDropDownBar);
        ArrayAdapter<String> tempAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,typeSpecs);
        typeDropDownBar.setAdapter(tempAdapter);

        lessonDropDown = findViewById(R.id.addLessonDropDownBar);
        adapter = new CheckBoxDropDownSpinnerAdapter(this.getApplicationContext());
        lessonDropDown.setAdapter(adapter);
        lessonDropDown.setSelection(0);

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
        boolean lessonSupplied = !(adapter.getLessonsArrayList().size()==0);
        if(engSupplied && jpnsSupplied && lessonSupplied)
        {
            final Term temp = new Term();
            if(engTextBox.getText().toString().contains("\n") || jpnsTextBox.getText().toString().contains("\n") ||
                    kanjiTextBox.getText().toString().contains("\n"))
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(engTextBox.getContext());
                builder.setTitle(getResources().getString(R.string.warningTitle));
                builder.setMessage(getResources().getString(R.string.multiLineWarning));
                builder.setNegativeButton(getResources().getString(R.string.cancelLabel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton(getResources().getString(R.string.proceedLabel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        addNewTerm(temp);
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            else
            {
                addNewTerm(temp);
            }
        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(addTermButton.getContext());
            builder.setTitle(getResources().getString(R.string.errorTitle));
            builder.setMessage(getResources().getString(R.string.fillReqFields));
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
        adapter.refreshList();
        reqKanjiCheckbox.setChecked(false);
    }

    private void addNewTerm(Term temp)
    {
        temp.setEng(engTextBox.getText().toString());
        temp.setJpns(jpnsTextBox.getText().toString());
        temp.setKanji(kanjiTextBox.getText().toString());
        temp.setType(typeDropDownBar.getSelectedItem().toString());
        Lessons lessons = new Lessons();
        lessons.setLessons(adapter.getLessonsArrayList());
        temp.setLessons(lessons);
        temp.setReqKanji(reqKanjiCheckbox.isChecked());
        resetFields();
        AddNewTerm addNewTerm = new AddNewTerm(getApplicationContext(),temp);
        addNewTerm.execute();
    }
}
