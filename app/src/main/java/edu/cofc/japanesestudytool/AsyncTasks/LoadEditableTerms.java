package edu.cofc.japanesestudytool.AsyncTasks;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import edu.cofc.japanesestudytool.EditTermsMetrics;
import edu.cofc.japanesestudytool.Pages.EditTermsPage;
import edu.cofc.japanesestudytool.Term;
import edu.cofc.japanesestudytool.TermDatabase;

public class LoadEditableTerms extends AsyncTask<Void,Void,ArrayList<Term>>
{
    Context context;
    String japanese;

    String english;
    String kanji;
    int lesson;
    String termType;
    String typeSpecial;
    boolean reqKanji;

    String mode;
    TermDatabase termDatabase;


    public LoadEditableTerms(Context mContext, String mMode, String value)
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
            if(value.charAt(0) == 'u')
            {
                termType = "verb";
                typeSpecial ="u";
            }
            else if(value.charAt(0) == 'r')
            {
                termType = "verb";
                typeSpecial ="ru";
            }
            else if (value.charAt(0) == 'i')
            {
                termType = "verb";
                typeSpecial ="irr";
            }
            else
            {
                termType = value;
            }
        }
        else  if(mode.equalsIgnoreCase("Lesson"))
        {
            lesson = Integer.valueOf(value);
        }
        else if(mode.equalsIgnoreCase("Req. Kanji"))
        {
            reqKanji = value.equalsIgnoreCase("required");
        }
        termDatabase = Room.databaseBuilder(context,TermDatabase.class,"terms").build();
    }

    @Override
    protected void onPostExecute(ArrayList<Term> terms)
    {
        super.onPostExecute(terms);
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
            returnValue = (ArrayList<Term>) termDatabase.termDAO().searchJpns(japanese);
        }
        else if(mode.equalsIgnoreCase("English"))
        {
            returnValue = (ArrayList<Term>) termDatabase.termDAO().searchEng(english);
        }
        else if(mode.equalsIgnoreCase("Kanji"))
        {
            returnValue = (ArrayList<Term>) termDatabase.termDAO().searchKanji(kanji);
        }
        else if(mode.equalsIgnoreCase("Type"))
        {
            returnValue = (ArrayList<Term>) termDatabase.termDAO().searchType(termType);
        }
        else  if(mode.equalsIgnoreCase("Lesson"))
        {
            returnValue = (ArrayList<Term>) termDatabase.termDAO().searchLesson(lesson);
        }
        else if(mode.equalsIgnoreCase("Req. Kanji"))
        {
            returnValue = (ArrayList<Term>) termDatabase.termDAO().searchReqKanji(reqKanji);
        }

        return returnValue;
    }
}
