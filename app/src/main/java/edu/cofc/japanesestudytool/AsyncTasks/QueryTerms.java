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
import edu.cofc.japanesestudytool.Pages.KanjiWritingPage;
import edu.cofc.japanesestudytool.Pages.StoryPage;
import edu.cofc.japanesestudytool.R;
import edu.cofc.japanesestudytool.Term;
import edu.cofc.japanesestudytool.TermDatabase;
import edu.cofc.japanesestudytool.TermMenuMetrics;

public class QueryTerms extends AsyncTask<Void,Void,Void>
{
    private TermDatabase termDatabase;
    private TermMenuMetrics metrics;
    private Context mContext;
    private ArrayList<Term> nounList, verbList, adjectiveList, grammarList, otherList,termList;
    public QueryTerms(TermMenuMetrics termMenuMetrics, Context context)
    {
        mContext = context;
        metrics = termMenuMetrics;
        termDatabase = Room.databaseBuilder(mContext,TermDatabase.class,"terms").build();
    }

    @Override
    protected void onPostExecute(Void voids)
    {
        if(termList.size()==0)
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
            if(metrics.getMode().equalsIgnoreCase(mContext.getResources().getString(R.string.storyModeText)))
            {
                intent = new Intent(mContext, StoryPage.class);
            }
            if(metrics.getMode().equalsIgnoreCase(mContext.getResources().getString(R.string.flashCardModeText)))
            {
                intent = new Intent(mContext, FlashCardPage.class);
            }
            if(metrics.getMode().equalsIgnoreCase(mContext.getResources().getString(R.string.kanjiStrokeModeText)))
            {
                intent= new Intent(mContext, KanjiWritingPage.class);
            }

            metrics.setNounList(nounList);
            metrics.setVerbList(verbList);
            metrics.setAdjectiveList(adjectiveList);
            metrics.setGrammarList(grammarList);
            metrics.setOtherList(otherList);
            metrics.setTermList(termList);
            intent.putExtra("metrics",metrics);
            mContext.startActivity(intent);
        }

    }

    @Override
    protected Void doInBackground(Void... voids)
    {

        if(metrics.isAllTerms())
        {
            nounList = loadTerms("noun", metrics.getNounCount(),metrics.isCountAll());
            verbList = loadTerms("verb", metrics.getVerbCount(),metrics.isCountAll());
            adjectiveList = loadTerms("adjective", metrics.getAdjectiveCount(),metrics.isCountAll());
            grammarList = loadTerms("grammar", metrics.getGrammarCount(),metrics.isCountAll());
            otherList = loadTerms("other", metrics.getOtherCount(),metrics.isCountAll());
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
        else
        {
            int[] lessons = metrics.getLessons();
            nounList=   loadFromLessons("noun",metrics.getNounCount(),metrics.isCountAll(),lessons);
            verbList=   loadFromLessons("verb",metrics.getVerbCount(),metrics.isCountAll(),lessons);
            adjectiveList=  loadFromLessons("adjective",metrics.getAdjectiveCount(),metrics.isCountAll(),lessons);
            grammarList=    loadFromLessons("grammar",metrics.getGrammarCount(),metrics.isCountAll(),lessons);
            otherList=  loadFromLessons("other",metrics.getOtherCount(),metrics.isCountAll(),lessons);
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
        Collections.shuffle(termList);
        return null;
    }

    private ArrayList<Term> loadTerms(String type, int count, boolean isCountAll)
    {
        ArrayList<Term> rtnval = new ArrayList<>();
        if(metrics.useKanjiOnly())
        {
            if(metrics.useLessonKanjiOnly())
            {
                if(isCountAll)
                {
                    rtnval = (ArrayList<Term>) termDatabase.termDAO().getLessonKanjiOnly(type);
                }
                else
                {
                    rtnval = (ArrayList<Term>) termDatabase.termDAO().getLessonKanjiOnly(type,count);
                }

            }
            else
            {
                if(isCountAll)
                {
                    rtnval = (ArrayList<Term>) termDatabase.termDAO().getKanjiOnly(type);
                }
                else
                {
                    rtnval = (ArrayList<Term>) termDatabase.termDAO().getKanjiOnly(type,count);
                }



            }
        }
        else
        {
            if(isCountAll)
            {
                rtnval= (ArrayList<Term>) termDatabase.termDAO().getAllTypes(type);
            }
            else
            {
                rtnval= (ArrayList<Term>) termDatabase.termDAO().getAllTypes(type,count);
            }

        }

        return rtnval;
    }

    private ArrayList<Term> loadFromLessons(String type, int count, boolean isCountAll, int[] lessons)
    {
        Set<Term> termHashTable = new HashSet<>();
        ArrayList<Term> rtnval=new ArrayList<>();
        ArrayList<Term> temp=new ArrayList<>();

        temp = loadTerms(type,count,isCountAll);
        for(int i =0; i< temp.size();i++)
        {
            for(int q=0; q<lessons.length; q++)
            {
                if(temp.get(i).getLesson().contains(Term.getLessonChar(lessons[q])))
                {
                    if(!rtnval.contains(temp.get(i)))
                    {
                        rtnval.add(temp.get(i));
                    }
                }
            }
        }
        return rtnval;
        /*
        if(metrics.useKanjiOnly())
        {
            if(metrics.useLessonKanjiOnly())
            {
                if(isCountAll)
                {
                    for(int i=0;i<lessons.length;i++ )
                    {
                        temp = (ArrayList<Term>) termDatabase.termDAO().getLessonKanjiOnlyFromLesson(type,Term.getLessonChar(lessons[i]));
                        termHashTable.addAll(temp);
                    }
                }
                else
                {
                    for(int i=0;i<lessons.length;i++ )
                    {
                        temp = (ArrayList<Term>) termDatabase.termDAO().getLessonKanjiOnlyFromLesson(type,count,Term.getLessonChar(lessons[i] ));
                        termHashTable.addAll(temp);
                    }
                }

            }
            else
            {
                if(isCountAll)
                {
                    for(int i=0;i<lessons.length;i++ )
                    {
                        temp = (ArrayList<Term>) termDatabase.termDAO().getKanjiOnlyFromLesson(type,Term.getLessonChar(lessons[i]));
                        termHashTable.addAll(temp);
                    }
                }
                else
                {
                    for(int i=0;i<lessons.length;i++ )
                    {
                        temp = (ArrayList<Term>) termDatabase.termDAO().getKanjiOnlyFromLesson(type,count,Term.getLessonChar(lessons[i]));
                        termHashTable.addAll(temp);
                    }
                }

            }
        }
        else
        {
            if(isCountAll)
            {
                for(int i=0;i<lessons.length;i++ )
                {
                    temp = (ArrayList<Term>) termDatabase.termDAO().getAllTypeFromLesson(type,Term.getLessonChar(lessons[i]));
                    termHashTable.addAll(temp);
                }
            }
            else
            {
                for(int i=0;i<lessons.length;i++ )
                {
                    temp = (ArrayList<Term>) termDatabase.termDAO().getAllTypeFromLesson(type,count,Term.getLessonChar(lessons[i]));
                    termHashTable.addAll(temp);
                }
            }

        }
        //Add all terms in the set to the array list
        rtnval.addAll(termHashTable);
        //Randomize order
        Collections.shuffle(rtnval);
        //truncate if needed
        if(!isCountAll && rtnval.size()>count)
        {
            temp = new ArrayList<>();
            for (int i = 0; i < count; i++)
            {
                temp.add(rtnval.get(i));
            }
        }
        return rtnval;//*/

    }
}
