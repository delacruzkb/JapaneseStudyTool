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

    //QueryTerms || query requested terms based on metrics
    @Query("SELECT * FROM Term WHERE type LIKE '%' || :type || '%' and lesson LIKE '%' || :lesson || '%' ORDER BY RANDOM() LIMIT :limit")
    List<Term> getAllTypeFromLessons(String type, String lesson, int limit);

    @Query("SELECT* FROM Term WHERE kanji NOT LIKE 'null' and type LIKE '%' || :type || '%'ORDER BY RANDOM() LIMIT :limit")
    List<Term> getKanjiOnly(String type, int limit);

    @Query("SELECT* FROM Term WHERE kanji NOT LIKE 'null' and type LIKE '%' || :type || '%' and lesson LIKE '%' || :lesson || '%' ORDER BY RANDOM() LIMIT :limit")
    List<Term> getKanjiOnlyFromLessons(String type, String lesson, int limit);

    @Query("SELECT* FROM Term WHERE reqKanji = 1 and kanji NOT LIKE 'null' and type LIKE '%' || :type || '%'ORDER BY RANDOM() LIMIT :limit")
    List<Term> getLessonKanjiOnly(String type, int limit);

    @Query("SELECT* FROM Term WHERE reqKanji = 1 and  kanji NOT LIKE 'null' and type LIKE '%' || :type || '%' and lesson LIKE '%' || :lesson || '%'  ORDER BY RANDOM() LIMIT :limit")
    List<Term> getLessonKanjiOnlyFromLessons(String type, String lesson, int limit);


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
