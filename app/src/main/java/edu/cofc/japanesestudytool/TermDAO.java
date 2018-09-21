package edu.cofc.japanesestudytool;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.provider.ContactsContract;

import java.util.List;


@Dao
public interface TermDAO
{
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertTerm(Term term);

    @Query("SELECT * FROM Term ORDER BY jpns")
    List<Term> getAllTerms();

    @Query("SELECT * FROM Term WHERE id LIKE :id")
    Term getTermById(String id);

    @Query("SELECT * FROM Term WHERE type LIKE '%' || :type || '%' ORDER BY RANDOM() LIMIT :limit")
    List<Term> getAllTypes(String type, int limit);

    @Query("SELECT* FROM Term WHERE kanji NOT LIKE '' and type LIKE '%' || :type || '%'ORDER BY RANDOM() LIMIT :limit")
    List<Term> getKanjiOnly(String type, int limit);

    @Query("SELECT* FROM Term WHERE reqKanji = 1 and kanji NOT LIKE '' and type LIKE '%' || :type || '%'ORDER BY RANDOM() LIMIT :limit")
    List<Term> getLessonKanjiOnly(String type, int limit);

    //Lesson Specific Queries
    @Query("SELECT * FROM Term WHERE type LIKE '%' || :type || '%' and lesson LIKE '%' || :lesson || '%' ORDER BY RANDOM() LIMIT :limit")
    List<Term> getAllTypeFromLessons(String type, int limit, String lesson);

    @Query("SELECT * FROM Term WHERE type LIKE '%' || :type || '%' " +
            "and (lesson LIKE '%' || :lesson || '%' " +
            "or lesson LIKE '%' || :lesson2 || '%' " +
            "or lesson LIKE '%' || :lesson3 || '%' " +
            "or lesson LIKE '%' || :lesson4 || '%' " +
            "or lesson LIKE '%' || :lesson5 || '%' " +
            "or lesson LIKE '%' || :lesson6 || '%' " +
            "or lesson LIKE '%' || :lesson7 || '%' " +
            "or lesson LIKE '%' || :lesson8 || '%' " +
            "or lesson LIKE '%' || :lesson9 || '%' " +
            "or lesson LIKE '%' || :lesson10 || '%' " +
            "or lesson LIKE '%' || :lesson11 || '%' " +
            "or lesson LIKE '%' || :lesson12 || '%' " +
            "or lesson LIKE '%' || :lesson13 || '%' " +
            "or lesson LIKE '%' || :lesson14 || '%' " +
            "or lesson LIKE '%' || :lesson15 || '%' " +
            "or lesson LIKE '%' || :lesson16 || '%' " +
            "or lesson LIKE '%' || :lesson17 || '%' " +
            "or lesson LIKE '%' || :lesson18 || '%' " +
            "or lesson LIKE '%' || :lesson19 || '%' " +
            "or lesson LIKE '%' || :lesson20 || '%' " +
            "or lesson LIKE '%' || :lesson21 || '%' " +
            "or lesson LIKE '%' || :lesson22 || '%' " +
            "or lesson LIKE '%' || :lesson23 || '%' " +
            "or lesson LIKE '%' || :lesson24 || '%' )" +
            "ORDER BY RANDOM() LIMIT :limit")
    List<Term> getAllTypeFromLessons(String type, int limit, String lesson, String lesson2, String lesson3,
                                     String lesson4, String lesson5, String lesson6,
                                     String lesson7, String lesson8, String lesson9,
                                     String lesson10, String lesson11, String lesson12,
                                     String lesson13, String lesson14, String lesson15,
                                     String lesson16, String lesson17, String lesson18,
                                     String lesson19, String lesson20, String lesson21,
                                     String lesson22, String lesson23, String lesson24);

    @Query("SELECT* FROM Term WHERE (kanji != '') and type LIKE '%' || :type || '%' and lesson LIKE '%' || :lesson || '%' ORDER BY RANDOM() LIMIT :limit")
    List<Term> getKanjiOnlyFromLessons(String type, int limit, String lesson);

    @Query("SELECT* FROM Term WHERE (kanji != '') and type LIKE '%' || :type || '%' " +
            "and (lesson LIKE '%' || :lesson || '%' " +
            "or lesson LIKE '%' || :lesson2 || '%' " +
            "or lesson LIKE '%' || :lesson3 || '%' " +
            "or lesson LIKE '%' || :lesson4 || '%' " +
            "or lesson LIKE '%' || :lesson5 || '%' " +
            "or lesson LIKE '%' || :lesson6 || '%' " +
            "or lesson LIKE '%' || :lesson7 || '%' " +
            "or lesson LIKE '%' || :lesson8 || '%' " +
            "or lesson LIKE '%' || :lesson9 || '%' " +
            "or lesson LIKE '%' || :lesson10 || '%' " +
            "or lesson LIKE '%' || :lesson11 || '%' " +
            "or lesson LIKE '%' || :lesson12 || '%' " +
            "or lesson LIKE '%' || :lesson13 || '%' " +
            "or lesson LIKE '%' || :lesson14 || '%' " +
            "or lesson LIKE '%' || :lesson15 || '%' " +
            "or lesson LIKE '%' || :lesson16 || '%' " +
            "or lesson LIKE '%' || :lesson17 || '%' " +
            "or lesson LIKE '%' || :lesson18 || '%' " +
            "or lesson LIKE '%' || :lesson19 || '%' " +
            "or lesson LIKE '%' || :lesson20 || '%' " +
            "or lesson LIKE '%' || :lesson21 || '%' " +
            "or lesson LIKE '%' || :lesson22 || '%' " +
            "or lesson LIKE '%' || :lesson23 || '%' " +
            "or lesson LIKE '%' || :lesson24 || '%' )" +
            "ORDER BY RANDOM() LIMIT :limit")
    List<Term> getKanjiOnlyFromLessons(String type, int limit, String lesson, String lesson2, String lesson3,
                                       String lesson4, String lesson5, String lesson6,
                                       String lesson7, String lesson8, String lesson9,
                                       String lesson10, String lesson11, String lesson12,
                                       String lesson13, String lesson14, String lesson15,
                                       String lesson16, String lesson17, String lesson18,
                                       String lesson19, String lesson20, String lesson21,
                                       String lesson22, String lesson23, String lesson24);

