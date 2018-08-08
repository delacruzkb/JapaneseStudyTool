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
    int[] lessons;
    int limit;
    public QueryTerms(ArrayList<Term> terms, String type, boolean useKanji,int[] lessons, int limit)
    {
        this.terms = terms;
        this.type = type;
        this.useKanji = useKanji;
        this.lessons = lessons;
        this.limit = limit;
    }

    @Override
    protected void onPostExecute(ArrayList<Term> terms)
    {
        this.terms.addAll(terms);
    }

    @Override
    protected ArrayList<Term> doInBackground(Void... voids)
    {
        ArrayList<Term> returnValue = new ArrayList<>();

        if(lessons.length == 1)
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
                    returnValue.addAll((ArrayList<Term>) termDatabase.termDAO().getAllTypeFromLesson(type,limit,lessons[i]));
                }
            }
            else
            {
                for( int i =0; i<lessons.length; i++)
                {
                    returnValue.addAll((ArrayList<Term>) termDatabase.termDAO().getHiraganaTypeFromLesson(type,limit,lessons[i]));
                }
            }
        }

        return returnValue;
    }
}
