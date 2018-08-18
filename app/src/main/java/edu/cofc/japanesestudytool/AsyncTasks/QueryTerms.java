package edu.cofc.japanesestudytool.AsyncTasks;

import android.os.AsyncTask;

import java.util.ArrayList;

import edu.cofc.japanesestudytool.Term;
import edu.cofc.japanesestudytool.TermDatabase;

public class QueryTerms extends AsyncTask<Void,Void,ArrayList<Term>>
{
    TermDatabase termDatabase;
    ArrayList<Term> terms;
    String type;
    boolean allTerms;
    int[] lessons;
    int limit;

    public QueryTerms(TermDatabase termDatabase,ArrayList<Term> terms, String type, int[] lessons, int limit)
    {
        this.termDatabase = termDatabase;
        this.terms = terms;
        this.type = type;
        this.allTerms=false;
        if(lessons ==null) {
            this.allTerms = true;
        }
        this.lessons = lessons;
        this.limit = limit;
    }

    @Override
    protected void onPostExecute(ArrayList<Term> terms)
    {
        if(terms != null) {
            this.terms.addAll(terms);
        }
    }

    @Override
    protected ArrayList<Term> doInBackground(Void... voids)
    {
        ArrayList<Term> returnValue = new ArrayList<>();

        if(allTerms)
        {
            returnValue = (ArrayList<Term>)termDatabase.termDAO().getAllTypes(type,limit);
        }
        else
        {
            for( int i =0; i<lessons.length; i++)
            {
                ArrayList<Term> temp = (ArrayList<Term>) termDatabase.termDAO().getAllTypeFromLesson(type,limit,lessons[i]);
                if(temp != null)
                {
                    returnValue.addAll(temp);
                }
            }
        }

        return returnValue;
    }
}
