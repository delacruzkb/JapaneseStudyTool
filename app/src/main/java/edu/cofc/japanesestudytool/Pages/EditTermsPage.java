package edu.cofc.japanesestudytool.Pages;

import edu.cofc.japanesestudytool.R;
import edu.cofc.japanesestudytool.Term;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

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
        //TODO:add list adapter when implemented
    }
}
