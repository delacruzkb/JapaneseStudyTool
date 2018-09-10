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
//TODO: Configure Spinner Checkbox
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
    private final String[] typeSpecs = new String[]{"noun","u-verb","ru-verb","irr-verb","adjective","grammar","other"};
    private final String[] lessonSpec = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19",
            "20","21","22","23","extra"};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_single_term_dialog);

        DisplayMetrics dp = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dp);
        double factor = 0.8;
        getWindow().setLayout((dp.widthPixels),(int)(dp.heightPixels*factor));

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
        /*
        Integer temp = new Integer(originalTerm.getLesson());
        lessonDropDownBar.setSelection(adapter.getPosition(temp.toString()));
        //*/
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
                else if (englishTextBox.getText().toString().contains("\n") || japaneseTextBox.getText().toString().contains("\n")
                        || kanjiTextBox.getText().toString().contains("\n"))
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(englishTextBox.getContext());
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
                            promptThenAdd();
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
        builder.setTitle(getResources().getString(R.string.warningTitle));
        builder.setMessage(getResources().getString(R.string.overwriteMessage));
        builder.setNegativeButton(getResources().getString(R.string.cancelLabel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(getResources().getString(R.string.proceedLabel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                editedTerm = new Term();
                editedTerm.setJpns(japaneseTextBox.getText().toString());
                editedTerm.setEng(englishTextBox.getText().toString());
                editedTerm.setKanji(kanjiTextBox.getText().toString());
                editedTerm.setType(typeDropDownBar.getSelectedItem().toString());
                /*
                if(lessonDropDownBar.getSelectedItem().toString().equalsIgnoreCase("extra")) {
                    editedTerm.setLesson(0);
                }
                else
                {
                    editedTerm.setLesson(Integer.parseInt(lessonDropDownBar.getSelectedItem().toString()));
                }
                //*/
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
