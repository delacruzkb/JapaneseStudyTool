package edu.cofc.japanesestudytool;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;


@Dao
public interface TermDAO
{
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertTerm(Term term);
    @Query("SELECT * FROM Term ORDER BY RANDOM() LIMIT :limit")
    List<Term> getAllTerms(int limit);
    //if allLessons picked
    @Query("SELECT * FROM Term WHERE type LIKE :type ORDER BY RANDOM() LIMIT :limit")
    List<Term> getAllTypes(String type, int limit);

    //if not all Lessons picked
    @Query("SELECT * FROM Term WHERE lesson IN(:lesson) ORDER BY RANDOM() LIMIT :limit")
    List<Term> getAllTermFromLesson(int[] lesson, int limit);
    @Query("SELECT * FROM Term WHERE type LIKE :type and lesson IN(:lesson) ORDER BY RANDOM() LIMIT :limit")
    List<Term> getAllTypeFromLesson(String type, int[] lesson, int limit);

    @Query("SELECT* FROM Term WHERE jpns LIKE :japanese OR eng LIKE :english")
    List<Term> getAllMatchedTerms(String japanese, String english);

    //Delete everything
    @Query("DELETE FROM Term")
    public void deleteAllTerms();


    @Delete
    void deleteTerm( Term term);


}
