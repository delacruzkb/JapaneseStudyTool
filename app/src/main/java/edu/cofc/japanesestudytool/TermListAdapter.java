package edu.cofc.japanesestudytool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;

public class TermListAdapter extends BaseAdapter
{
    private Context context;
    private LayoutInflater mLayoutInflater;
    private ArrayList<Term> data;
    private boolean showJapaneseFirst;
    private boolean useKanji;
    private boolean lessonKanjiOnly;
    private boolean showKanjiFirst;
    public TermListAdapter(Context context, ArrayList<Term> data, boolean showJapaneseFirst, boolean useKanji, boolean lessonKanjiOnly,boolean showKanjiFirst)
    {
        this.context = context;
        this.data = data;
        mLayoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.showJapaneseFirst = showJapaneseFirst;
        this.useKanji = useKanji;
        this.lessonKanjiOnly = lessonKanjiOnly;
        this.showKanjiFirst = showKanjiFirst;
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
        CheckBox checkBox = rowView.findViewById(R.id.checkBox);
        final EditText termText = rowView.findViewById(R.id.termText);
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
            }
        });
        Button toKanji = rowView.findViewById(R.id.toKanjiButton);
        toKanji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                termText.setText(term.getKanji());
            }
        });

        if(!useKanji)
        {
            toKanji.setVisibility(View.INVISIBLE);
        }
        if(showKanjiFirst)
        {
            termText.setText(term.getJpns());
            if(showKanjiFirst)
            {
                termText.setText(term.getKanji());
                if(lessonKanjiOnly&& !term.getReqKanji())
                {
                    termText.setText(term.getJpns());
                    toKanji.setVisibility(View.INVISIBLE);
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
