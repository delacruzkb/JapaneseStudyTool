package edu.cofc.japanesestudytool;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

@Database(entities = {Term.class,LessonTerm.class},version=1)
@TypeConverters({LessonsConverter.class})
public abstract class StudyGuideDatabase extends RoomDatabase
{
    public abstract TermDAO termDAO();
    public abstract LessonTermDAO lessonTermDAO();
}
