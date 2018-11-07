package edu.cofc.japanesestudytool.Pages;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import edu.cofc.japanesestudytool.AsyncTasks.LoadInitialTerms;
import edu.cofc.japanesestudytool.Pages.DatabaseEditing.AddTermsPage;
import edu.cofc.japanesestudytool.Pages.DatabaseEditing.EditTermsMenuPage;
import edu.cofc.japanesestudytool.Pages.Study.TermsMenuPage;
import edu.cofc.japanesestudytool.R;

public class HomePage extends AppCompatActivity
{
    private Button studyButton, addTermsButton,editTermsButton,loadDataButton;
    private ProgressBar spinner;
    private LinearLayout menuButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        menuButtons=findViewById(R.id.menuButtonsLayout);

        spinner = findViewById(R.id.progressBar);
        spinner.setVisibility(View.GONE);

        studyButton = findViewById(R.id.studyButton);
        studyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(studyButton.getContext(),TermsMenuPage.class);
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


        loadDataButton = findViewById(R.id.loadDataButton);
        loadDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(loadDataButton.getContext());
                builder.setMessage(R.string.loadDataWarning);
                builder.setTitle(R.string.loadDataLabel);
                builder.setPositiveButton(R.string.proceedLabel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        menuButtons.setVisibility(View.GONE);
                        spinner.setVisibility(View.VISIBLE);
                        LoadInitialTerms load = new LoadInitialTerms(getApplicationContext(),spinner,menuButtons);
                        load.execute();
                    }
                });
                builder.setNegativeButton(getResources().getString(R.string.cancelLabel), new DialogInterface.OnClickListener() {
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
