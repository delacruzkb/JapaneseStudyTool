package edu.cofc.japanesestudytool.Pages;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;

import edu.cofc.japanesestudytool.AsyncTasks.DeleteAllTerms;
import edu.cofc.japanesestudytool.AsyncTasks.InsertTerms;
import edu.cofc.japanesestudytool.R;
import edu.cofc.japanesestudytool.Term;
import edu.cofc.japanesestudytool.TermDatabase;

public class HomePage extends AppCompatActivity
{
    private Button storyButton;
    private Button flashCardButton;
    private Button addTermsButton;
    private Button editTermsButton;
    private Button loadDataButton;
    private TermDatabase termDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        termDatabase = Room.databaseBuilder(this,TermDatabase.class,"terms").build();

        storyButton = findViewById(R.id.storyButton);
        storyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(storyButton.getContext(),TermsMenuPage.class);
                intent.putExtra("mode","story");
                startActivity(intent);
            }
        });


        flashCardButton = findViewById(R.id.flashCardButton);
        flashCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(flashCardButton.getContext(),TermsMenuPage.class);
                intent.putExtra("mode","flashcard");
                startActivity(intent);
            }
        });


        addTermsButton = findViewById(R.id.addTermsButton);
        addTermsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addTermsButton.getContext(),AddTermsPage.class);
                startActivity(intent);
            }
        });


        editTermsButton = findViewById(R.id.editTermsButton);
        editTermsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(editTermsButton.getContext(),EditTermsPage.class);
                startActivity(intent);
            }
        });

        //
        loadDataButton = findViewById(R.id.loadDataButton);
        loadDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                DeleteAllTerms clearDatabase = new DeleteAllTerms((termDatabase));
                clearDatabase.execute();
                InsertTerms loadInitialData = new InsertTerms(termDatabase);
                loadInitialData.execute(getInitialData());
            }
        });

    }

    /**TODO: Parsing csv code*/
    private ArrayList<Term> getInitialData()
    {
        ArrayList<Term> returnValue = new ArrayList();
        try
        {
            InputStream is = getAssets().open("jpnsInitialData.csv");
            String line ="";
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            while( (line = reader.readLine()) !=null)
            {
                String[] termData = line.split(",");
                Term term = new Term();
                term.setJpns(termData[0]);
                term.setEng(termData[1]);
                term.setKanji(termData[2]);
                term.setType(termData[3]);
                term.setLesson(termData[4]);
                term.setReqKanji(termData[5]);
                returnValue.add(term);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return returnValue;
    }
}
