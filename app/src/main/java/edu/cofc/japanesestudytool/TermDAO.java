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
    @Query("SELECT * FROM Term WHERE type LIKE :type ORDER BY RANDOM() LIMIT :limit")
    List<Term> getAllTypes(String type, int limit);
        //if kanji option is deselected
    @Query("SELECT * FROM Term WHERE type LIKE :type and kanji = NULL ORDER BY RANDOM() LIMIT :limit")
    List<Term> getHiraganaType(String type, int limit);

    //if not all Lessons picked
        //if kanji option selected
    @Query("SELECT * FROM Term WHERE type LIKE :type and lesson = :lesson ORDER BY RANDOM() LIMIT :limit")
    List<Term> getAllTypeFromLesson(String type, int lesson, int limit);
        //if kanji option is deselected
    @Query("SELECT * FROM Term WHERE type LIKE :type and kanji = NULL and lesson = :lesson ORDER BY RANDOM() LIMIT :limit")
    List<Term> getHiraganaTypeFromLesson(String type, int lesson, int limit);

    //Delete everything
    @Query("DELETE FROM Term")
    public void deleteAllTerms();


    @Delete
    void deleteTerm( Term term);


}
