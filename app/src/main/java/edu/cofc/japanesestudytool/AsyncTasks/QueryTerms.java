package edu.cofc.japanesestudytool.AsyncTasks;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import java.util.ArrayList;

import edu.cofc.japanesestudytool.Pages.FlashCardPage;
import edu.cofc.japanesestudytool.Pages.HomePage;
import edu.cofc.japanesestudytool.Pages.StoryPage;
import edu.cofc.japanesestudytool.Term;
import edu.cofc.japanesestudytool.TermDatabase;
import edu.cofc.japanesestudytool.TermMenuMetrics;

public class QueryTerms extends AsyncTask<Void,Void,Void>
{
    TermDatabase termDatabase;
    TermMenuMetrics metrics;
    Context mContext;
    Activity parentActivity;
    ArrayList<Term> nounList, verbList, adjectiveList, grammarList, otherList,termList;
    public QueryTerms(TermMenuMetrics termMenuMetrics, Activity activity)
    {
        mContext = activity.getApplicationContext();
        parentActivity = activity;
        metrics = termMenuMetrics;
        termDatabase = Room.databaseBuilder(mContext,TermDatabase.class,"terms").build();

    }

    @Override
    protected void onPostExecute(Void voids)
    {
        Intent intent = new Intent(mContext, HomePage.class);
        if(metrics.getMode().equalsIgnoreCase("story"))
        {
            intent = new Intent(mContext, StoryPage.class);
        }
        if(metrics.getMode().equalsIgnoreCase("flashcard"))
        {
            intent = new Intent(mContext, FlashCardPage.class);

        }
        intent.putExtra("metrics",metrics);
        intent.putParcelableArrayListExtra("nounList",nounList);
        intent.putParcelableArrayListExtra("verbList",verbList);
        intent.putParcelableArrayListExtra("adjectiveList",adjectiveList);
        intent.putParcelableArrayListExtra("grammarList",grammarList);
        intent.putParcelableArrayListExtra("otherList",otherList);
        parentActivity.startActivity(intent);
    }

    @Override
    protected Void doInBackground(Void... voids)
    {
        int allTermCount = metrics.getAdjectiveCount() + metrics.getNounCount() + metrics.getVerbCount() + metrics.getGrammarCount() +metrics.getOtherCount();
        if(metrics.isAllTerms())
        {
            if(metrics.getMode().equalsIgnoreCase("story"))
            {
                nounList=   (ArrayList<Term>)   termDatabase.termDAO().getAllTypes("noun",metrics.getNounCount());
                verbList=   (ArrayList<Term>)   termDatabase.termDAO().getAllTypes("verb",metrics.getVerbCount());
                adjectiveList=  (ArrayList<Term>)   termDatabase.termDAO().getAllTypes("adjective",metrics.getAdjectiveCount());
                grammarList=    (ArrayList<Term>)   termDatabase.termDAO().getAllTypes("grammar",metrics.getGrammarCount());
                otherList=  (ArrayList<Term>)   termDatabase.termDAO().getAllTypes("other",metrics.getOtherCount());
            }
            if(metrics.getMode().equalsIgnoreCase("flashcard"))
            {
                termList=   (ArrayList<Term>)   termDatabase.termDAO().getAllTerms(allTermCount);
            }
        }
        else
        {
            int[] lessons = metrics.getLessons();
            if(metrics.getMode().equalsIgnoreCase("story"))
            {
                nounList=   (ArrayList<Term>)   termDatabase.termDAO().getAllTypeFromLesson("noun",lessons,metrics.getNounCount());
                verbList=   (ArrayList<Term>)   termDatabase.termDAO().getAllTypeFromLesson("verb",lessons,metrics.getVerbCount());
                adjectiveList=  (ArrayList<Term>)   termDatabase.termDAO().getAllTypeFromLesson("adjective",lessons,metrics.getAdjectiveCount());
                grammarList=    (ArrayList<Term>)   termDatabase.termDAO().getAllTypeFromLesson("grammar",lessons,metrics.getGrammarCount());
                otherList=  (ArrayList<Term>)   termDatabase.termDAO().getAllTypeFromLesson("other",lessons,metrics.getOtherCount());
            }
            if(metrics.getMode().equalsIgnoreCase("flashcard"))
            {
                termList=   (ArrayList<Term>)   termDatabase.termDAO().getAllTermFromLesson(lessons,allTermCount);
            }

        }

        return null;
    }


}
