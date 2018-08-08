package edu.cofc.japanesestudytool.AsyncTasks;

import android.os.AsyncTask;

import edu.cofc.japanesestudytool.Term;
import edu.cofc.japanesestudytool.TermDatabase;

public class DeleteTerm extends AsyncTask<Term,Void,Void>
{
    TermDatabase termDatabase;

    public DeleteTerm(TermDatabase termDatabase)
    {
        this.termDatabase = termDatabase;
    }

    @Override
    protected Void doInBackground(Term... term)
    {
        termDatabase.termDAO().deleteTerm(term[0]);
        return null;
    }
}
