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
            if(metrics.getMode().equalsIgnoreCase("story"))
            {
                intent = new Intent(mContext, StoryPage.class);
            }
            if(metrics.getMode().equalsIgnoreCase("flashcard"))
            {
                intent = new Intent(mContext, FlashCardPage.class);
            }
            if(metrics.getMode().equalsIgnoreCase("kanjiwriting"))
            {
                intent= new Intent(mContext, KanjiWritingPage.class);
            }
            intent.putExtra("metrics",metrics);
            intent.putExtra("nounList",nounList);
            intent.putExtra("verbList",verbList);
            intent.putExtra("adjectiveList",adjectiveList);
            intent.putExtra("grammarList",grammarList);
            intent.putExtra("otherList",otherList);
            Collections.shuffle(termList);
            intent.putExtra("termList",termList);
            mContext.startActivity(intent);
        }

    }

    @Override
    protected Void doInBackground(Void... voids)
    {

        if(metrics.isAllTerms())
        {
            nounList = loadTerms("noun", metrics.getNounCount());
            verbList = loadTerms("verb", metrics.getVerbCount());
            adjectiveList = loadTerms("adjective", metrics.getAdjectiveCount());
            grammarList = loadTerms("grammar", metrics.getGrammarCount());
            otherList = loadTerms("other", metrics.getOtherCount());
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
            nounList=   loadFromLessons("noun",metrics.getNounCount(),lessons);
            verbList=   loadFromLessons("verb",metrics.getVerbCount(),lessons);
            adjectiveList=  loadFromLessons("adjective",metrics.getAdjectiveCount(),lessons);
            grammarList=    loadFromLessons("grammar",metrics.getGrammarCount(),lessons);
            otherList=  loadFromLessons("other",metrics.getOtherCount(),lessons);
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
        return null;
    }

    private ArrayList<Term> loadTerms(String type, int count)
    {
        ArrayList<Term> rtnval = new ArrayList<>();
        if(metrics.useKanjiOnly())
        {
            if(metrics.useLessonKanjiOnly())
            {
                rtnval = (ArrayList<Term>) termDatabase.termDAO().getLessonKanjiOnly(type,count);
            }
            else
            {

                rtnval = (ArrayList<Term>) termDatabase.termDAO().getKanjiOnly(type,count);

            }
        }
        else
        {
            rtnval= (ArrayList<Term>) termDatabase.termDAO().getAllTypes(type,count);
        }

        return rtnval;
    }

    private ArrayList<Term> loadFromLessons(String type, int count, int[] lessons)
    {
        ArrayList<Term> rtnval = new ArrayList<>();
        String[] lessonStrings= new String[24];

        //Fill the lessonStrings array with the proper values
        // 1) copy all lessons
        // 2) fill the rest with the string "ignore" to satisfy the query call and not affect the result
        // all lessons are maped to a character sorted by ascii, 'ignore' will never appear
        for(int i =0; i<lessonStrings.length;i++)
        {
            if(i<lessons.length)
            {
                lessonStrings[i]=Term.getLessonChar(lessons[i]);
            }
            else
            {
                lessonStrings[i]="ignore";
            }
        }

        if(metrics.useKanjiOnly())
        {
            if(metrics.useLessonKanjiOnly())
            {
                rtnval = (ArrayList<Term>) termDatabase.termDAO().getLessonKanjiOnlyFromLessons(type,count,
                    lessonStrings[0], lessonStrings[1], lessonStrings[2],
                        lessonStrings[3], lessonStrings[4], lessonStrings[5],
                        lessonStrings[6], lessonStrings[7], lessonStrings[8],
                        lessonStrings[9], lessonStrings[10], lessonStrings[11],
                        lessonStrings[12], lessonStrings[13], lessonStrings[14],
                        lessonStrings[15], lessonStrings[16], lessonStrings[17],
                        lessonStrings[18], lessonStrings[19], lessonStrings[20],
                        lessonStrings[21], lessonStrings[22], lessonStrings[23]);
            }
            else
            {

                rtnval = (ArrayList<Term>) termDatabase.termDAO().getKanjiOnlyFromLessons(type,count,
                        lessonStrings[0], lessonStrings[1], lessonStrings[2],
                        lessonStrings[3], lessonStrings[4], lessonStrings[5],
                        lessonStrings[6], lessonStrings[7], lessonStrings[8],
                        lessonStrings[9], lessonStrings[10], lessonStrings[11],
                        lessonStrings[12], lessonStrings[13], lessonStrings[14],
                        lessonStrings[15], lessonStrings[16], lessonStrings[17],
                        lessonStrings[18], lessonStrings[19], lessonStrings[20],
                        lessonStrings[21], lessonStrings[22], lessonStrings[23]);

            }
        }
        else
        {
            rtnval= (ArrayList<Term>) termDatabase.termDAO().getAllTypeFromLessons(type,count,
                    lessonStrings[0], lessonStrings[1], lessonStrings[2],
                    lessonStrings[3], lessonStrings[4], lessonStrings[5],
                    lessonStrings[6], lessonStrings[7], lessonStrings[8],
                    lessonStrings[9], lessonStrings[10], lessonStrings[11],
                    lessonStrings[12], lessonStrings[13], lessonStrings[14],
                    lessonStrings[15], lessonStrings[16], lessonStrings[17],
                    lessonStrings[18], lessonStrings[19], lessonStrings[20],
                    lessonStrings[21], lessonStrings[22], lessonStrings[23]);
        }

        return rtnval;
    }
}
