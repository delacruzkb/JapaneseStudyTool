package edu.cofc.japanesestudytool.Pages;

import android.arch.persistence.room.Room;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import edu.cofc.japanesestudytool.AsyncTasks.LoadInitialTerms;
import edu.cofc.japanesestudytool.R;
import edu.cofc.japanesestudytool.TermDatabase;

public class HomePage extends AppCompatActivity
{
    private Button storyButton;
    private Button flashCardButton;
    private Button addTermsButton;
    private Button editTermsButton;
    private Button loadDataButton;
    private TermDatabase termDatabase;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        termDatabase = Room.databaseBuilder(this,TermDatabase.class,"terms").build();
        spinner = findViewById(R.id.progressBar);
        spinner.setVisibility(View.INVISIBLE);
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
                Intent intent = new Intent(editTermsButton.getContext(),EditTermsMenuPage.class);
                startActivity(intent);
            }
        });

        //
        loadDataButton = findViewById(R.id.loadDataButton);
        loadDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(loadDataButton.getContext());
                builder.setMessage("Warning: will erase all data and load default data.");
                builder.setTitle("Load Initial Data");
                builder.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        spinner.setVisibility(View.VISIBLE);
                        LoadInitialTerms load = new LoadInitialTerms(getApplicationContext(),spinner);
                        load.execute();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //do nothing
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

    }
}
