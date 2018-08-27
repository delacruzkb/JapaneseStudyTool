package edu.cofc.japanesestudytool.AsyncTasks;

import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import edu.cofc.japanesestudytool.AddTermDialogListAdapter;
import edu.cofc.japanesestudytool.R;
import edu.cofc.japanesestudytool.Term;
import edu.cofc.japanesestudytool.TermDatabase;

public class AddNewTerm extends AsyncTask<Void,Void,ArrayList<Term>>
{
    Context mContext;
    Term mTerm;
    TermDatabase termDatabase;
    public AddNewTerm(Context context, Term term)
    {
        mContext = context;
        mTerm =term;
        termDatabase = Room.databaseBuilder(mContext,TermDatabase.class,"terms").build();
    }
    @Override
    protected void onPostExecute(ArrayList<Term> terms)
    {
        super.onPostExecute(terms);
        if(terms == null || terms.size()==0)
        {
            ArrayList<Term> temp = new ArrayList<>();
            temp.add(mTerm);
            InsertTerms insertTerms = new InsertTerms(termDatabase);
            insertTerms.execute(temp);
        }
        else
        {
            final Dialog builder = new Dialog(mContext);
            builder.setTitle("Found Similarities");
            ListView listView = builder.findViewById(R.id.similarTermListView);
            AddTermDialogListAdapter addTermDialogListAdapter = new AddTermDialogListAdapter(terms, mContext);
            listView.setAdapter(addTermDialogListAdapter);

            Button cancelButton =builder.findViewById(R.id.cancelNewTermButton);
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    builder.dismiss();
                }
            });
            Button proceedButton = builder.findViewById(R.id.proceedNewTermButton);
            proceedButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    ArrayList<Term> temp = new ArrayList<>();
                    temp.add(mTerm);
                    InsertTerms insertTerms = new InsertTerms(termDatabase);
                    insertTerms.execute(temp);
                    builder.dismiss();
                }
            });
            builder.show();
        }
    }

    @Override
    protected ArrayList<Term> doInBackground(Void... voids)
    {
        ArrayList<Term> similarTerms= (ArrayList<Term>) termDatabase.termDAO().getAllMatchedTerms(mTerm.getJpns(),mTerm.getEng());
        return similarTerms;
    }
}
