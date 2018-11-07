package edu.cofc.japanesestudytool.AsyncTasks;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.util.ArrayList;

import edu.cofc.japanesestudytool.Pages.DatabaseEditing.SimilarTermsPage;
import edu.cofc.japanesestudytool.R;
import edu.cofc.japanesestudytool.Database.Entities.Term;
import edu.cofc.japanesestudytool.Database.StudyGuideDatabase;

public class AddNewTerm extends AsyncTask<Void,Void,ArrayList<Term>>
{
    private Context context;
    private Term mTerm;
    private StudyGuideDatabase studyGuideDatabase;
    public AddNewTerm(Context iContext, Term term)
    {
        context = iContext;
        mTerm =term;
        studyGuideDatabase = Room.databaseBuilder(context,StudyGuideDatabase.class,context.getResources().getString(R.string.databaseName)).build();
    }
    @Override
    protected void onPostExecute(ArrayList<Term> terms)
    {
        super.onPostExecute(terms);
        if(terms == null || terms.size()==0)
        {
            InsertTerm insertTerm = new InsertTerm(context);
            insertTerm.execute(mTerm);
        }
        else
        {
            Intent intent = new Intent(context, SimilarTermsPage.class);
            intent.putExtra("similarTerms",terms);
            intent.putExtra("newTerm",mTerm);
            context.startActivity(intent);
        }
    }

    @Override
    protected ArrayList<Term> doInBackground(Void... voids)
    {
        ArrayList<Term> similarTerms= (ArrayList<Term>) studyGuideDatabase.termDAO().searchSimilarTerms(mTerm.getJpns(),mTerm.getEng());
        return similarTerms;
    }
}
