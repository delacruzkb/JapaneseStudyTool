package edu.cofc.japanesestudytool.AsyncTasks;

import android.app.Dialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import edu.cofc.japanesestudytool.AddTermDialogListAdapter;
import edu.cofc.japanesestudytool.R;
import edu.cofc.japanesestudytool.SimilarTermDialog;
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
            InsertTerms insertTerms = new InsertTerms(mContext);
            insertTerms.execute(temp);
        }
        else
        {
            Intent intent = new Intent(mContext, SimilarTermDialog.class);
            intent.putExtra("similarTerms",terms);
            intent.putExtra("newTerm",mTerm);
            mContext.startActivity(intent);
        }
    }

    @Override
    protected ArrayList<Term> doInBackground(Void... voids)
    {
        ArrayList<Term> similarTerms= (ArrayList<Term>) termDatabase.termDAO().getAllMatchedTerms(mTerm.getJpns(),mTerm.getEng());
        return similarTerms;
    }
}
