package edu.cofc.japanesestudytool.Pages;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;

import edu.cofc.japanesestudytool.Adapters.CheckBoxDropDownSpinnerAdapter;
import edu.cofc.japanesestudytool.AsyncTasks.QueryTerms;
import edu.cofc.japanesestudytool.R;
import edu.cofc.japanesestudytool.TermMenuMetrics;

public class TermsMenuPage extends AppCompatActivity
{
    private EditText nounCountText, adjectiveCountText, verbCountText, grammarCountText, otherCountText;
    private Button nounCountDecreaseButton, nounCountIncreaseButton;
    private Button adjectiveCountDecreaseButton, adjectiveCountIncreaseButton;
    private Button verbCountDecreaseButton, verbCountIncreaseButton;
    private Button grammarCountDecreaseButton, grammarCountIncreaseButton;
    private Button otherCountDecreaseButton, otherCountIncreaseButton;
    private Button confirmButton;

    private Switch showJpnsFirstSwitch, showKanjiSwitch, showKanjiFirstSwitch,
            showLessonKanjiOnlySwitch, useKanjiOnlySwitch,useLessonKanjiOnlySwitch;

    private Spinner lessonDropDown;
    private CheckBox allLessons;
    private String whichMode;
    private final int minCountLimit=0;
    private final int maxNounCountLimit=200;
    private final int maxCountLimit=100;

    private CheckBoxDropDownSpinnerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_menu_page);
        whichMode= getIntent().getStringExtra("mode");
        // segregated into private methods to ease debugging
        initializeViews();
        setCountButtonOnClickListeners();
        setCountTextOnFocusChangeListeners();
        setConfirmButtonOnClickListener();
        setSwitchOnClickListener();
        setCheckBoxOnClickListener();
        if(whichMode.equalsIgnoreCase("kanjiwriting"))
        {
            adjustViewsForKanjiWriting();
        }
    }

    private void initializeViews()
    {
        //Instantiate Counters
        nounCountText = findViewById(R.id.nounCountInput);
        adjectiveCountText = findViewById(R.id.adjectiveCountInput);
        verbCountText = findViewById(R.id.verbCountInput);
        grammarCountText = findViewById(R.id.grammarCountInput);
        otherCountText = findViewById(R.id.otherCountInput);

        //Instantiate buttons
        nounCountDecreaseButton = findViewById(R.id.nounCountDecreaseButton);
        nounCountIncreaseButton = findViewById(R.id.nounCountIncreaseButton);
        adjectiveCountDecreaseButton = findViewById(R.id.adjectiveCountDecreaseButton);
        adjectiveCountIncreaseButton = findViewById(R.id.adjectiveCountIncreaseButton);
        verbCountDecreaseButton = findViewById(R.id.verbCountDecreaseButton);
        verbCountIncreaseButton = findViewById(R.id.verbCountIncreaseButton);
        grammarCountDecreaseButton = findViewById(R.id.grammarCountDecreaseButton);
        grammarCountIncreaseButton = findViewById(R.id.grammarCountIncreaseButton);
        otherCountDecreaseButton = findViewById(R.id.otherCountDecreaseButton);
        otherCountIncreaseButton = findViewById(R.id.otherCountIncreaseButton);
        confirmButton = findViewById(R.id.confirmButton);

        //Instantiate switches
        showJpnsFirstSwitch = findViewById(R.id.showJpnsFirstSwitch);
        showKanjiSwitch = findViewById(R.id.showKanjiSwitch);
        showKanjiFirstSwitch = findViewById(R.id.showKanjiFirstSwitch);
        showLessonKanjiOnlySwitch = findViewById(R.id.showLessonKanjiOnlySwitch);
        useKanjiOnlySwitch = findViewById(R.id.useKanjiOnlySwitch);
        useLessonKanjiOnlySwitch = findViewById(R.id.useLessonKanjiOnlySwitch);
        showKanjiFirstSwitch.setVisibility(View.GONE);
        showLessonKanjiOnlySwitch.setVisibility(View.GONE);
        useKanjiOnlySwitch.setVisibility(View.GONE);
        useLessonKanjiOnlySwitch.setVisibility(View.GONE);

        //Instantiate checkboxes
        allLessons = findViewById(R.id.allLessonsCheckBox);

        //Instantiate dropdown checkboxes
        lessonDropDown = findViewById(R.id.termLessonsCheckDropDown);
        adapter = new CheckBoxDropDownSpinnerAdapter(this.getApplicationContext());
        lessonDropDown.setAdapter(adapter);
        lessonDropDown.setSelection(0);
        lessonDropDown.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(confirmButton.getContext());
        builder.setTitle(getResources().getString(R.string.warningTitle));
        builder.setMessage(getResources().getString(R.string.onBackPressedMessage));
        builder.setNegativeButton(getResources().getString(R.string.cancelLabel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton(getResources().getString(R.string.proceedLabel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(confirmButton.getContext(), HomePage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean("showJpnsFirstSwitch", showJpnsFirstSwitch.isChecked());
        savedInstanceState.putBoolean("showKanjiSwitch", showKanjiSwitch.isChecked());
        savedInstanceState.putBoolean("showKanjiFirstSwitch", showKanjiFirstSwitch.isChecked());
        savedInstanceState.putBoolean("showLessonKanjiOnlySwitch", showLessonKanjiOnlySwitch.isChecked());
        savedInstanceState.putBoolean("useKanjiOnlySwitch", useKanjiOnlySwitch.isChecked());
        savedInstanceState.putBoolean("useLessonKanjiOnlySwitch", useLessonKanjiOnlySwitch.isChecked());
        savedInstanceState.putBoolean("allLessons",allLessons.isChecked());
        savedInstanceState.putIntArray("lessonDropDownAdapter",adapter.getLessonsArray());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        showJpnsFirstSwitch.setChecked(savedInstanceState.getBoolean("showJpnsFirstSwitch"));
        showKanjiSwitch.setChecked(savedInstanceState.getBoolean("showKanjiSwitch"));
        if(showKanjiSwitch.isChecked())
        {
            showKanjiFirstSwitch.setVisibility(View.VISIBLE);
            showLessonKanjiOnlySwitch.setVisibility(View.VISIBLE);
            useKanjiOnlySwitch.setVisibility(View.VISIBLE);

            showKanjiFirstSwitch.setChecked(savedInstanceState.getBoolean("showKanjiFirstSwitch"));
            showLessonKanjiOnlySwitch.setChecked(savedInstanceState.getBoolean("showLessonKanjiOnlySwitch"));
            useKanjiOnlySwitch.setChecked(savedInstanceState.getBoolean("useKanjiOnlySwitch"));
            if(useKanjiOnlySwitch.isChecked())
            {
                useLessonKanjiOnlySwitch.setVisibility(View.VISIBLE);
                showLessonKanjiOnlySwitch.setVisibility(View.INVISIBLE);
                showLessonKanjiOnlySwitch.setChecked(savedInstanceState.getBoolean("showLessonKanjiOnlySwitch"));
            }
            else
            {
                useLessonKanjiOnlySwitch.setVisibility(View.GONE);
                useLessonKanjiOnlySwitch.setChecked(false);
            }
        }
        else
        {
            showKanjiFirstSwitch.setVisibility(View.GONE);
            showLessonKanjiOnlySwitch.setVisibility(View.GONE);
            useKanjiOnlySwitch.setVisibility(View.GONE);
            useLessonKanjiOnlySwitch.setVisibility(View.GONE);
            showKanjiFirstSwitch.setChecked(false);
            showLessonKanjiOnlySwitch.setChecked(false);
            useKanjiOnlySwitch.setChecked(false);
            useLessonKanjiOnlySwitch.setChecked(false);
        }

        allLessons.setChecked(savedInstanceState.getBoolean("allLessons"));
        if(allLessons.isChecked())
        {
            lessonDropDown.setVisibility(View.INVISIBLE);
            adapter.refreshList();
        }
        else
        {
            lessonDropDown.setVisibility(View.VISIBLE);
            adapter.setCheckedFromArray(savedInstanceState.getIntArray("lessonDropDownAdapter"));
            lessonDropDown.setAdapter(adapter);
        }
    }

    private void setCountButtonOnClickListeners()
    {
        nounCountDecreaseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                makeCountsValid();
                int count = Integer.parseInt(nounCountText.getText().toString());
                if(count > minCountLimit)
                {
                    nounCountText.setText((count-1) + "");
                }
            }
        });

        nounCountIncreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                makeCountsValid();
                int count = Integer.parseInt(nounCountText.getText().toString());
                if(count < maxNounCountLimit)
                {
                    nounCountText.setText((count+1) + "");
                }
            }
        });

        adjectiveCountDecreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                makeCountsValid();
                int count = Integer.parseInt(adjectiveCountText.getText().toString());
                if(count > minCountLimit)
                {
                    adjectiveCountText.setText((count-1) + "");
                }
            }
        });

        adjectiveCountIncreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                makeCountsValid();
                int count = Integer.parseInt(adjectiveCountText.getText().toString());
                if(count < maxCountLimit)
                {
                    adjectiveCountText.setText((count+1) + "");
                }
            }
        });

        verbCountDecreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCountsValid();
                int count = Integer.parseInt(verbCountText.getText().toString());
                if(count > minCountLimit)
                {
                    verbCountText.setText((count-1) + "");
                }
            }
        });

        verbCountIncreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCountsValid();
                int count = Integer.parseInt(verbCountText.getText().toString());
                if(count < maxCountLimit)
                {
                    verbCountText.setText((count+1) + "");
                }
            }
        });

        grammarCountDecreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCountsValid();
                int count = Integer.parseInt(grammarCountText.getText().toString());
                if(count > minCountLimit)
                {
                    grammarCountText.setText((count-1) + "");
                }
            }
        });

        grammarCountIncreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCountsValid();
                int count = Integer.parseInt(grammarCountText.getText().toString());
                if(count < maxCountLimit)
                {
                    grammarCountText.setText((count+1) + "");
                }
            }
        });

        otherCountDecreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCountsValid();
                int count = Integer.parseInt(otherCountText.getText().toString());
                if(count > minCountLimit)
                {
                    otherCountText.setText((count-1) + "");
                }
            }
        });

        otherCountIncreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCountsValid();
                int count = Integer.parseInt(otherCountText.getText().toString());
                if(count <maxCountLimit )
                {
                    otherCountText.setText((count+1) + "");
                }
            }
        });
    }

    private void setCountTextOnFocusChangeListeners()
    {
        View.OnFocusChangeListener listener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                makeCountsValid();
            }
        };
        nounCountText.setOnFocusChangeListener(listener);

        adjectiveCountText.setOnFocusChangeListener(listener);

        verbCountText.setOnFocusChangeListener(listener);

        grammarCountText.setOnFocusChangeListener(listener);

        otherCountText.setOnFocusChangeListener(listener);
    }

    private void setSwitchOnClickListener()
    {
        showKanjiSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!showKanjiSwitch.isChecked())
                {
                    showKanjiFirstSwitch.setVisibility(View.GONE);
                    showLessonKanjiOnlySwitch.setVisibility(View.GONE);
                    useKanjiOnlySwitch.setVisibility(View.GONE);
                    useLessonKanjiOnlySwitch.setVisibility(View.GONE);
                    showKanjiFirstSwitch.setChecked(false);
                    showLessonKanjiOnlySwitch.setChecked(false);
                    useKanjiOnlySwitch.setChecked(false);
                    useLessonKanjiOnlySwitch.setChecked(false);
                }
                else
                {
                    showKanjiFirstSwitch.setVisibility(View.VISIBLE);
                    showLessonKanjiOnlySwitch.setVisibility(View.VISIBLE);
                    useKanjiOnlySwitch.setVisibility(View.VISIBLE);
                }
            }
        });

        useKanjiOnlySwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!useKanjiOnlySwitch.isChecked())
                {
                    useLessonKanjiOnlySwitch.setVisibility(View.GONE);
                    useLessonKanjiOnlySwitch.setChecked(false);
                    showLessonKanjiOnlySwitch.setVisibility(View.VISIBLE);
                }
                else
                {
                    useLessonKanjiOnlySwitch.setVisibility(View.VISIBLE);
                    showLessonKanjiOnlySwitch.setVisibility(View.INVISIBLE);
                    showLessonKanjiOnlySwitch.setChecked(false);
                }
            }
        });

        showJpnsFirstSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!showJpnsFirstSwitch.isChecked())
                {
                    showKanjiFirstSwitch.setChecked(false);
                }
            }
        });

    }

    private void setCheckBoxOnClickListener()
    {

        allLessons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(allLessons.isChecked())
                {
                    lessonDropDown.setVisibility(View.INVISIBLE);
                    adapter.refreshList();
                }
                else
                {
                    lessonDropDown.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void adjustViewsForKanjiWriting()
    {
        LinearLayout layout = findViewById(R.id.grammarCountLayout);
        grammarCountText.setText("0");
        layout.setVisibility(View.GONE);

        showKanjiSwitch.setVisibility(View.GONE);
        showKanjiFirstSwitch.setVisibility(View.GONE);
        showLessonKanjiOnlySwitch.setVisibility(View.GONE);
        useKanjiOnlySwitch.setChecked(true);
        useKanjiOnlySwitch.setVisibility(View.GONE);
        useLessonKanjiOnlySwitch.setVisibility(View.VISIBLE);
    }
    private void setConfirmButtonOnClickListener()
    {
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TermMenuMetrics metrics = new TermMenuMetrics();
                metrics.setMode(whichMode);
                makeCountsValid();
                metrics.setNounCount(Integer.parseInt(nounCountText.getText().toString()));
                metrics.setAdjectiveCount(Integer.parseInt(adjectiveCountText.getText().toString()));
                metrics.setVerbCount( Integer.parseInt(verbCountText.getText().toString()));
                metrics.setGrammarCount(Integer.parseInt(grammarCountText.getText().toString()));
                metrics.setOtherCount(Integer.parseInt(otherCountText.getText().toString()));

                metrics.setShowJpnsFirst(showJpnsFirstSwitch.isChecked());
                metrics.setShowKanji(showKanjiSwitch.isChecked());
                metrics.setShowKanjiFirst(showKanjiFirstSwitch.isChecked());
                metrics.setShowLessonKanjiOnly(showLessonKanjiOnlySwitch.isChecked());
                metrics.setUseKanjiOnly(useKanjiOnlySwitch.isChecked());
                metrics.setUseLessonKanjiOnly(useLessonKanjiOnlySwitch.isChecked());
                metrics.setAllTerms(allLessons.isChecked());
                //Gather all lessons selected
                int[] lessons=null;
                if(!allLessons.isChecked())
                {
                    lessons = adapter.getLessonsArray();
                }
                metrics.setLessons(lessons);
                QueryTerms queryTerms = new QueryTerms(metrics, confirmButton.getContext());
                queryTerms.execute();
            }
        });
    }

    private void makeCountsValid()
    {
        if(nounCountText.getText().toString().equalsIgnoreCase(""))
        {
            nounCountText.setText("0");
        }
        else if(nounCountText.getText().toString().length() >6)
        {
            nounCountText.setText(Integer.toString(maxNounCountLimit));
        }
        else if(Integer.parseInt(nounCountText.getText().toString()) >maxCountLimit)
        {
            nounCountText.setText(Integer.toString(maxNounCountLimit));
        }

        if((adjectiveCountText.getText().toString().equalsIgnoreCase("")))
        {
            adjectiveCountText.setText("0");
        }
        else if(adjectiveCountText.getText().toString().length() >6)
        {
            adjectiveCountText.setText(Integer.toString(maxCountLimit));
        }
        else if(Integer.parseInt(adjectiveCountText.getText().toString()) >maxCountLimit)
        {
            adjectiveCountText.setText(Integer.toString(maxCountLimit));
        }

        if((verbCountText.getText().toString().equalsIgnoreCase("")))
        {
            verbCountText.setText("0");
        }
        else if(verbCountText.getText().toString().length() >6)
        {
            verbCountText.setText(Integer.toString(maxCountLimit));
        }
        else if(Integer.parseInt( verbCountText.getText().toString()) >maxCountLimit)
        {
            verbCountText.setText(Integer.toString(maxCountLimit));
        }

        if((grammarCountText.getText().toString().equalsIgnoreCase("")))
        {
            grammarCountText.setText("0");
        }
        else if(grammarCountText.getText().toString().length() >6)
        {
            grammarCountText.setText(Integer.toString(maxCountLimit));
        }
        else if(Integer.parseInt(grammarCountText.getText().toString()) >maxCountLimit)
        {
            grammarCountText.setText(Integer.toString(maxCountLimit));
        }

        if((otherCountText.getText().toString().equalsIgnoreCase("")))
        {
            otherCountText.setText("0");
        }
        else if(otherCountText.getText().toString().length() >6)
        {
            otherCountText.setText(Integer.toString(maxCountLimit));
        }
        else if(Integer.parseInt(otherCountText.getText().toString()) >maxCountLimit)
        {
            otherCountText.setText(Integer.toString(maxCountLimit));
        }
    }
}
