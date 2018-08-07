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

    @Query("SELECT * FROM Term")
    List<Term> getAllTerms();

    @Query("SELECT * FROM Term WHERE lesson = :lesson")
    List<Term> getFromLesson(int lesson);

    @Query("SELECT * FROM Term WHERE reqKanji=:bool")
    List<Term> getRequiredKanji(boolean bool);

    @Query("SELECT * FROM Term WHERE type LIKE :type")
    List<Term> getTermType(String type);

    @Query("SELECT * FROM Term WHERE type LIKE :type and lesson = :lesson")
    List<Term> getTermTypeFromLesson(String type, int lesson);

    @Query("SELECT * FROM Term WHERE type LIKE :type and lesson = :lesson and reqKanji = :reqKanji")
    List<Term> getAllRequiredKanjiType(String type, boolean reqKanji);

    @Query("SELECT * FROM Term WHERE type LIKE :type and lesson = :lesson and reqKanji = :reqKanji")
    List<Term> getSpecificTerms(String type, int lesson, boolean reqKanji);

    @Delete
    void deleteTerm( Term term);
}
