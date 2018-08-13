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
    boolean useKanji;
    boolean allTerms;
    int[] lessons;
    int limit;

    public QueryTerms(TermDatabase termDatabase,ArrayList<Term> terms, String type, boolean useKanji, int limit)
    {
        this.type = type;
        this.limit = limit;
        allTerms = true;
        this.terms = terms;
        this.termDatabase = termDatabase;
    }

    public QueryTerms(TermDatabase termDatabase,ArrayList<Term> terms, String type, boolean useKanji,int[] lessons, int limit)
    {
        this.terms = terms;
        this.type = type;
        this.useKanji = useKanji;
        this.lessons = lessons;
        this.limit = limit;
        this.allTerms = false;
        this.termDatabase = termDatabase;
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
            if(useKanji)
            {
                returnValue = (ArrayList<Term>)termDatabase.termDAO().getAllTypes(type,limit);
            }
            else
            {
                returnValue = (ArrayList<Term>) termDatabase.termDAO().getHiraganaType(type,limit);
            }
        }
        else
        {
            if(useKanji)
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
            else
            {
                for( int i =0; i<lessons.length; i++)
                {
                    ArrayList<Term> temp =(ArrayList<Term>) termDatabase.termDAO().getHiraganaTypeFromLesson(type,limit,lessons[i]);
                    if(temp != null)
                    {
                        returnValue.addAll(temp);
                    }
                }
            }
        }

        return returnValue;
    }
}
