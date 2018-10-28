package edu.cofc.japanesestudytool.AsyncTasks;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.util.ArrayList;

import edu.cofc.japanesestudytool.Pages.EditTermsPage;
import edu.cofc.japanesestudytool.R;
import edu.cofc.japanesestudytool.StudyGuideDatabase;
import edu.cofc.japanesestudytool.Term;

public class LoadEditableTerms extends AsyncTask<Void,Void,ArrayList<Term>>
{
    private Context context;
    private String japanese,english,kanji,termType,mode;
    int lesson;
    private boolean reqKanji,isExact;
    private StudyGuideDatabase studyGuideDatabase;


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
            lesson = Integer.parseInt(value);
        }
        else if(mode.equalsIgnoreCase("Req. Kanji"))
        {
            reqKanji = value.equalsIgnoreCase("required");
        }
        this.isExact =isExact;
        studyGuideDatabase = Room.databaseBuilder(context,StudyGuideDatabase.class,context.getResources().getString(R.string.databaseName)).build();
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
                returnValue = (ArrayList<Term>) studyGuideDatabase.termDAO().searchExactJpns(japanese);
            }
            else
            {
                returnValue = (ArrayList<Term>) studyGuideDatabase.termDAO().searchJpns(japanese);
            }

        }
        else if(mode.equalsIgnoreCase("English"))
        {
            if(isExact)
            {
                returnValue = (ArrayList<Term>) studyGuideDatabase.termDAO().searchExactEng(english);
            }
            else
            {
                returnValue = (ArrayList<Term>) studyGuideDatabase.termDAO().searchEng(english);
            }
        }
        else if(mode.equalsIgnoreCase("Kanji"))
        {
            if(isExact)
            {
                returnValue = (ArrayList<Term>) studyGuideDatabase.termDAO().searchExactKanji(kanji);
            }
            else
            {
                returnValue = (ArrayList<Term>) studyGuideDatabase.termDAO().searchKanji(kanji);
            }

        }
        else if(mode.equalsIgnoreCase("Type"))
        {
            returnValue = (ArrayList<Term>) studyGuideDatabase.termDAO().searchType(termType);
        }
        else  if(mode.equalsIgnoreCase("Lesson"))
        {
            returnValue = (ArrayList<Term>) studyGuideDatabase.termDAO().searchLesson(lesson);
            return returnValue;
        }
        else if(mode.equalsIgnoreCase("Req. Kanji"))
        {
            returnValue = (ArrayList<Term>) studyGuideDatabase.termDAO().searchReqKanji(reqKanji);
        }

        return returnValue;
    }
}
