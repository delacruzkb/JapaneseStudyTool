package edu.cofc.japanesestudytool.Database.DAOs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import edu.cofc.japanesestudytool.Database.Entities.Lessons;
import edu.cofc.japanesestudytool.Database.Entities.Term;


@Dao
public interface TermDAO
{
    //Insert Term
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertTerm(Term term);
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    // Generic Queries
    //----------------------------------------------------------------------------------------------
    @Query("SELECT * " +
            "FROM Term " +
            "WHERE type " +
            "LIKE '%' || :type || '%' " +
            "ORDER BY RANDOM()")
    List<Term> getAllTypes(String type);

    @Query("SELECT * " +
            "FROM Term " +
            "WHERE type " +
            "LIKE '%' || :type || '%' " +
            "ORDER BY RANDOM() " +
            "LIMIT :limit")
    List<Term> getAllTypes(String type, int limit);

    @Query("SELECT * " +
            "FROM Term " +
            "WHERE kanji IS NOT NULL " +
            "and type LIKE '%' || :type || '%' " +
            "ORDER BY RANDOM()")
    List<Term> getKanjiOnly(String type);

    @Query("SELECT * " +
            "FROM Term " +
            "WHERE kanji IS NOT NULL " +
            "and type LIKE '%' || :type || '%' " +
            "ORDER BY RANDOM() " +
            "LIMIT :limit")
    List<Term> getKanjiOnly(String type, int limit);

    @Query("SELECT * " +
            "FROM Term " +
            "WHERE reqKanji = 1 " +
            "and kanji IS NOT NULL " +
            "and type LIKE '%' || :type || '%' " +
            "ORDER BY RANDOM()")
    List<Term> getLessonKanjiOnly(String type);

    @Query("SELECT * " +
            "FROM Term " +
            "WHERE reqKanji = 1 " +
            "and kanji IS NOT NULL " +
            "and type LIKE '%' || :type || '%' " +
            "ORDER BY RANDOM() " +
            "LIMIT :limit")
    List<Term> getLessonKanjiOnly(String type, int limit);
    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    // Lesson-based Queries
    //----------------------------------------------------------------------------------------------
    @Query("SELECT t.* " +
            "FROM Term t, LessonTerm lt " +
            "WHERE t.type LIKE '%' || :type || '%' " +
            "and lt.lesson IN(:lessons) " +
            "and t.jpns = lt.jpnsID " +
            "and t.eng = lt.engID " +
            "ORDER BY RANDOM()")
    List<Term> getAllTypesFromLessons(String type, int[] lessons);

    @Query("SELECT t.* " +
            "FROM Term t, LessonTerm lt " +
            "WHERE t.type LIKE '%' || :type || '%' " +
            "and lt.lesson IN(:lessons) " +
            "and t.jpns = lt.jpnsID " +
            "and t.eng = lt.engID " +
            "ORDER BY RANDOM() " +
            "LIMIT :limit")
    List<Term> getAllTypesFromLessons(String type, int[] lessons, int limit);

    @Query("SELECT t.* " +
            "FROM Term t, LessonTerm lt " +
            "WHERE t.kanji IS NOT NULL " +
            "and t.type LIKE '%' || :type || '%' " +
            "and lt.lesson IN (:lessons) " +
            "and t.eng = lt.engID " +
            "and t.jpns = lt.jpnsID "+
            "ORDER BY RANDOM()")
    List<Term> getKanjiOnlyFromLessons(String type,int[] lessons);

    @Query("SELECT t.* " +
            "FROM Term t, LessonTerm lt " +
            "WHERE t.kanji IS NOT NULL " +
            "and t.type LIKE '%' || :type || '%' " +
            "and lt.lesson IN (:lessons) " +
            "and t.eng = lt.engID " +
            "and t.jpns = lt.jpnsID "+
            "ORDER BY RANDOM() " +
            "LIMIT :limit")
    List<Term> getKanjiOnlyFromLessons(String type, int[] lessons,int limit);

    @Query("SELECT t.* " +
            "FROM Term t, LessonTerm lt " +
            "WHERE t.reqKanji = 1 " +
            "and t.kanji IS NOT NULL " +
            "and t.type LIKE '%' || :type || '%' " +
            "and lt.lesson IN (:lessons) " +
            "and t.eng = lt.engID " +
            "and t.jpns = lt.jpnsID "+
            "ORDER BY RANDOM()")
    List<Term> getLessonKanjiOnlyFromLessons(String type, int[] lessons);

    @Query("SELECT t.* " +
            "FROM Term t, LessonTerm lt " +
            "WHERE t.reqKanji = 1 " +
            "and t.kanji IS NOT NULL " +
            "and t.type LIKE '%' || :type || '%' " +
            "and lt.lesson IN (:lessons) " +
            "and t.eng = lt.engID " +
            "and t.jpns = lt.jpnsID "+
            "ORDER BY RANDOM() " +
            "LIMIT :limit")
    List<Term> getLessonKanjiOnlyFromLessons(String type, int[] lessons ,int limit);

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //Query to find Similar terms
    //----------------------------------------------------------------------------------------------
    @Query("SELECT * " +
            "FROM Term " +
            "WHERE jpns LIKE '%' || :japanese || '%' " +
            "or eng LIKE '%' || :english|| '%'")
    List<Term> searchSimilarTerms(String japanese, String english);

    //----------------------------------------------------------------------------------------------
    //----------------------------------------------------------------------------------------------
    //Query to find a term
    //----------------------------------------------------------------------------------------------
    @Query("SELECT * " +
            "FROM Term " +
            "WHERE jpns LIKE '%' || :japanese || '%' " +
            "ORDER BY jpns")
    List<Term> searchJpns(String japanese);

    @Query("SELECT * " +
            "FROM Term " +
            "WHERE eng LIKE  '%' || :english || '%' " +
            "ORDER BY eng")
    List<Term> searchEng(String english);

    @Query("SELECT * " +
            "FROM Term " +
            "WHERE kanji LIKE '%' || :kanji || '%' " +
            "ORDER BY jpns")
    List<Term> searchKanji(String kanji);

    @Query("SELECT * " +
            "FROM Term " +
            "WHERE jpns " +
            "LIKE :japanese " +
            "ORDER BY jpns")
    List<Term> searchExactJpns(String japanese);

    @Query("SELECT * " +
            "FROM Term " +
            "WHERE eng LIKE  :english " +
            "ORDER BY eng")
    List<Term> searchExactEng(String english);

    @Query("SELECT * " +
            "FROM Term " +
            "WHERE kanji LIKE :kanji " +
            "ORDER BY jpns")
    List<Term> searchExactKanji(String kanji);

    @Query("SELECT * " +
            "FROM Term " +
            "WHERE type LIKE :type " +
            "ORDER BY jpns")
    List<Term> searchType(String type);

    @Query("SELECT * " +
            "FROM Term " +
            "WHERE reqKanji = :reqKanji " +
            "ORDER BY jpns")
    List<Term> searchReqKanji(boolean reqKanji);

    @Query("SELECT t.* " +
            "FROM Term t, LessonTerm lt " +
            "WHERE lt.lesson = :lesson " +
            "and t.eng = lt.engID " +
            "and t.jpns = lt.jpnsID "+
            "ORDER BY jpns")
    List<Term> searchLesson(int lesson);

    //Delete everything
    @Query("DELETE FROM Term")
    void deleteAllTerms();

    //Delete Single Term
    @Delete
    void deleteTerm( Term term);
}
