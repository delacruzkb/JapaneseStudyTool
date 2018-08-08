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
        //if kanji option selected
    @Query("SELECT * FROM Term WHERE type LIKE :type")
    List<Term> getAllTypes(String type);
        //if kanji option is deselected
    @Query("SELECT * FROM Term WHERE type LIKE :type and kanji = NULL")
    List<Term> getHiraganaType(String type);

    //if not all Lessons picked
        //if kanji option selected
    @Query("SELECT * FROM Term WHERE type LIKE :type and lesson = :lesson")
    List<Term> getAllTypeFromLesson(String type, int lesson);
        //if kanji option is deselected
    @Query("SELECT * FROM Term WHERE type LIKE :type and kanji = NULL and lesson = :lesson")
    List<Term> getHiraganaTypeFromLesson(String type, int lesson);

    @Delete
    void deleteTerm( Term term);
}
