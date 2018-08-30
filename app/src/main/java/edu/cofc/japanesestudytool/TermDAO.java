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


    @Query("SELECT * FROM Term WHERE type LIKE :type ORDER BY RANDOM() LIMIT :limit")
    List<Term> getAllTypes(String type, int limit);

    @Query("SELECT * FROM Term WHERE type LIKE :type and lesson IN(:lesson) ORDER BY RANDOM() LIMIT :limit")
    List<Term> getAllTypeFromLessons(String type, int[] lesson, int limit);

    @Query("SELECT* FROM Term WHERE jpns LIKE :japanese OR eng LIKE :english")
    List<Term> getAllMatchedTerms(String japanese, String english);


    @Query("SELECT* FROM Term WHERE jpns LIKE :japanese")
    List<Term> searchJpns(String japanese);

    @Query("SELECT* FROM Term WHERE eng LIKE :english")
    List<Term> searchEng(String english);

    @Query("SELECT* FROM Term WHERE kanji LIKE :kanji")
    List<Term> searchKanji(String kanji);

    @Query("SELECT* FROM Term WHERE type LIKE :type")
    List<Term> searchType(String type);

    @Query("SELECT* FROM Term WHERE type LIKE :type AND typeSpecial LIKE :typeSpecial")
    List<Term> searchType(String type, String typeSpecial);

    @Query("SELECT* FROM Term WHERE reqKanji = :reqKanji")
    List<Term> searchReqKanji(boolean reqKanji);

    @Query("SELECT* FROM Term WHERE lesson = :lesson")
    List<Term> searchLesson(int lesson);



    //Delete everything
    @Query("DELETE FROM Term")
    public void deleteAllTerms();


    @Delete
    void deleteTerm( Term term);


}
