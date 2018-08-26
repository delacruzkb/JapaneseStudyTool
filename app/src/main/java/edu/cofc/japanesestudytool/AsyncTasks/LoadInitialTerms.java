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
    public LoadInitialTerms(Context iContext)
    {
        context = iContext;
        termDatabase = Room.databaseBuilder(context,TermDatabase.class,"terms").build();
    }

    @Override
    protected void onPostExecute(Void aVoid)
    {
        super.onPostExecute(aVoid);
        Toast.makeText(context, "Complete.", Toast.LENGTH_LONG).show();
    }

    @Override
    protected Void doInBackground(Void... voids)
    {
        Toast.makeText(context, "Please wait . . .", Toast.LENGTH_LONG).show();
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
                term.setTypeSpecial(termData[4]);
                term.setLesson(Integer.parseInt(termData[5]));
                term.setReqKanji(termData[6]);
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
        System.out.println("done:");
        return null;
    }
}
