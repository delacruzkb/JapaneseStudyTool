package edu.cofc.japanesestudytool;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity
public class Term
{
    @PrimaryKey @NonNull
    private String id;
    private String jpns;
    private String eng;
    private String kanji;
    private String type;
    /**Format for Lessons
     * ex. Lessons 1,3,5,7 = 1;3;5;7;
     * */
    private String lessons;
    private boolean reqKanji;

    public Term()
    {
        id = "アイディNULL";
        jpns ="a";
        eng= "ア";
        kanji = "a";
        type = "a";
        lessons = "234;";
        reqKanji=false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getJpns()
    {
        return jpns;
    }

    public void setJpns(String jpns)
    {
        this.jpns = jpns;
        renewId();
    }


    public String getKanji()
    {
        return kanji;
    }

    public void setKanji(String kanji)
    {
        this.kanji = kanji;
        renewId();
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

    public boolean equals(Object term)
    {
        boolean rtnval = false;
        Term otherTerm = (Term)term;
        if(this.getJpns().equalsIgnoreCase(otherTerm.getJpns())&&
                this.getEng().equals(otherTerm.getEng())&&
                this.getKanji().equals(otherTerm.getKanji()))
        {
            rtnval = true;
        }

        return rtnval;

    }
    private void renewId()
    {
        id = getJpns() + getKanji();
    }


}
