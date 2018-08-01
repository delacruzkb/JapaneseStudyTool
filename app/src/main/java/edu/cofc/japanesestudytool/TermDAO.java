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
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTerm(Term term);

    @Query("SELECT * FROM Term")
    List<Term> getAllTerms();

    @Query("SELECT * FROM Term WHERE lessons LIKE :lesson")
    List<Term> getFromLessons(String lesson);

    @Query("SELECT * FROM Term WHERE reqKanji=:bool")
    List<Term> getRequiredKanji(boolean bool);

    @Query("SELECT * FROM Term WHERE type LIKE :type")
    List<Term> getTermType(String type);

    @Delete
    void deleteTerm( Term term);
}
