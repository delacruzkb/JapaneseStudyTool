package edu.cofc.japanesestudytool.AsyncTasks;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import edu.cofc.japanesestudytool.Term;
import edu.cofc.japanesestudytool.TermDatabase;

public class DeleteTerm extends AsyncTask<Term,Void,Void>
{
    private TermDatabase termDatabase;

    public DeleteTerm(Context mContext)
    {
        termDatabase = Room.databaseBuilder(mContext,TermDatabase.class,"terms").build();
    }

    @Override
    protected Void doInBackground(Term... term)
    {
        termDatabase.termDAO().deleteTerm(term[0]);
        return null;
    }
}
