package edu.cofc.japanesestudytool.AsyncTasks;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import edu.cofc.japanesestudytool.Database.Entities.Term;
import edu.cofc.japanesestudytool.Database.StudyGuideDatabase;
import edu.cofc.japanesestudytool.Pages.DatabaseEditing.AddTermsPage;
import edu.cofc.japanesestudytool.R;

public class MergeTerms extends AsyncTask<Void,Void,Void>
{
    private StudyGuideDatabase studyGuideDatabase;
    Term originalTerm;
    Term termToBeMerged;
    Context context;

    public MergeTerms(Context context, Term originalTerm, Term termTobeMerged)
    {
        this.originalTerm = originalTerm;
        this.termToBeMerged = termTobeMerged;
        this.context = context;
        studyGuideDatabase = Room.databaseBuilder(context,StudyGuideDatabase.class,context.getResources().getString(R.string.databaseName)).build();
    }

    @Override
    protected void onPostExecute(Void aVoid)
    {
        super.onPostExecute(aVoid);
        Intent intent = new Intent(context,AddTermsPage.class);
        context.startActivity(intent);
    }

    @Override
    protected Void doInBackground(Void... voids)
    {
        if(termToBeMerged.getKanji()!=null)
        {
            originalTerm.setKanji(termToBeMerged.getKanji());
        }
        originalTerm.addLessons(termToBeMerged.getLessons());
        if(!originalTerm.isReqKanji() && termToBeMerged.isReqKanji())
        {
            originalTerm.setReqKanji(termToBeMerged.isReqKanji());
        }
        studyGuideDatabase.termDAO().insertTerm(originalTerm);

        return null;
    }
}