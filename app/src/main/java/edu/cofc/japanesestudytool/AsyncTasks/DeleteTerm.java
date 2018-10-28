package edu.cofc.japanesestudytool.AsyncTasks;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import edu.cofc.japanesestudytool.R;
import edu.cofc.japanesestudytool.StudyGuideDatabase;
import edu.cofc.japanesestudytool.Term;

public class DeleteTerm extends AsyncTask<Term,Void,Void>
{
    private StudyGuideDatabase studyGuideDatabase;

    public DeleteTerm(Context context)
    {
        studyGuideDatabase = Room.databaseBuilder(context,StudyGuideDatabase.class,context.getResources().getString(R.string.databaseName)).build();
    }

    @Override
    protected Void doInBackground(Term... terms)
    {
        Term term = terms[0];
        studyGuideDatabase.termDAO().deleteTerm(term);

        studyGuideDatabase.lessonTermDAO().deleteTermInLesson(term.getJpns(),term.getEng());
        return null;
    }
}
