package edu.cofc.japanesestudytool;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import edu.cofc.japanesestudytool.AsyncTasks.InsertTerms;

public class SimilarTermDialog extends AppCompatActivity
{
    ListView similarTermsList;
    Button cancel;
    Button addNewTerm;
    ArrayList<Term> similarTerms;
    Term newTerm;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.8));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similar_term_dialog);
        Intent intent = getIntent();
        newTerm = (Term) intent.getSerializableExtra("newTerm");
        similarTerms = (ArrayList<Term>)intent.getSerializableExtra("similarTerms");
        similarTermsList = findViewById(R.id.similarTermListView);
        AddTermDialogListAdapter addTermDialogListAdapter = new AddTermDialogListAdapter(similarTerms,this);
        similarTermsList.setAdapter(addTermDialogListAdapter);

        cancel = findViewById(R.id.cancelNewTermButton);
        cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder warning = new AlertDialog.Builder(cancel.getContext());
                warning.setTitle("Cancel");
                warning.setMessage("Are you sure you want to cancel?");
                warning.setNegativeButton("Go back to List", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                warning.setPositiveButton("Yes, Cancel Add.", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
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
                warning.setTitle("Cancel");
                warning.setMessage("Are you sure you want to proceed?");
                warning.setNegativeButton("Go back to List", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                warning.setPositiveButton("Yes, Add the new Term.", new DialogInterface.OnClickListener() {
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
}
