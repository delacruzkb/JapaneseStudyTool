package edu.cofc.japanesestudytool.AsyncTasks;

import android.os.AsyncTask;

import edu.cofc.japanesestudytool.TermDatabase;

public class DeleteAllTerms extends AsyncTask<Void,Void,Void>
{
    TermDatabase termDatabase;

    public DeleteAllTerms(TermDatabase termDatabase)
    {
        this.termDatabase = termDatabase;
    }

    @Override
    protected Void doInBackground(Void... voids)
    {
        termDatabase.termDAO().deleteAllTerms();
        return null;
    }
}