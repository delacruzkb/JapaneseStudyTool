package edu.cofc.japanesestudytool;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;


@Dao
public interface TermDAO
{
    @Insert
    void insertTerm(Term term);

    //if allLessons picked
    @Query("SELECT * FROM Term WHERE type LIKE :type ORDER BY RANDOM() LIMIT :limit")
    List<Term> getAllTypes(String type, int limit);

    //if not all Lessons picked
    @Query("SELECT * FROM Term WHERE type LIKE :type and lesson = :lesson ORDER BY RANDOM() LIMIT :limit")
    List<Term> getAllTypeFromLesson(String type, int lesson, int limit);

    //Delete everything
    @Query("DELETE FROM Term")
    public void deleteAllTerms();


    @Delete
    void deleteTerm( Term term);


}
