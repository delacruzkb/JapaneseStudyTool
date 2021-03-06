package edu.cofc.japanesestudytool.Pages.Study;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;

import java.util.ArrayList;

import edu.cofc.japanesestudytool.Adapters.CheckBoxDropDownSpinnerAdapter;
import edu.cofc.japanesestudytool.AsyncTasks.QueryTerms;
import edu.cofc.japanesestudytool.Pages.HomePage;
import edu.cofc.japanesestudytool.R;
import edu.cofc.japanesestudytool.TermMenuMetrics;

public class TermsMenuPage extends AppCompatActivity
{
    private EditText nounCountText, adjectiveCountText, verbCountText, grammarCountText, otherCountText;
    private Button nounMaxCountButton;
    private Button adjectiveMaxCountButton;
    private Button verbMaxCountButton;
    private Button grammarMaxCountButton;
    private Button otherMaxCountButton;
    private Button confirmButton;

    private Switch useSpecificCountSwitch,showJpnsFirstSwitch, showKanjiFirstSwitch,
            useKanjiOnlySwitch,useLessonKanjiOnlySwitch;
    private TermMenuMetrics metrics;
    private Spinner modeDropDown,lessonDropDown;
    private CheckBox allLessons;
    private String whichMode;
    private final int minCountLimit=0;
    private final int maxCountLimit=9999;

    private CheckBoxDropDownSpinnerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_menu_page);
        // segregated into private methods to ease debugging
        initializeViews();
        setCountButtonOnClickListeners();
        setCountTextOnFocusChangeListeners();
        setConfirmButtonOnClickListener();
        setSwitchOnClickListener();
        setCheckBoxOnClickListener();
    }

    private void initializeViews()
    {
        metrics = new TermMenuMetrics();
        //Instantiate Counters
        nounCountText = findViewById(R.id.nounCountInput);
        adjectiveCountText = findViewById(R.id.adjectiveCountInput);
        verbCountText = findViewById(R.id.verbCountInput);
        grammarCountText = findViewById(R.id.grammarCountInput);
        otherCountText = findViewById(R.id.otherCountInput);

        //Instantiate buttons
        nounMaxCountButton = findViewById(R.id.nounMaxCountButton);
        adjectiveMaxCountButton = findViewById(R.id.adjectiveMaxCountButton);
        verbMaxCountButton = findViewById(R.id.verbMaxCountButton);
        grammarMaxCountButton = findViewById(R.id.grammarMaxCountButton);
        otherMaxCountButton = findViewById(R.id.otherMaxCountButton);
        confirmButton = findViewById(R.id.confirmButton);

        //Instantiate switches
        showJpnsFirstSwitch = findViewById(R.id.showJpnsFirstSwitch);
        showKanjiFirstSwitch = findViewById(R.id.showKanjiFirstSwitch);
        useKanjiOnlySwitch = findViewById(R.id.useKanjiOnlySwitch);
        useLessonKanjiOnlySwitch = findViewById(R.id.useLessonKanjiOnlySwitch);
        useLessonKanjiOnlySwitch.setVisibility(View.INVISIBLE);
        useSpecificCountSwitch = findViewById(R.id.useSpecificCountSwitch);
        hideCounters();

        //Instantiate checkbox
        allLessons = findViewById(R.id.allLessonsCheckBox);

        //Instantiate spinner
        modeDropDown = findViewById(R.id.modeDropDown);
        final String[] items = {getResources().getString(R.string.flashCardModeText),
                            getResources().getString(R.string.kanjiStrokeModeText)};
        final ArrayAdapter<String> modeAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,items);
        modeDropDown.setAdapter(modeAdapter);
        modeDropDown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                whichMode = parent.getItemAtPosition(position).toString();
                if(whichMode.equalsIgnoreCase(modeDropDown.getContext().getResources().getString(R.string.kanjiStrokeModeText)))
                {
                    hideExtraSwitches();
                }
                else
                {
                    showExtraSwitches();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

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

        EditText count;
        count = findViewById(R.id.nounCountInput);
        savedInstanceState.putString("nci",count.getText().toString());

        count = findViewById(R.id.verbCountInput);
        savedInstanceState.putString("vci",count.getText().toString());

        count = findViewById(R.id.adjectiveCountInput);
        savedInstanceState.putString("aci",count.getText().toString());

        count = findViewById(R.id.grammarCountInput);
        savedInstanceState.putString("gci",count.getText().toString());

        count = findViewById(R.id.otherCountInput);
        savedInstanceState.putString("oci",count.getText().toString());

        LinearLayout layout;
        layout = findViewById(R.id.nounCountLayout);
        savedInstanceState.putInt("ncl",layout.getVisibility());

        layout = findViewById(R.id.adjectiveCountLayout);
        savedInstanceState.putInt("acl",layout.getVisibility());

        layout = findViewById(R.id.verbCountLayout);
        savedInstanceState.putInt("vcl",layout.getVisibility());

        layout = findViewById(R.id.grammarCountLayout);
        savedInstanceState.putInt("gcl",layout.getVisibility());

        layout = findViewById(R.id.otherCountLayout);
        savedInstanceState.putInt("ocl",layout.getVisibility());

        savedInstanceState.putBoolean("useSpecificCountSwitch", useSpecificCountSwitch.isChecked());

        savedInstanceState.putBoolean("useKanjiOnlySwitch", useKanjiOnlySwitch.isChecked());
        savedInstanceState.putBoolean("useLessonKanjiOnlySwitch", useLessonKanjiOnlySwitch.isChecked());
        savedInstanceState.putBoolean("allLessons",allLessons.isChecked());
        savedInstanceState.putIntegerArrayList("lessonDropDownAdapter",adapter.getLessonsArrayList());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);

        EditText count;
        count = findViewById(R.id.nounCountInput);
        count.setText(savedInstanceState.getString("nci"));

        count = findViewById(R.id.verbCountInput);
        count.setText(savedInstanceState.getString("vci"));

        count = findViewById(R.id.adjectiveCountInput);
        count.setText(savedInstanceState.getString("aci"));

        count = findViewById(R.id.grammarCountInput);
        count.setText(savedInstanceState.getString("gci"));

        count = findViewById(R.id.otherCountInput);
        count.setText(savedInstanceState.getString("oci"));

        LinearLayout layout;
        layout = findViewById(R.id.nounCountLayout);
        layout.setVisibility(savedInstanceState.getInt("ncl"));

        layout = findViewById(R.id.adjectiveCountLayout);
        layout.setVisibility(savedInstanceState.getInt("acl"));

        layout = findViewById(R.id.verbCountLayout);
        layout.setVisibility(savedInstanceState.getInt("vcl"));

        layout = findViewById(R.id.grammarCountLayout);
        layout.setVisibility(savedInstanceState.getInt("gcl"));

        layout = findViewById(R.id.otherCountLayout);
        layout.setVisibility(savedInstanceState.getInt("ocl"));

        useSpecificCountSwitch.setChecked(savedInstanceState.getBoolean("useSpecificCountSwitch"));

        showJpnsFirstSwitch.setChecked(savedInstanceState.getBoolean("showJpnsFirstSwitch"));
        if(useKanjiOnlySwitch.isChecked())
        {
            useLessonKanjiOnlySwitch.setVisibility(View.VISIBLE);
        }
        else
        {
            useLessonKanjiOnlySwitch.setVisibility(View.GONE);
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
            adapter.setCheckedFromArrayList(savedInstanceState.getIntegerArrayList("lessonDropDownAdapter"));
            lessonDropDown.setAdapter(adapter);
        }
    }

    private void setCountButtonOnClickListeners()
    {

        nounMaxCountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nounCountText.setText(maxCountLimit +"");
            }
        });

        adjectiveMaxCountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                adjectiveCountText.setText(maxCountLimit +"");
            }
        });

        verbMaxCountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                verbCountText.setText(maxCountLimit +"");
            }
        });

        grammarMaxCountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                grammarCountText.setText(maxCountLimit +"");
            }
        });


        otherMaxCountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                otherCountText.setText(maxCountLimit +"");
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
        useSpecificCountSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!useSpecificCountSwitch.isChecked())
                {
                    hideCounters();
                }
                else
                {
                    showCounters();

                }
            }
        });

        useKanjiOnlySwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!useKanjiOnlySwitch.isChecked())
                {
                    useLessonKanjiOnlySwitch.setVisibility(View.INVISIBLE);
                    useLessonKanjiOnlySwitch.setChecked(false);
                }
                else
                {
                    useLessonKanjiOnlySwitch.setVisibility(View.VISIBLE);
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

    private void setConfirmButtonOnClickListener()
    {
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                metrics.setMode(whichMode);
                makeCountsValid();
                metrics.setNounCount(Integer.parseInt(nounCountText.getText().toString()));
                metrics.setAdjectiveCount(Integer.parseInt(adjectiveCountText.getText().toString()));
                metrics.setVerbCount( Integer.parseInt(verbCountText.getText().toString()));
                metrics.setGrammarCount(Integer.parseInt(grammarCountText.getText().toString()));
                metrics.setOtherCount(Integer.parseInt(otherCountText.getText().toString()));

                metrics.setShowJpnsFirst(showJpnsFirstSwitch.isChecked());
                metrics.setShowKanjiFirst(showKanjiFirstSwitch.isChecked());
                metrics.setUseKanjiOnly(useKanjiOnlySwitch.isChecked());
                metrics.setUseLessonKanjiOnly(useLessonKanjiOnlySwitch.isChecked());
                metrics.setAllTerms(allLessons.isChecked());
                //Gather all lessons selected
                int[] lessons=null;
                if(!allLessons.isChecked())
                {
                    lessons = adapter.getLessonArray();
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
            nounCountText.setText(Integer.toString(maxCountLimit));
        }
        else if(Integer.parseInt(nounCountText.getText().toString()) >maxCountLimit)
        {
            nounCountText.setText(Integer.toString(maxCountLimit));
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

    private void hideExtraSwitches()
    {
        if(useSpecificCountSwitch.isChecked())
        {
            LinearLayout layout = findViewById(R.id.grammarCountLayout);
            grammarCountText.setText("0");
            layout.setVisibility(View.INVISIBLE);
        }
        showKanjiFirstSwitch.setVisibility(View.GONE);
        useKanjiOnlySwitch.setChecked(true);
        useKanjiOnlySwitch.setVisibility(View.GONE);
        useLessonKanjiOnlySwitch.setVisibility(View.VISIBLE);
    }

    private void showExtraSwitches()
    {
        if(useSpecificCountSwitch.isChecked())
        {
            LinearLayout layout = findViewById(R.id.grammarCountLayout);
            grammarCountText.setText("0");
            layout.setVisibility(View.VISIBLE);
        }

        showKanjiFirstSwitch.setVisibility(View.VISIBLE);
        useKanjiOnlySwitch.setChecked(false);
        useKanjiOnlySwitch.setVisibility(View.VISIBLE);
        useLessonKanjiOnlySwitch.setVisibility(View.INVISIBLE);
    }


    private void hideCounters()
    {
        LinearLayout layout;
        layout = findViewById(R.id.nounCountLayout);
        nounCountText.setText("0");
        layout.setVisibility(View.GONE);
        layout = findViewById(R.id.adjectiveCountLayout);
        adjectiveCountText.setText("0");
        layout.setVisibility(View.GONE);
        layout = findViewById(R.id.verbCountLayout);
        verbCountText.setText("0");
        layout.setVisibility(View.GONE);
        layout = findViewById(R.id.grammarCountLayout);
        grammarCountText.setText("0");
        layout.setVisibility(View.GONE);
        layout = findViewById(R.id.otherCountLayout);
        otherCountText.setText("0");
        layout.setVisibility(View.GONE);
        metrics.setCountAll(true);
    }

    private void showCounters()
    {
        LinearLayout layout;
        layout = findViewById(R.id.nounCountLayout);
        nounCountText.setText("0");
        layout.setVisibility(View.VISIBLE);
        layout = findViewById(R.id.adjectiveCountLayout);
        adjectiveCountText.setText("0");
        layout.setVisibility(View.VISIBLE);
        layout = findViewById(R.id.verbCountLayout);
        verbCountText.setText("0");
        layout.setVisibility(View.VISIBLE);

        if(!whichMode.equalsIgnoreCase(getResources().getString(R.string.kanjiStrokeModeText)))
        {
            layout = findViewById(R.id.grammarCountLayout);
            grammarCountText.setText("0");
            layout.setVisibility(View.VISIBLE);
        }

        layout = findViewById(R.id.otherCountLayout);
        otherCountText.setText("0");
        layout.setVisibility(View.VISIBLE);
        metrics.setCountAll(false);
    }
}
