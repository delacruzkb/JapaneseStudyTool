package edu.cofc.japanesestudytool.AsyncTasks;

import android.app.AlertDialog;
import android.app.Dialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.cofc.japanesestudytool.Pages.FlashCardPage;
import edu.cofc.japanesestudytool.Pages.HomePage;
import edu.cofc.japanesestudytool.Pages.StoryPage;
import edu.cofc.japanesestudytool.R;
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
            builder.setTitle(mContext.getResources().getString(R.string.errorTitle));
            builder.setMessage(mContext.getResources().getString(R.string.noTermsFound));
            builder.setPositiveButton(mContext.getResources().getString(R.string.okLabel), new DialogInterface.OnClickListener() {
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
            if(termList!=null)
            {
                Collections.shuffle(termList);
            }
            intent.putExtra("termList",termList);
            mContext.startActivity(intent);
        }

    }

    @Override
    protected Void doInBackground(Void... voids)
    {

        if(metrics.isAllTerms())
        {
            if(metrics.isKanjiOnly())
            {
                if(metrics.isLessonKanjiOnly())
                {
                    nounList = (ArrayList<Term>) termDatabase.termDAO().getLessonKanjiOnly("noun", metrics.getNounCount());
                    verbList = (ArrayList<Term>) termDatabase.termDAO().getLessonKanjiOnly("verb", metrics.getVerbCount());
                    adjectiveList = (ArrayList<Term>) termDatabase.termDAO().getLessonKanjiOnly("adjective", metrics.getAdjectiveCount());
                    grammarList = (ArrayList<Term>) termDatabase.termDAO().getLessonKanjiOnly("grammar", metrics.getGrammarCount());
                    otherList = (ArrayList<Term>) termDatabase.termDAO().getLessonKanjiOnly("other", metrics.getOtherCount());
                }
                else
                {
                    nounList = (ArrayList<Term>) termDatabase.termDAO().getKanjiOnly("noun", metrics.getNounCount());
                    verbList = (ArrayList<Term>) termDatabase.termDAO().getKanjiOnly("verb", metrics.getVerbCount());
                    adjectiveList = (ArrayList<Term>) termDatabase.termDAO().getKanjiOnly("adjective", metrics.getAdjectiveCount());
                    grammarList = (ArrayList<Term>) termDatabase.termDAO().getKanjiOnly("grammar", metrics.getGrammarCount());
                    otherList = (ArrayList<Term>) termDatabase.termDAO().getKanjiOnly("other", metrics.getOtherCount());
                }

            }
            else
            {
                nounList = (ArrayList<Term>) termDatabase.termDAO().getAllTypes("noun", metrics.getNounCount());
                verbList = (ArrayList<Term>) termDatabase.termDAO().getAllTypes("verb", metrics.getVerbCount());
                adjectiveList = (ArrayList<Term>) termDatabase.termDAO().getAllTypes("adjective", metrics.getAdjectiveCount());
                grammarList = (ArrayList<Term>) termDatabase.termDAO().getAllTypes("grammar", metrics.getGrammarCount());
                otherList = (ArrayList<Term>) termDatabase.termDAO().getAllTypes("other", metrics.getOtherCount());
            }
            if(metrics.getMode().equalsIgnoreCase("flashcard"))
            {
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
            if(metrics.isKanjiOnly())
            {
                if(metrics.isLessonKanjiOnly())
                {
                    nounList=   loadLessonKanjiOnlyFromLessons("noun",lessons,metrics.getNounCount());
                    verbList=   loadLessonKanjiOnlyFromLessons("verb",lessons,metrics.getVerbCount());
                    adjectiveList=  loadLessonKanjiOnlyFromLessons("adjective",lessons,metrics.getAdjectiveCount());
                    grammarList=    loadLessonKanjiOnlyFromLessons("grammar",lessons,metrics.getGrammarCount());
                    otherList=  loadLessonKanjiOnlyFromLessons("other",lessons,metrics.getOtherCount());
                }
                else
                {
                    nounList=   loadKanjiOnlyFromLessons("noun",lessons,metrics.getNounCount());
                    verbList=   loadKanjiOnlyFromLessons("verb",lessons,metrics.getVerbCount());
                    adjectiveList=  loadKanjiOnlyFromLessons("adjective",lessons,metrics.getAdjectiveCount());
                    grammarList=    loadKanjiOnlyFromLessons("grammar",lessons,metrics.getGrammarCount());
                    otherList=  loadKanjiOnlyFromLessons("other",lessons,metrics.getOtherCount());
                }

            }
            else
            {
                nounList=   loadAllTypeFromLessons("noun",lessons,metrics.getNounCount());
                verbList=   loadAllTypeFromLessons("verb",lessons,metrics.getVerbCount());
                adjectiveList=  loadAllTypeFromLessons("adjective",lessons,metrics.getAdjectiveCount());
                grammarList=    loadAllTypeFromLessons("grammar",lessons,metrics.getGrammarCount());
                otherList=  loadAllTypeFromLessons("other",lessons,metrics.getOtherCount());
            }
            if(metrics.getMode().equalsIgnoreCase("flashcard"))
            {
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

    private ArrayList<Term> loadLessonKanjiOnlyFromLessons(String type, int[] lessons, int count)
    {
        Set<Term> termHashTable = new HashSet<>();
        ArrayList<Term> rtnval=new ArrayList<>();

        //Fill the set with terms
        for(int i=0;i<lessons.length;i++ )
        {
            ArrayList<Term> temp = (ArrayList<Term>) termDatabase.termDAO().getLessonKanjiOnlyFromLessons(type,Term.getLessonChar(lessons[i]),count);
            termHashTable.addAll(temp);
        }


        //Add all terms in the set to the array list
        rtnval.addAll(termHashTable);
        //Randomize order and truncate to fit use
        Collections.shuffle(rtnval);
        if(rtnval.size()>count)
        {
            ArrayList<Term> temp = new ArrayList<>();
            for(int i =0; i<count;i++)
            {
                temp.add(rtnval.get(i));
            }
            rtnval=temp;
        }

        return rtnval;
    }

    private ArrayList<Term> loadKanjiOnlyFromLessons(String type, int[] lessons, int count)
    {
        Set<Term> termHashTable = new HashSet<>();
        ArrayList<Term> rtnval=new ArrayList<>();

        //Fill the set with terms
        for(int i=0;i<lessons.length;i++ )
        {
            ArrayList<Term> temp = (ArrayList<Term>) termDatabase.termDAO().getKanjiOnlyFromLessons(type,Term.getLessonChar(lessons[i]),count);
            termHashTable.addAll(temp);
        }


        //Add all terms in the set to the array list
        rtnval.addAll(termHashTable);
        //Randomize order and truncate to fit use
        Collections.shuffle(rtnval);
        if(rtnval.size()>count)
        {
            ArrayList<Term> temp = new ArrayList<>();
            for(int i =0; i<count;i++)
            {
                temp.add(rtnval.get(i));
            }
            rtnval=temp;
        }

        return rtnval;
    }
    private ArrayList<Term> loadAllTypeFromLessons(String type, int[] lessons, int count)
    {
        Set<Term> termHashTable = new HashSet<>();
        ArrayList<Term> rtnval=new ArrayList<>();

        //Fill the set with terms
        for(int i=0;i<lessons.length;i++ )
        {
            ArrayList<Term> temp = (ArrayList<Term>) termDatabase.termDAO().getAllTypeFromLessons(type,Term.getLessonChar(lessons[i]),count);
            termHashTable.addAll(temp);
        }


        //Add all terms in the set to the array list
        rtnval.addAll(termHashTable);
        //Randomize order and truncate to fit use
        Collections.shuffle(rtnval);
        if(rtnval.size()>count)
        {
            ArrayList<Term> temp = new ArrayList<>();
            for(int i =0; i<count;i++)
            {
                temp.add(rtnval.get(i));
            }
            rtnval=temp;
        }
        return rtnval;
    }


}
