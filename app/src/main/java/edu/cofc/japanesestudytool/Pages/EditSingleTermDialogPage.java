package edu.cofc.japanesestudytool.Pages;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import edu.cofc.japanesestudytool.AsyncTasks.DeleteTerm;
import edu.cofc.japanesestudytool.AsyncTasks.InsertTerms;
import edu.cofc.japanesestudytool.R;
import edu.cofc.japanesestudytool.Term;

public class EditSingleTermDialogPage extends AppCompatActivity
{
    EditText japaneseTextBox;
    EditText englishTextBox;
    EditText kanjiTextBox;
    Spinner typeDropDownBar;
    Spinner lessonDropDownBar;
    CheckBox reqKanjiCheckBox;
    Button cancelEdit;
    Button confirmEdit;
    Term originalTerm;
    Term editedTerm;
    private final String[] typeSpecs = new String[]{"noun","u-verb","ru-verb","irregular-verb","adjective","grammar","other"};
    private final String[] lessonSpec = new String[]{"0","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19",
            "20","21","22","23"};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_single_term_dialog);

        DisplayMetrics dp = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dp);
        double factor = 0.8;
        getWindow().setLayout((int)(dp.widthPixels),(int)(dp.heightPixels*factor));

        originalTerm = (Term) getIntent().getSerializableExtra("term");
        japaneseTextBox = findViewById(R.id.editHiraganaTextBox);
        japaneseTextBox.setText(originalTerm.getJpns());
        englishTextBox = findViewById(R.id.editEnglishTextBox);
        englishTextBox.setText(originalTerm.getEng());
        kanjiTextBox = findViewById(R.id.editKanjiTextBox);
        kanjiTextBox.setText(originalTerm.getKanji());
        typeDropDownBar = findViewById(R.id.editTypeDropDownBar);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,typeSpecs);
        typeDropDownBar.setAdapter(adapter);
        typeDropDownBar.setSelection(adapter.getPosition(originalTerm.getType().toString()));
        lessonDropDownBar = findViewById(R.id.editLessonDropDownBar);
        adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,lessonSpec);
        lessonDropDownBar.setAdapter(adapter);
        Integer temp = new Integer(originalTerm.getLesson());
        lessonDropDownBar.setSelection(adapter.getPosition(temp.toString()));
        reqKanjiCheckBox = findViewById(R.id.editReqKanjiCheckBox);
        cancelEdit = findViewById(R.id.cancelEditTermButton);
        cancelEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        confirmEdit = findViewById(R.id.confirmEditTermButton);
        confirmEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(englishTextBox.getText().toString().length() ==0 || japaneseTextBox.getText().toString().length() ==0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(confirmEdit.getContext());
                    builder.setTitle("Error");
                    builder.setMessage("Please make sure that the Japanese and English fields are not blank.");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else
                {
                    promptThenAdd();
                }
            }
        });
    }


    private void promptThenAdd()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(confirmEdit.getContext());
        builder.setTitle("Confirmation");
        builder.setMessage("Are you sure you want to over-write this term?");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                editedTerm = new Term();
                editedTerm.setJpns(japaneseTextBox.getText().toString());
                editedTerm.setEng(englishTextBox.getText().toString());
                editedTerm.setKanji(kanjiTextBox.getText().toString());
                editedTerm.setType(typeDropDownBar.getSelectedItem().toString());
                editedTerm.setLesson(Integer.valueOf(lessonDropDownBar.getSelectedItem().toString()));
                editedTerm.setReqKanji(reqKanjiCheckBox.isChecked());
                ArrayList<Term> temp = new ArrayList<>();
                temp.add(editedTerm);
                DeleteTerm deleteTerm = new DeleteTerm(confirmEdit.getContext());
                deleteTerm.execute(originalTerm);
                InsertTerms insertTerms = new InsertTerms(confirmEdit.getContext());
                insertTerms.execute(temp);
                Intent intent = new Intent(confirmEdit.getContext(),EditTermsMenuPage.class);
                confirmEdit.getContext().startActivity(intent);
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}