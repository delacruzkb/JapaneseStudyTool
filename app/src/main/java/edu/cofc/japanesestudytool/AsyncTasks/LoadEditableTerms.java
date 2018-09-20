package edu.cofc.japanesestudytool.AsyncTasks;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.util.ArrayList;

import edu.cofc.japanesestudytool.Pages.EditTermsPage;
import edu.cofc.japanesestudytool.Term;
import edu.cofc.japanesestudytool.TermDatabase;

public class LoadEditableTerms extends AsyncTask<Void,Void,ArrayList<Term>>
{
    private Context context;
    private String japanese,english,kanji,lesson,termType,mode;
    private boolean reqKanji,isExact;
    private TermDatabase termDatabase;


    public LoadEditableTerms(Context mContext, String mMode, String value, boolean isExact)
    {
        this.context = mContext;
        mode = mMode;
        if(mode.equalsIgnoreCase("Japanese"))
        {
            japanese = value;
        }
        else if(mode.equalsIgnoreCase("English"))
        {
            english=value;
        }
        else if(mode.equalsIgnoreCase("Kanji"))
        {
            kanji = value;
        }
        else if(mode.equalsIgnoreCase("Type"))
        {
            termType = value;
        }
        else  if(mode.equalsIgnoreCase("Lesson"))
        {
            lesson = Term.getLessonChar(Integer.parseInt(value));
        }
        else if(mode.equalsIgnoreCase("Req. Kanji"))
        {
            reqKanji = value.equalsIgnoreCase("required");
        }
        this.isExact =isExact;
        termDatabase = Room.databaseBuilder(context,TermDatabase.class,"terms").build();
    }

    @Override
    protected void onPostExecute(ArrayList<Term> terms)
    {
        super.onPostExecute(terms);
        ((Activity)context).finish();
        Intent intent = new Intent(context,EditTermsPage.class);
        intent.putExtra("terms",terms);
        context.startActivity(intent);
    }

    @Override
    protected ArrayList<Term> doInBackground(Void... voids)
    {
        ArrayList<Term> returnValue = new ArrayList<>();
        if(mode.equalsIgnoreCase("Japanese"))
        {
            if(isExact)
            {
                returnValue = (ArrayList<Term>) termDatabase.termDAO().searchExactJpns(japanese);
            }
            else
            {
                returnValue = (ArrayList<Term>) termDatabase.termDAO().searchJpns(japanese);
            }

        }
        else if(mode.equalsIgnoreCase("English"))
        {
            if(isExact)
            {
                returnValue = (ArrayList<Term>) termDatabase.termDAO().searchExactEng(english);
            }
            else
            {
                returnValue = (ArrayList<Term>) termDatabase.termDAO().searchEng(english);
            }
        }
        else if(mode.equalsIgnoreCase("Kanji"))
        {
            if(isExact)
            {
                returnValue = (ArrayList<Term>) termDatabase.termDAO().searchExactKanji(kanji);
            }
            else
            {
                returnValue = (ArrayList<Term>) termDatabase.termDAO().searchKanji(kanji);
            }

        }
        else if(mode.equalsIgnoreCase("Type"))
        {
            returnValue = (ArrayList<Term>) termDatabase.termDAO().searchType(termType);
        }
        else  if(mode.equalsIgnoreCase("Lesson"))
        {
            returnValue = (ArrayList<Term>) termDatabase.termDAO().searchLesson(lesson);
            return returnValue;
        }
        else if(mode.equalsIgnoreCase("Req. Kanji"))
        {
            returnValue = (ArrayList<Term>) termDatabase.termDAO().searchReqKanji(reqKanji);
        }

        return returnValue;
    }
}
