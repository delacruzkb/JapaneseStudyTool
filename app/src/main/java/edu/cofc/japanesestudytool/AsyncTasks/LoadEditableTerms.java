package edu.cofc.japanesestudytool.AsyncTasks;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import edu.cofc.japanesestudytool.Pages.DatabaseEditing.EditTermsPage;
import edu.cofc.japanesestudytool.R;
import edu.cofc.japanesestudytool.Database.StudyGuideDatabase;
import edu.cofc.japanesestudytool.Database.Entities.Term;

public class LoadEditableTerms extends AsyncTask<Void,Void,ArrayList<Term>>
{
    private Context context;
    private String input,mode;
    int lesson;
    private boolean isExact, beginWith;
    private StudyGuideDatabase studyGuideDatabase;


    public LoadEditableTerms(Context mContext, String mMode, String value, boolean isExact, boolean beginWith)
    {
        this.context = mContext;
        mode = mMode;
        if(mode.equalsIgnoreCase("Lesson"))
        {
            lesson = Integer.parseInt(value);
        }
        else
        {
            input=value;
        }
        this.isExact =isExact;
        this.beginWith = beginWith;
        studyGuideDatabase = Room.databaseBuilder(context,StudyGuideDatabase.class,context.getResources().getString(R.string.databaseName)).build();
    }

    @Override
    protected void onPostExecute(ArrayList<Term> terms)
    {
        super.onPostExecute(terms);
        ((Activity)context).finish();
        Intent intent = new Intent(context,EditTermsPage.class);
        if(mode.equalsIgnoreCase("English"))
        {
            Collections.sort(terms, new Comparator<Term>(){
                public int compare(Term t1, Term t2){
                    return t1.getEng().compareTo(t2.getEng());
                }
            });
        }
        else if(mode.equalsIgnoreCase("Kanji"))
        {
            Collections.sort(terms, new Comparator<Term>(){
                public int compare(Term t1, Term t2){
                    return t1.getKanji().compareTo(t2.getKanji());
                }
            });
        }
        else
        {
            Collections.sort(terms, new Comparator<Term>(){
                public int compare(Term t1, Term t2){
                    return t1.getJpns().compareTo(t2.getJpns());
                }
            });
        }
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
                returnValue = (ArrayList<Term>) studyGuideDatabase.termDAO().searchExactJpns(input);
            }
            else if(beginWith)
            {
                returnValue = (ArrayList<Term>) studyGuideDatabase.termDAO().searchBeginWithJapanese(input);
            }
            else
            {
                returnValue = (ArrayList<Term>) studyGuideDatabase.termDAO().searchJpns(input);
            }

        }
        else if(mode.equalsIgnoreCase("English"))
        {
            if(isExact)
            {
                returnValue = (ArrayList<Term>) studyGuideDatabase.termDAO().searchExactEng(input);
            }
            else if(beginWith)
            {
                returnValue = (ArrayList<Term>) studyGuideDatabase.termDAO().searchBeginWithEnglish(input);
            }
            else
            {
                returnValue = (ArrayList<Term>) studyGuideDatabase.termDAO().searchEng(input);
            }
        }
        else if(mode.equalsIgnoreCase("Kanji"))
        {
            if(isExact)
            {
                returnValue = (ArrayList<Term>) studyGuideDatabase.termDAO().searchExactKanji(input);
            }
            else if(beginWith)
            {
                returnValue = (ArrayList<Term>) studyGuideDatabase.termDAO().searchBeginWithKanji(input);
            }
            else
            {
                returnValue = (ArrayList<Term>) studyGuideDatabase.termDAO().searchKanji(input);
            }

        }
        else  if(mode.equalsIgnoreCase("Lesson"))
        {
            returnValue = (ArrayList<Term>) studyGuideDatabase.termDAO().searchLesson(lesson);
        }

        return returnValue;
    }
}
