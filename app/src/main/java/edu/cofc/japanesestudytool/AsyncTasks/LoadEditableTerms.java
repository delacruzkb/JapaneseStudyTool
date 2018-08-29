package edu.cofc.japanesestudytool.AsyncTasks;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import java.util.ArrayList;

import edu.cofc.japanesestudytool.EditTermsMetrics;
import edu.cofc.japanesestudytool.Pages.EditTermsPage;
import edu.cofc.japanesestudytool.Term;
import edu.cofc.japanesestudytool.TermDatabase;

public class LoadEditableTerms extends AsyncTask<Void,Void,ArrayList<Term>>
{
    Context context;
    EditTermsMetrics editTermsMetrics;
    String japanese;

    String english;
    String kanji;
    int lesson;
    String termType;
    String typeSpecial;
    boolean reqKanji;

    TermDatabase termDatabase;

    public LoadEditableTerms(Context mContext) {
        this.context = mContext;
        editTermsMetrics = null;
        termDatabase = Room.databaseBuilder(context,TermDatabase.class,"terms").build();
    }

    public LoadEditableTerms(Context mContext, EditTermsMetrics editTermsMetrics) {
        this.context = mContext;
        this.editTermsMetrics = editTermsMetrics;
        japanese = editTermsMetrics.getJapanese();
        english = editTermsMetrics.getEnglish();
        kanji = editTermsMetrics.getKanji();
        lesson = editTermsMetrics.getLesson();
        termType = editTermsMetrics.getTermType();
        typeSpecial = editTermsMetrics.getTypeSpecial();
        reqKanji = editTermsMetrics.isReqKanji();
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
        if(editTermsMetrics==null)
        {
            returnValue = (ArrayList<Term>)termDatabase.termDAO().getAllTerms();
        }
        else
        {
            if(!japanese.equalsIgnoreCase(""))
            {
                returnValue = (ArrayList<Term>)termDatabase.termDAO().searchJpns(japanese);
            }
            else if(!english.equalsIgnoreCase(""))
            {
                returnValue = (ArrayList<Term>)termDatabase.termDAO().searchEng(english);
            }
            else if(!kanji.equalsIgnoreCase(""))
            {
                returnValue = (ArrayList<Term>)termDatabase.termDAO().searchKanji(kanji);
            }
            else if(!termType.equalsIgnoreCase(""))
            {
                if(!typeSpecial.equalsIgnoreCase(""))
                {
                    returnValue = (ArrayList<Term>)termDatabase.termDAO().searchType(termType,typeSpecial);
                }
                else
                {
                    returnValue = (ArrayList<Term>)termDatabase.termDAO().searchType(termType);
                }
            }
            else if(lesson>0)
            {
                returnValue = (ArrayList<Term>)termDatabase.termDAO().searchReqKanjiAndLesson(reqKanji,lesson);
            }
            else
            {
                returnValue = (ArrayList<Term>)termDatabase.termDAO().searchReqKanji(reqKanji);
            }
        }

        return returnValue;
    }
}
