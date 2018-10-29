package edu.cofc.japanesestudytool;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
@Dao
public interface LessonTermDAO
{
    //Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTerm(LessonTerm lessonTerm);

    //Single Delete
    @Delete
    void deleteTerm( LessonTerm lessonTerm);

    //Delete Based on Term
    @Query("DELETE " +
            "FROM LessonTerm " +
            "WHERE jpnsID = :jpns " +
            "and engID = :eng")
    void deleteTermInLesson(String jpns, String eng);

    //Delete everything
    @Query("DELETE " +
            "FROM LessonTerm")
    void deleteAllTerms();
}
