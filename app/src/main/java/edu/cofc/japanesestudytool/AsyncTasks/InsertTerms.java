package edu.cofc.japanesestudytool.AsyncTasks;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import edu.cofc.japanesestudytool.Term;
import edu.cofc.japanesestudytool.TermDatabase;

public class InsertTerms extends AsyncTask<ArrayList<Term>,Void,Void>
{
    private TermDatabase termDatabase;

    public InsertTerms(Context context)
    {
        termDatabase = Room.databaseBuilder(context,TermDatabase.class,"terms").build();
    }

    @Override
    protected Void doInBackground(ArrayList<Term>... terms)
    {
        ArrayList<Term> termList = terms[0];
        for(int i=0; i < termList.size(); i++)
        {
            termDatabase.termDAO().insertTerm(termList.get(i));
        }
        return null;
    }
}
