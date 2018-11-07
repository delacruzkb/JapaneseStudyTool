package edu.cofc.japanesestudytool.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import edu.cofc.japanesestudytool.AsyncTasks.DeleteTerm;
import edu.cofc.japanesestudytool.AsyncTasks.MergeTerms;
import edu.cofc.japanesestudytool.Pages.DatabaseEditing.EditSingleTermDialogPage;
import edu.cofc.japanesestudytool.Pages.HomePage;
import edu.cofc.japanesestudytool.R;
import edu.cofc.japanesestudytool.Database.Entities.Term;

public class EditTermListAdapter extends BaseAdapter
{
    private ArrayList<Term> terms;
    private Context context;
    private LayoutInflater mLayoutInflater;
    private Term mergeableTerm;
    public EditTermListAdapter(ArrayList<Term> eTerms, Context mContext)
    {
        terms = eTerms;
        context = mContext;
        mLayoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public EditTermListAdapter(ArrayList<Term> eTerms, Context mContext, Term iTerm)
    {
        terms = eTerms;
        context = mContext;
        mergeableTerm = iTerm;
        mLayoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return terms.size();
    }

    @Override
    public Object getItem(int position) {
        return terms.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent)
    {
        View rowView = mLayoutInflater.inflate(R.layout.edit_term_list_item, parent,false);
        final Term term = (Term) getItem(position);
        EditText jpns = rowView.findViewById(R.id.searchHiraganaTextBox);
        jpns.setEnabled(false);
        jpns.setText(term.getJpns());
        EditText eng = rowView.findViewById(R.id.searchEnglishTextBox);
        eng.setEnabled(false);
        eng.setText(term.getEng());
        EditText kanji = rowView.findViewById(R.id.searchKanjiTextBox);
        kanji.setEnabled(false);
        if(term.getKanji() != null || !term.getKanji().equalsIgnoreCase("") || !term.getKanji().equalsIgnoreCase("null"))
        {
            kanji.setText(term.getKanji());
        }

        TextView lesson = rowView.findViewById(R.id.searchLessonTextBox);
        lesson.setText(term.getLessonString());
        TextView type = rowView.findViewById(R.id.searchTypeTextBox);
        type.setText(term.getType());
        TextView reqKanji = rowView.findViewById(R.id.searchReqKanjiTextBox);
        if(!term.isReqKanji())
        {
            reqKanji.setVisibility(View.INVISIBLE);
        }
        final Button editButton = rowView.findViewById(R.id.editTermButton);
        if(mergeableTerm!=null)
        {
            editButton.setText(context.getResources().getString(R.string.mergeTermButton));
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(editButton.getContext());
                    builder.setTitle(context.getResources().getString(R.string.warningTitle));
                    builder.setMessage(context.getResources().getString(R.string.mergeTermMessage));
                    builder.setNegativeButton(context.getResources().getString(R.string.cancelLabel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.setPositiveButton(context.getResources().getString(R.string.proceedLabel), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            MergeTerms mergeTerms = new MergeTerms(context,term,mergeableTerm);
                            mergeTerms.execute();
                        }
                    });
                    android.support.v7.app.AlertDialog dialog = builder.create();
                    dialog.show();
                }
            });
        }
        else
        {
            editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(context,EditSingleTermDialogPage.class);
                    intent.putExtra("term",term);
                    ((Activity)context).startActivityForResult(intent,1);
                }
            });
        }



        Button deleteButton = rowView.findViewById(R.id.deleteTermButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(context.getResources().getString(R.string.warningTitle));
                builder.setMessage(context.getResources().getString(R.string.deleteFromDatabaseWarning));
                builder.setNegativeButton(context.getResources().getString(R.string.cancelLabel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton(context.getResources().getString(R.string.proceedLabel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {

                        DeleteTerm deleteTerm = new DeleteTerm(context);
                        deleteTerm.execute(term);
                        terms.remove(position);
                        notifyDataSetChanged();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return rowView;
    }
}
