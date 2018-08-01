package edu.cofc.japanesestudytool;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Term
{
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String jpns;
    private String eng;
    private String kanji;
    private String type;
    /**Format for Lessons
     * ex. Lessons 1,3,5,7 = 1;3;5;7;
     * */
    private String lessons;
    private boolean reqKanji;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getJpns()
    {
        return jpns;
    }

    public void setJpns(String jpns)
    {
        this.jpns = jpns;
    }


    public String getKanji()
    {
        return kanji;
    }

    public void setKanji(String kanji)
    {
        this.kanji = kanji;
    }


    public String getEng()
    {
        return eng;
    }

    public void setEng(String eng)
    {
        this.eng = eng.toLowerCase();
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type.toLowerCase();
    }

    public String getLessons()
    {
        return lessons;
    }

    public void setLessons(String lessons) {
        this.lessons = lessons;
    }

    public boolean isReqKanji() {
        return reqKanji;
    }

    public void setReqKanji(String flag)
    {
        if( flag.equalsIgnoreCase("kanji"))
        {
            reqKanji = true;
        }
        else
        {
            reqKanji = false;
        }
    }


}
