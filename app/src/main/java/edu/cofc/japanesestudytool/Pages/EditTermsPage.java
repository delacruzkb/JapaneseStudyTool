package edu.cofc.japanesestudytool.Pages;

import edu.cofc.japanesestudytool.EditTermListAdapter;
import edu.cofc.japanesestudytool.R;
import edu.cofc.japanesestudytool.Term;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
//TODO: configure refresh after edit
public class EditTermsPage extends AppCompatActivity
{
    ListView editTermsListView;
    ArrayList<Term> terms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_terms_page);
        Intent intent = getIntent();
        terms = (ArrayList<Term>) intent.getSerializableExtra("terms");

        editTermsListView = findViewById(R.id.editTermsListView);
        EditTermListAdapter editTermListAdapter = new EditTermListAdapter(terms,this);
        editTermsListView.setAdapter(editTermListAdapter);
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(editTermsListView.getContext());
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
                Intent intent = new Intent(editTermsListView.getContext(), EditTermsMenuPage.class);
                startActivity(intent);
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
