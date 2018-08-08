package edu.cofc.japanesestudytool.AsyncTasks;

import android.os.AsyncTask;

import java.util.ArrayList;

import edu.cofc.japanesestudytool.Term;
import edu.cofc.japanesestudytool.TermDatabase;

public class InsertTerm extends AsyncTask<ArrayList<Term>,Void,Void>
{
    TermDatabase termDatabase;

    public InsertTerm(TermDatabase termDatabase)
    {
        this.termDatabase = termDatabase;
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
