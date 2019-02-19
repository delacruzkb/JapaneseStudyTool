package edu.cofc.japanesestudytool.Database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import edu.cofc.japanesestudytool.Database.Entities.LessonTerm;
import edu.cofc.japanesestudytool.Database.DAOs.LessonTermDAO;
import edu.cofc.japanesestudytool.Database.Converters.LessonsConverter;
import edu.cofc.japanesestudytool.Database.DAOs.TermDAO;
import edu.cofc.japanesestudytool.Database.Entities.Term;

@Database(entities = {Term.class,LessonTerm.class},version=1, exportSchema = false)

@TypeConverters({LessonsConverter.class})
public abstract class StudyGuideDatabase extends RoomDatabase
{
    public abstract TermDAO termDAO();
    public abstract LessonTermDAO lessonTermDAO();
}