    @Query("SELECT* FROM Term WHERE reqKanji = 1 and  kanji NOT LIKE '' and type LIKE '%' || :type || '%' and lesson LIKE '%' || :lesson || '%'  ORDER BY RANDOM() LIMIT :limit")
    List<Term> getLessonKanjiOnlyFromLessons(String type,  int limit, String lesson);

    @Query("SELECT* FROM Term WHERE reqKanji = 1 and  kanji NOT LIKE '' and type LIKE '%' || :type || '%' " +
            "and (lesson LIKE '%' || :lesson || '%' " +
            "or lesson LIKE '%' || :lesson2 || '%' " +
            "or lesson LIKE '%' || :lesson3 || '%' " +
            "or lesson LIKE '%' || :lesson4 || '%' " +
            "or lesson LIKE '%' || :lesson5 || '%' " +
            "or lesson LIKE '%' || :lesson6 || '%' " +
            "or lesson LIKE '%' || :lesson7 || '%' " +
            "or lesson LIKE '%' || :lesson8 || '%' " +
            "or lesson LIKE '%' || :lesson9 || '%' " +
            "or lesson LIKE '%' || :lesson10 || '%' " +
            "or lesson LIKE '%' || :lesson11 || '%' " +
            "or lesson LIKE '%' || :lesson12 || '%' " +
            "or lesson LIKE '%' || :lesson13 || '%' " +
            "or lesson LIKE '%' || :lesson14 || '%' " +
            "or lesson LIKE '%' || :lesson15 || '%' " +
            "or lesson LIKE '%' || :lesson16 || '%' " +
            "or lesson LIKE '%' || :lesson17 || '%' " +
            "or lesson LIKE '%' || :lesson18 || '%' " +
            "or lesson LIKE '%' || :lesson19 || '%' " +
            "or lesson LIKE '%' || :lesson20 || '%' " +
            "or lesson LIKE '%' || :lesson21 || '%' " +
            "or lesson LIKE '%' || :lesson22 || '%' " +
            "or lesson LIKE '%' || :lesson23 || '%' " +
            "or lesson LIKE '%' || :lesson24 || '%' )" +
            "ORDER BY RANDOM() LIMIT :limit")
    List<Term> getLessonKanjiOnlyFromLessons(String type, int limit, String lesson, String lesson2, String lesson3,
                                             String lesson4, String lesson5, String lesson6,
                                             String lesson7, String lesson8, String lesson9,
                                             String lesson10, String lesson11, String lesson12,
                                             String lesson13, String lesson14, String lesson15,
                                             String lesson16, String lesson17, String lesson18,
                                             String lesson19, String lesson20, String lesson21,
                                             String lesson22, String lesson23, String lesson24);

    //AddNewTerm || ued to find similarities
    @Query("SELECT* FROM Term WHERE jpns LIKE '%' || :japanese || '%'OR eng LIKE '%' || :english|| '%'")
    List<Term> searchSimilarTerms(String japanese, String english);


    //LoadEditableTerms || used to search for a tem
    @Query("SELECT* FROM Term WHERE jpns LIKE '%' || :japanese || '%' ORDER BY jpns")
    List<Term> searchJpns(String japanese);

    @Query("SELECT* FROM Term WHERE eng LIKE  '%' || :english || '%' ORDER BY eng")
    List<Term> searchEng(String english);

    @Query("SELECT* FROM Term WHERE kanji LIKE '%' || :kanji || '%' ORDER BY jpns")
    List<Term> searchKanji(String kanji);

    @Query("SELECT* FROM Term WHERE jpns LIKE :japanese ORDER BY jpns")
    List<Term> searchExactJpns(String japanese);

    @Query("SELECT* FROM Term WHERE eng LIKE  :english ORDER BY eng")
    List<Term> searchExactEng(String english);

    @Query("SELECT* FROM Term WHERE kanji LIKE :kanji ORDER BY jpns")
    List<Term> searchExactKanji(String kanji);

    @Query("SELECT* FROM Term WHERE type LIKE :type ORDER BY jpns")
    List<Term> searchType(String type);

    @Query("SELECT* FROM Term WHERE reqKanji = :reqKanji ORDER BY jpns")
    List<Term> searchReqKanji(boolean reqKanji);

    @Query("SELECT* FROM Term WHERE lesson LIKE '%' || :lesson || '%'  ORDER BY jpns")
    List<Term> searchLesson(String lesson);

    //Delete everything
    @Query("DELETE FROM Term")
    void deleteAllTerms();


    @Delete
    void deleteTerm( Term term);


}
