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

import edu.cofc.japanesestudytool.Pages.FlashCardPage;
import edu.cofc.japanesestudytool.Pages.HomePage;
import edu.cofc.japanesestudytool.Pages.KanjiWritingPage;
import edu.cofc.japanesestudytool.Pages.StoryPage;
import edu.cofc.japanesestudytool.R;
import edu.cofc.japanesestudytool.StudyGuideDatabase;
import edu.cofc.japanesestudytool.Term;
import edu.cofc.japanesestudytool.TermMenuMetrics;

public class QueryTerms extends AsyncTask<Void,Void,Void>
{
    private StudyGuideDatabase studyGuideDatabase;
    private TermMenuMetrics metrics;
    private Context context;
    private ArrayList<Term> nounList, verbList, adjectiveList, grammarList, otherList,termList;
    public QueryTerms(TermMenuMetrics termMenuMetrics, Context iContext)
    {
        context = iContext;
        metrics = termMenuMetrics;
        studyGuideDatabase = Room.databaseBuilder(context,StudyGuideDatabase.class,context.getResources().getString(R.string.databaseName)).build();
    }

    @Override
    protected void onPostExecute(Void voids)
    {
        if(termList.size()==0)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(context.getResources().getString(R.string.errorTitle));
            builder.setMessage(context.getResources().getString(R.string.noTermsFound));
            builder.setPositiveButton(context.getResources().getString(R.string.okLabel), new DialogInterface.OnClickListener() {
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
            Intent intent = new Intent(context, HomePage.class);
            if(metrics.getMode().equalsIgnoreCase(context.getResources().getString(R.string.storyModeText)))
            {
                intent = new Intent(context, StoryPage.class);
            }
            if(metrics.getMode().equalsIgnoreCase(context.getResources().getString(R.string.flashCardModeText)))
            {
                intent = new Intent(context, FlashCardPage.class);
            }
            if(metrics.getMode().equalsIgnoreCase(context.getResources().getString(R.string.kanjiStrokeModeText)))
            {
                intent= new Intent(context, KanjiWritingPage.class);
            }

            metrics.setNounList(nounList);
            metrics.setVerbList(verbList);
            metrics.setAdjectiveList(adjectiveList);
            metrics.setGrammarList(grammarList);
            metrics.setOtherList(otherList);
            metrics.setTermList(termList);
            intent.putExtra("metrics",metrics);
            context.startActivity(intent);
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
            int[] lessons = metrics.getLessonsArray();
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
                    rtnval = (ArrayList<Term>) studyGuideDatabase.termDAO().getLessonKanjiOnly(type);
                }
                else
                {
                    rtnval = (ArrayList<Term>) studyGuideDatabase.termDAO().getLessonKanjiOnly(type,count);
                }
            }
            else
            {
                if(isCountAll)
                {
                    rtnval = (ArrayList<Term>) studyGuideDatabase.termDAO().getKanjiOnly(type);
                }
                else
                {
                    rtnval = (ArrayList<Term>) studyGuideDatabase.termDAO().getKanjiOnly(type,count);
                }
            }
        }
        else
        {
            if(isCountAll)
            {
                rtnval= (ArrayList<Term>) studyGuideDatabase.termDAO().getAllTypes(type);
            }
            else
            {
                rtnval= (ArrayList<Term>) studyGuideDatabase.termDAO().getAllTypes(type,count);
            }
        }
        return rtnval;
    }

    private ArrayList<Term> loadFromLessons(String type, int count, boolean isCountAll, int[] lessons)
    {
        ArrayList<Term> rtnval = new ArrayList<>();
        if(metrics.useKanjiOnly())
        {
            if(metrics.useLessonKanjiOnly())
            {
                if(isCountAll)
                {
                    rtnval = (ArrayList<Term>) studyGuideDatabase.termDAO().getLessonKanjiOnlyFromLessons(type,lessons);
                }
                else
                {
                    rtnval = (ArrayList<Term>) studyGuideDatabase.termDAO().getLessonKanjiOnlyFromLessons(type,lessons,count);
                }
            }
            else
            {
                if(isCountAll)
                {
                    rtnval = (ArrayList<Term>) studyGuideDatabase.termDAO().getKanjiOnlyFromLessons(type,lessons);
                }
                else
                {
                    rtnval = (ArrayList<Term>) studyGuideDatabase.termDAO().getKanjiOnlyFromLessons(type,lessons, count);
                }
            }
        }
        else
        {
            if(isCountAll)
            {
                rtnval= (ArrayList<Term>) studyGuideDatabase.termDAO().getAllTypesFromLessons(type,lessons);
            }
            else
            {
                rtnval= (ArrayList<Term>) studyGuideDatabase.termDAO().getAllTypesFromLessons(type,lessons, count);
            }
        }

        return rtnval;
    }
}
