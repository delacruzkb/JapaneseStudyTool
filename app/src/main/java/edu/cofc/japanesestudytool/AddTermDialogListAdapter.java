package edu.cofc.japanesestudytool;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import edu.cofc.japanesestudytool.AsyncTasks.DeleteTerm;

public class AddTermDialogListAdapter extends BaseAdapter
{
    private ArrayList<Term> similarTerms;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    public AddTermDialogListAdapter(ArrayList<Term> similarTerms, Context mContext)
    {
        this.similarTerms = similarTerms;
        this.mContext = mContext;
        mLayoutInflater =(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return similarTerms.size();
    }

    @Override
    public Object getItem(int position) {
        return similarTerms.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        View rowView = mLayoutInflater.inflate(R.layout.add_term_dialog_list_item, parent,false);
        final Term term = (Term) getItem(position);
        EditText jpns = rowView.findViewById(R.id.similarHiraganaTextBox);
        jpns.setText(term.getJpns());
        EditText eng = rowView.findViewById(R.id.similarEnglishTextBox);
        eng.setText(term.getEng());
        EditText kanji = rowView.findViewById(R.id.similarKanjiTextBox);
        kanji.setText(term.getKanji());
        EditText lesson = rowView.findViewById(R.id.similarLessonTextBox);
        lesson.setText(term.getLesson());
        EditText type = rowView.findViewById(R.id.similarTypeTextBox);
        if(term.getType().equalsIgnoreCase("verb"))
        {
            type.setText(term.getTypeSpecial()+ "-"+term.getType());
        }
        else
        {
            type.setText(term.getType());
        }
        Button deleteButton = rowView.findViewById(R.id.deleteSimilarTermButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Warning");
                builder.setMessage("Would you like to delete this from the database?");
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton("Proceed", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                        DeleteTerm deleteTerm = new DeleteTerm(mContext);
                        deleteTerm.execute(term);
                        similarTerms.remove(position);
                        notifyDataSetChanged();
                    }
                });


            }
        });

        return null;
    }
}
