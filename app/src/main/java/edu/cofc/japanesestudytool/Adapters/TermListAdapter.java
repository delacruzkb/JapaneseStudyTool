package edu.cofc.japanesestudytool.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;

import edu.cofc.japanesestudytool.R;
import edu.cofc.japanesestudytool.Database.Entities.Term;
import edu.cofc.japanesestudytool.TermMenuMetrics;

public class TermListAdapter extends BaseAdapter
{
    private Context context;
    private LayoutInflater mLayoutInflater;
    private ArrayList<Term> data;
    private boolean showJapaneseFirst, showKanjiFirst;
    public TermListAdapter(Context context, ArrayList<Term> data, TermMenuMetrics metrics)
    {
        this.context = context;
        this.data = data;
        mLayoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        showJapaneseFirst = metrics.showJpnsFirst();
        showKanjiFirst = metrics.showKanjiFirst();
    }
    @Override
    public int getCount()
    {
        return data.size();
    }

    @Override
    public Object getItem(int position)
    {
        return data.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        View rowView = mLayoutInflater.inflate(R.layout.term_list_item, parent,false);
        final Term term = (Term) getItem(position);
        final CheckBox checkBox = rowView.findViewById(R.id.checkBox);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    term.setChecked(checkBox.isChecked());
            }
        });
        checkBox.setChecked(term.isChecked());
        final EditText termText = rowView.findViewById(R.id.termText);
        termText.setEnabled(false);
        Button toEng = rowView.findViewById(R.id.toEnglishButton);
        toEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                termText.setText(term.getEng());
            }
        });
        Button toJpns = rowView.findViewById(R.id.toJapaneseButton);
        toJpns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                termText.setText(term.getJpns());
                if(term.getType().contains("verb"))
                {
                    termText.setText(term.getJpns() + "(" + term.getType() + ")");
                }
            }
        });
        Button toKanji = rowView.findViewById(R.id.toKanjiButton);
        toKanji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                termText.setText(term.getKanji());
                if(term.getType().contains("verb"))
                {
                    termText.setText(term.getKanji() + "(" + term.getType() + ")");
                }
            }
        });
        if( term.getKanji() == null || term.getKanji().equalsIgnoreCase("") || term.getKanji().equalsIgnoreCase("null"))
        {
            toKanji.setVisibility(View.INVISIBLE);
        }
        if(showJapaneseFirst)
        {
            termText.setText(term.getJpns());
            if(term.getType().contains("verb"))
            {
                termText.setText(term.getJpns() + "(" + term.getType() + ")");
            }
            if(showKanjiFirst && toKanji.getVisibility() == View.VISIBLE)
            {
                termText.setText(term.getKanji());
                if(term.getType().contains("verb"))
                {
                    termText.setText(term.getKanji() + "(" + term.getType() + ")");
                }
            }
        }
        else
        {
            termText.setText(term.getEng());
        }
        return rowView;
    }

}
