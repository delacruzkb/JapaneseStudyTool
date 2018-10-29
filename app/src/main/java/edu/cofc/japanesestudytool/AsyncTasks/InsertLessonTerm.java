package edu.cofc.japanesestudytool.AsyncTasks;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;

import edu.cofc.japanesestudytool.LessonTerm;
import edu.cofc.japanesestudytool.R;
import edu.cofc.japanesestudytool.StudyGuideDatabase;

public class InsertLessonTerm extends AsyncTask<LessonTerm,Void,Void>
{
    private StudyGuideDatabase studyGuideDatabase;
    private Context context;

    public InsertLessonTerm(Context context) {
        this.context = context;
        studyGuideDatabase = Room.databaseBuilder(context,StudyGuideDatabase.class,context.getResources().getString(R.string.databaseName)).build();
    }

    @Override
    protected Void doInBackground(LessonTerm... lessonTerms)
    {
        LessonTerm lessonTerm = lessonTerms[0];

        studyGuideDatabase.lessonTermDAO().insertTerm(lessonTerm);

        return null;
    }
}
