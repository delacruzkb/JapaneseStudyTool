package edu.cofc.japanesestudytool.AsyncTasks;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
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
    ArrayList<Term> nounList, verbList, adjectiveList, grammarList, otherList,termList;
    public QueryTerms(TermMenuMetrics termMenuMetrics, Context context)
    {
        mContext = context;
        metrics = termMenuMetrics;
        termDatabase = Room.databaseBuilder(mContext,TermDatabase.class,"terms").build();
    }

    @Override
    protected void onPostExecute(Void voids)
    {
        if((nounList.size() == 0 && adjectiveList.size()==0
                && verbList.size()==0 && grammarList.size() ==0
                && otherList.size()==0))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle("Error");
            builder.setMessage("No terms found");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            Dialog dialog = builder.create();
            dialog.show();
        }
        else
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
            intent.putExtra("nounList",nounList);
            intent.putExtra("verbList",verbList);
            intent.putExtra("adjectiveList",adjectiveList);
            intent.putExtra("grammarList",grammarList);
            intent.putExtra("otherList",otherList);
            intent.putExtra("termList",termList);
            mContext.startActivity(intent);
        }

    }

    @Override
    protected Void doInBackground(Void... voids)
    {

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
                nounList=   (ArrayList<Term>)   termDatabase.termDAO().getAllTypes("noun",metrics.getNounCount());
                verbList=   (ArrayList<Term>)   termDatabase.termDAO().getAllTypes("verb",metrics.getVerbCount());
                adjectiveList=  (ArrayList<Term>)   termDatabase.termDAO().getAllTypes("adjective",metrics.getAdjectiveCount());
                grammarList=    (ArrayList<Term>)   termDatabase.termDAO().getAllTypes("grammar",metrics.getGrammarCount());
                otherList=  (ArrayList<Term>)   termDatabase.termDAO().getAllTypes("other",metrics.getOtherCount());
                termList= new ArrayList<>();
                if(nounList !=null)
                {
                    termList.addAll(nounList);
                }
                if(verbList !=null)
                {
                    termList.addAll(verbList);
                }
                if(adjectiveList !=null)
                {
                    termList.addAll(adjectiveList);
                }
                if(grammarList !=null)
                {
                    termList.addAll(grammarList);
                }
                if(otherList !=null)
                {
                    termList.addAll(otherList);
                }
            }
        }
        else
        {
            int[] lessons = metrics.getLessons();
            if(metrics.getMode().equalsIgnoreCase("story"))
            {
                nounList=   (ArrayList<Term>)   termDatabase.termDAO().getAllTypeFromLessons("noun",lessons,metrics.getNounCount());
                verbList=   (ArrayList<Term>)   termDatabase.termDAO().getAllTypeFromLessons("verb",lessons,metrics.getVerbCount());
                adjectiveList=  (ArrayList<Term>)   termDatabase.termDAO().getAllTypeFromLessons("adjective",lessons,metrics.getAdjectiveCount());
                grammarList=    (ArrayList<Term>)   termDatabase.termDAO().getAllTypeFromLessons("grammar",lessons,metrics.getGrammarCount());
                otherList=  (ArrayList<Term>)   termDatabase.termDAO().getAllTypeFromLessons("other",lessons,metrics.getOtherCount());
            }
            if(metrics.getMode().equalsIgnoreCase("flashcard"))
            {
                nounList=   (ArrayList<Term>)   termDatabase.termDAO().getAllTypeFromLessons("noun",lessons,metrics.getNounCount());
                verbList=   (ArrayList<Term>)   termDatabase.termDAO().getAllTypeFromLessons("verb",lessons,metrics.getVerbCount());
                adjectiveList=  (ArrayList<Term>)   termDatabase.termDAO().getAllTypeFromLessons("adjective",lessons,metrics.getAdjectiveCount());
                grammarList=    (ArrayList<Term>)   termDatabase.termDAO().getAllTypeFromLessons("grammar",lessons,metrics.getGrammarCount());
                otherList=  (ArrayList<Term>)   termDatabase.termDAO().getAllTypeFromLessons("other",lessons,metrics.getOtherCount());
                termList= new ArrayList<>();
                if(nounList !=null)
                {
                    termList.addAll(nounList);
                }
                if(verbList !=null)
                {
                    termList.addAll(verbList);
                }
                if(adjectiveList !=null)
                {
                    termList.addAll(adjectiveList);
                }
                if(grammarList !=null)
                {
                    termList.addAll(grammarList);
                }
                if(otherList !=null)
                {
                    termList.addAll(otherList);
                }
            }

        }

        return null;
    }


}
