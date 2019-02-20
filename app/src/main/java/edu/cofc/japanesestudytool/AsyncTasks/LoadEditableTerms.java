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
    private String japanese,english,kanji,mode;
    int lesson;
    private boolean isExact, beginWith;
    private StudyGuideDatabase studyGuideDatabase;


    public LoadEditableTerms(Context mContext, String mMode, String value, boolean isExact, boolean beginWith)
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
        else  if(mode.equalsIgnoreCase("Lesson"))
        {
            lesson = Integer.parseInt(value);
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
                returnValue = (ArrayList<Term>) studyGuideDatabase.termDAO().searchExactJpns(japanese);
            }
            else if(beginWith)
            {
                returnValue = (ArrayList<Term>) studyGuideDatabase.termDAO().searchBeginWithJapanese(japanese);
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
            else if(beginWith)
            {
                returnValue = (ArrayList<Term>) studyGuideDatabase.termDAO().searchBeginWithEnglish(english);
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
            else if(beginWith)
            {
                returnValue = (ArrayList<Term>) studyGuideDatabase.termDAO().searchBeginWithKanji(kanji);
            }
            else
            {
                returnValue = (ArrayList<Term>) studyGuideDatabase.termDAO().searchKanji(kanji);
            }

        }
        else  if(mode.equalsIgnoreCase("Lesson"))
        {
            returnValue = (ArrayList<Term>) studyGuideDatabase.termDAO().searchLesson(lesson);
        }

        return returnValue;
    }
}
