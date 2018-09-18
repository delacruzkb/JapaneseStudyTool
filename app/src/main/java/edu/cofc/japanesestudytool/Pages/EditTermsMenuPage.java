package edu.cofc.japanesestudytool.Pages;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;

import edu.cofc.japanesestudytool.AsyncTasks.LoadEditableTerms;
import edu.cofc.japanesestudytool.R;

public class EditTermsMenuPage extends AppCompatActivity
{
    private Button searchButton;
    private Spinner dropDownBar;
    private Spinner specificDropDownBar;
    private EditText searchEditTextBox;
    private Switch searchExactSwitch;
    private final String[] items = new String[]{"Japanese","English","Kanji","Type","Lesson","Req. Kanji"};
    private final String[] typeSpecs = new String[]{"noun","u-verb","ru-verb","irr-verb","adjective","grammar","other"};
    private final String[] lessonSpec = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19",
            "20","21","22","23","extra"};
    private final String[] reqKanjiSpec = new String[]{"Required","Non-required"};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_terms_menu_page);

        searchEditTextBox = findViewById(R.id.searchEditTextBox);
        searchEditTextBox.setVisibility(View.INVISIBLE);
        specificDropDownBar = findViewById(R.id.specificDropDrown);
        specificDropDownBar.setVisibility(View.INVISIBLE);
        dropDownBar = findViewById(R.id.editSearchDropDownBar);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,items);
        dropDownBar.setAdapter(adapter);
        dropDownBar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String mode = parent.getItemAtPosition(position).toString();
                if(mode.equalsIgnoreCase("Japanese")|| mode.equalsIgnoreCase("English")
                        || mode.equalsIgnoreCase("Kanji"))
                {
                    searchEditTextBox.setVisibility(View.VISIBLE);
                    searchEditTextBox.requestFocus();
                    specificDropDownBar.setVisibility(View.INVISIBLE);
                    searchExactSwitch.setVisibility(View.VISIBLE);
                }
                else if(mode.equalsIgnoreCase("Type"))
                {
                    searchEditTextBox.setVisibility(View.INVISIBLE);
                    specificDropDownBar.setVisibility(View.VISIBLE);
                    ArrayAdapter<String> tempAdapter = new ArrayAdapter<>(dropDownBar.getContext(),android.R.layout.simple_spinner_dropdown_item,typeSpecs);
                    specificDropDownBar.setAdapter(tempAdapter);
                    searchExactSwitch.setVisibility(View.INVISIBLE);
                }
                else  if(mode.equalsIgnoreCase("Lesson"))
                {
                    searchEditTextBox.setVisibility(View.INVISIBLE);
                    specificDropDownBar.setVisibility(View.VISIBLE);
                    ArrayAdapter<String> tempAdapter = new ArrayAdapter<>(dropDownBar.getContext(),android.R.layout.simple_spinner_dropdown_item,lessonSpec);
                    specificDropDownBar.setAdapter(tempAdapter);
                    searchExactSwitch.setVisibility(View.INVISIBLE);
                }
                else if(mode.equalsIgnoreCase("Req. Kanji"))
                {
                    searchEditTextBox.setVisibility(View.INVISIBLE);
                    specificDropDownBar.setVisibility(View.VISIBLE);
                    ArrayAdapter<String> tempAdapter = new ArrayAdapter<>(dropDownBar.getContext(),android.R.layout.simple_spinner_dropdown_item,reqKanjiSpec);
                    specificDropDownBar.setAdapter(tempAdapter);
                    searchExactSwitch.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        searchExactSwitch = findViewById(R.id.searchExact);
        searchButton = findViewById(R.id.searchButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String mode = dropDownBar.getSelectedItem().toString();
                String tempValue="";
                boolean isFieldEmpty=false;
                if(mode.equalsIgnoreCase("Japanese")|| mode.equalsIgnoreCase("English")
                        || mode.equalsIgnoreCase("Kanji"))
                {
                    tempValue = searchEditTextBox.getText().toString();
                    if(tempValue.length() ==0)
                    {
                        isFieldEmpty = true;
                    }
                }
                else if(mode.equalsIgnoreCase("Type")
                        || mode.equalsIgnoreCase("Lesson")
                        || mode.equalsIgnoreCase("Req. Kanji"))
                {
                    tempValue = specificDropDownBar.getSelectedItem().toString();

                }
                if(tempValue.equalsIgnoreCase("extra"))
                {
                    tempValue="0";
                }
                final String value = tempValue;
                if(isFieldEmpty)
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(searchButton.getContext());
                    builder.setTitle(getResources().getString(R.string.errorTitle));
                    builder.setMessage(getResources().getString(R.string.emptyStringMessage));
                    builder.setPositiveButton(getResources().getString(R.string.okLabel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else if(value.contains("\n"))
                {
                    AlertDialog.Builder builder = new AlertDialog.Builder(searchButton.getContext());
                    builder.setTitle(getResources().getString(R.string.warningTitle));
                    builder.setMessage(getResources().getString(R.string.multiLineWarning));
                    builder.setNegativeButton(getResources().getString(R.string.cancelLabel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.dismiss();
                        }
                    });
                    builder.setPositiveButton(getResources().getString(R.string.proceedLabel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            LoadEditableTerms loadEditableTerms = new LoadEditableTerms(searchButton.getContext(),mode,value, searchExactSwitch.isChecked());
                            loadEditableTerms.execute();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else
                {
                    LoadEditableTerms loadEditableTerms = new LoadEditableTerms(searchButton.getContext(),mode,value, searchExactSwitch.isChecked());
                    loadEditableTerms.execute();
                }


            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("text",searchEditTextBox.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
        searchEditTextBox.setText(savedInstanceState.getString("text"));
        //TODO:figure out how to set spec
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(searchButton.getContext());
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

                Intent intent = new Intent(searchButton.getContext(), HomePage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}