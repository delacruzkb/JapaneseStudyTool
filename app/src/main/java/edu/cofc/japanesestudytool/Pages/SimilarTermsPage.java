package edu.cofc.japanesestudytool.Pages;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.cofc.japanesestudytool.AddTermListAdapter;
import edu.cofc.japanesestudytool.AsyncTasks.InsertTerms;
import edu.cofc.japanesestudytool.R;
import edu.cofc.japanesestudytool.Term;

public class SimilarTermsPage extends AppCompatActivity
{
    ListView similarTermsList;
    Button cancel;
    Button addNewTerm;
    ArrayList<Term> similarTerms;
    Term newTerm;
    EditText newEngTextBox;
    EditText newJpnsTextBox;
    EditText newKanjiTextBox;
    TextView newLessonTextBox;
    TextView newTypeTextBox;
    TextView newReqKanjiTextBox;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similar_terms_page);

        Intent intent = getIntent();
        newTerm = (Term) intent.getSerializableExtra("newTerm");
        similarTerms = (ArrayList<Term>)intent.getSerializableExtra("similarTerms");
        instantiateViews();
        loadNewTermOntoDisplay(newTerm);

    }

    private void instantiateViews()
    {
        newEngTextBox = findViewById(R.id.newTermEnglishTextBox);
        newEngTextBox.setEnabled(false);
        newJpnsTextBox = findViewById(R.id.newTermHiraganaTextBox);
        newJpnsTextBox.setEnabled(false);
        newKanjiTextBox = findViewById(R.id.newTermKanjiTextBox);
        newKanjiTextBox.setEnabled(false);
        newLessonTextBox = findViewById(R.id.newTermLessonTextBox);
        newTypeTextBox = findViewById(R.id.newTermTypeTextBox);
        newReqKanjiTextBox = findViewById(R.id.newTermReqKanjiTextBox);
        similarTermsList = findViewById(R.id.similarTermListView);
        AddTermListAdapter addTermListAdapter = new AddTermListAdapter(similarTerms,this);
        similarTermsList.setAdapter(addTermListAdapter);
        cancel = findViewById(R.id.cancelNewTermButton);
        cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder warning = new AlertDialog.Builder(cancel.getContext());
                warning.setTitle(getResources().getString(R.string.warningTitle));
                warning.setMessage(getResources().getString(R.string.onBackPressedMessage));
                warning.setPositiveButton(getResources().getString(R.string.proceedLabel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }
                });
                warning.setNegativeButton(getResources().getString(R.string.cancelLabel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = warning.create();
                dialog.show();
            }
        });

        addNewTerm = findViewById(R.id.proceedNewTermButton);
        addNewTerm.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder warning = new AlertDialog.Builder(cancel.getContext());
                warning.setTitle(getResources().getString(R.string.warningTitle));
                warning.setMessage(getResources().getString(R.string.addNewTermMessage));
                warning.setNegativeButton(getResources().getString(R.string.cancelLabel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                warning.setPositiveButton(getResources().getString(R.string.addNewTerm), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        InsertTerms insertTerms = new InsertTerms(addNewTerm.getContext());
                        ArrayList<Term> temp = new ArrayList<>();
                        temp.add(newTerm);
                        insertTerms.execute(temp);
                        onBackPressed();
                    }
                });
                AlertDialog dialog = warning.create();
                dialog.show();
            }
        });
    }
    private void loadNewTermOntoDisplay(Term term)
    {
        newEngTextBox.setText(term.getEng());
        newJpnsTextBox.setText(term.getJpns());
        newKanjiTextBox.setText(term.getKanji());
        newTypeTextBox.setText(term.getType());
        Integer tempInt = new Integer(term.getLesson());
        newLessonTextBox.setText(tempInt.toString());
        if(!term.isReqKanji())
        {
            newReqKanjiTextBox.setVisibility(View.INVISIBLE);
        }
    }
}
