package edu.cofc.japanesestudytool.AsyncTasks;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import edu.cofc.japanesestudytool.Database.Entities.LessonTerm;
import edu.cofc.japanesestudytool.Database.Entities.Lessons;
import edu.cofc.japanesestudytool.R;
import edu.cofc.japanesestudytool.Database.Entities.Term;
import edu.cofc.japanesestudytool.Database.StudyGuideDatabase;

public class LoadInitialTerms extends AsyncTask<Void,Void,Void>
{
    private Context context;
    private StudyGuideDatabase studyGuideDatabase;
    private ProgressBar spinner;
    private LinearLayout menuButtonsLayout;
    public LoadInitialTerms(Context iContext, ProgressBar progressBar, LinearLayout menuButtons)
    {
        context = iContext;
        spinner = progressBar;
        menuButtonsLayout = menuButtons;
        studyGuideDatabase = Room.databaseBuilder(context,StudyGuideDatabase.class,context.getResources().getString(R.string.databaseName)).build();
    }

    @Override
    protected void onPostExecute(Void aVoid)
    {
        super.onPostExecute(aVoid);
        spinner.setVisibility(View.GONE);
        menuButtonsLayout.setVisibility(View.VISIBLE);
    }

    @Override
    protected Void doInBackground(Void... voids)
    {

        studyGuideDatabase.termDAO().deleteAllTerms();
        ArrayList<Term> listOfTerms = new ArrayList();
        ArrayList<LessonTerm> listOfLessonTerms = new ArrayList<>();
        try
        {
            InputStream is = context.getAssets().open(context.getResources().getString(R.string.initialCSV));
            String line ="";
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            while( (line = reader.readLine()) !=null)
            {
                String[] termData = line.split(",");

                //Store data from CSV Line
                String jpns = termData[0];
                String eng = termData[1];
                String kanji = termData[2];
                String type = termData[3];
                int lesson = Integer.parseInt(termData[4]);
                boolean reqKanji = termData[5].equalsIgnoreCase(context.getResources().getString(R.string.requiredKanjiFlag));

                //Create new Term from CSV
                Term term = new Term();
                term.setJpns(jpns);
                term.setEng(eng);
                term.setKanji(kanji);
                term.setType(type);
                Lessons lessons = new Lessons();
                lessons.addLesson(lesson);
                term.setLessons(lessons);
                term.setReqKanji(reqKanji);

                //If duplicate is found, update it
                if(listOfTerms.contains(term)) // no duplicates
                {
                    Term duplicate = listOfTerms.remove(listOfTerms.indexOf(term));

                    // Don't replace if new kanji is null
                    if(kanji != null && !kanji.equalsIgnoreCase("") && !kanji.equalsIgnoreCase("null"))
                    {
                        duplicate.setKanji(kanji);
                    }

                    duplicate.addLesson(lesson);

                    //Only update if it is to be set to required
                    if(!duplicate.isReqKanji() && reqKanji)
                    {
                        duplicate.setReqKanji(reqKanji);
                    }

                    term = duplicate;
                }
                listOfTerms.add(term);

                //Create LessonTerm from CSV
                LessonTerm lessonTerm = new LessonTerm(lesson,jpns,eng);
                if(!listOfLessonTerms.contains(lessonTerm))
                {
                    listOfLessonTerms.add(lessonTerm);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        for(int i=0; i<listOfTerms.size();i++)
        {
            studyGuideDatabase.termDAO().insertTerm(listOfTerms.get(i));
        }

        for(int i=0; i<listOfLessonTerms.size();i++)
        {
            studyGuideDatabase.lessonTermDAO().insertTerm(listOfLessonTerms.get(i));
        }
        return null;
    }
}
