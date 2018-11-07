package edu.cofc.japanesestudytool.AsyncTasks;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;
import edu.cofc.japanesestudytool.R;
import edu.cofc.japanesestudytool.Database.Entities.Term;
import edu.cofc.japanesestudytool.Database.StudyGuideDatabase;

public class InsertTerm extends AsyncTask<Term,Void,Void>
{
    private StudyGuideDatabase studyGuideDatabase;

    public InsertTerm(Context context)
    {
        studyGuideDatabase = Room.databaseBuilder(context,StudyGuideDatabase.class,context.getResources().getString(R.string.databaseName)).build();
    }

    @Override
    protected Void doInBackground(Term... terms) {
        Term term = terms[0];

        ArrayList<Integer> lessons = term.getLessons().getLessons();

        studyGuideDatabase.termDAO().insertTerm(term);

        return null;
    }
}
