package edu.cofc.japanesestudytool.Pages;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.cofc.japanesestudytool.R;

public class HomePage extends AppCompatActivity
{
    Button storyButton;
    Button flashCardButton;
    Button addTermsButton;
    Button editTermsButton;
    Button loadDataButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        /**Create storyButton
         * A button that will take you to a page with the following:
         * 1) A menu with options that will help generate subjects for the story
         * 2) a button that will take you to a separate page listing the subjects
         *      and an area with google translate for error checking
         *
         * */
        storyButton.findViewById(R.id.storyButton);
        storyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(storyButton.getContext(),StoryPage.class);
                startActivity(intent);
            }
        });

        /**Create flashCardButton
         * A button that will take you to the page for 1 by 1 term viewing via flash cards
         * */
        flashCardButton.findViewById(R.id.flashCardButton);
        flashCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(flashCardButton.getContext(), FlashCardPage.class);
                startActivity(intent);
            }
        });

        /**Create addTermsButton
         * A button that will take you to the page for adding new entries
         * */
        addTermsButton.findViewById(R.id.addTermsButton);
        addTermsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addTermsButton.getContext(),AddTermsPage.class);
                startActivity(intent);
            }
        });

        /**Create edit termsButton
         * A button that will take you to the page for editing any information within the database
         * */
        editTermsButton.findViewById(R.id.editTermsButton);
        editTermsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(editTermsButton.getContext(),EditTermsPage.class);
                startActivity(intent);
            }
        });

        /**Create loadDataButton
         * A button that will clear the database and re-load all of the initial data it had before.
         *  + Will prompt user for confirmation
         * */
        loadDataButton.findViewById(R.id.loadDataButton);
        loadDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
