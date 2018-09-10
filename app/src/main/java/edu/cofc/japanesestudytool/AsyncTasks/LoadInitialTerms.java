package edu.cofc.japanesestudytool.AsyncTasks;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import edu.cofc.japanesestudytool.Term;
import edu.cofc.japanesestudytool.TermDatabase;

public class LoadInitialTerms extends AsyncTask<Void,Void,Void>
{
    Context context;
    TermDatabase termDatabase;
    ProgressBar spinner;
    public LoadInitialTerms(Context iContext,ProgressBar progressBar)
    {
        context = iContext;
        spinner = progressBar;
        termDatabase = Room.databaseBuilder(context,TermDatabase.class,"terms").build();
    }

    @Override
    protected void onPostExecute(Void aVoid)
    {
        super.onPostExecute(aVoid);
        spinner.setVisibility(View.INVISIBLE);
    }

    @Override
    protected Void doInBackground(Void... voids)
    {

        termDatabase.termDAO().deleteAllTerms();
        ArrayList<Term> listOfTerms = new ArrayList();
        try
        {
            InputStream is = context.getAssets().open("jpnsData(initial).csv");
            String line ="";
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            while( (line = reader.readLine()) !=null)
            {
                String[] termData = line.split(",");

                Term term = new Term();
                term.setJpns(termData[0]);
                term.setEng(termData[1]);
                term.setKanji(termData[2]);
                term.setType(termData[3]);
                term.setLesson(Integer.parseInt(termData[4]),Integer.parseInt(termData[4]));
                term.setReqKanji(termData[5]);

                if(listOfTerms.contains(term)) // no duplicates
                {
                    Term duplicate = listOfTerms.get(listOfTerms.indexOf(term));
                    //updates all possible fields

                    // don't touch kanji if null
                    if(!termData[3].equalsIgnoreCase("null"))
                    {
                        duplicate.setKanji(termData[2]);
                    }

                    duplicate.setLesson(Integer.parseInt(termData[4]),Integer.parseInt(termData[4]));

                    //don't touch reqKanji if already true
                    if(!duplicate.isReqKanji())
                    {
                        duplicate.setReqKanji(termData[5]);
                    }

                    term = duplicate;
                }
                listOfTerms.add(term);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        for(int i=0; i < listOfTerms.size(); i++)
        {
            termDatabase.termDAO().insertTerm(listOfTerms.get(i));
        }
        return null;
    }
}
