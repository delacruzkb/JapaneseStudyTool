package edu.cofc.japanesestudytool;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import java.io.Serializable;


@Entity
public class Term implements Serializable
{
    @PrimaryKey (autoGenerate = true)
    int id;

    private String jpns;
    private String eng;
    private String kanji;
    private String type;
    private int lesson;
    private boolean reqKanji;
    private boolean checked;

    public Term()
    {
        jpns ="a";
        eng= "ア";
        kanji = "a";
        type = "a";
        lesson = 0;
        reqKanji=false;
        checked = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJpns() {
        return jpns;
    }

    public void setJpns(String jpns) {
        this.jpns = jpns;
    }

    public String getEng() {
        return eng;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    public String getKanji() {
        return kanji;
    }

    public void setKanji(String kanji) {
        this.kanji = kanji;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public int getLesson() {
        return lesson;
    }

    public void setLesson(int lesson) {
        this.lesson = lesson;
    }

    public boolean isReqKanji() {
        return reqKanji;
    }

    public void setReqKanji(boolean reqKanji) {
        this.reqKanji = reqKanji;
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

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    public boolean equals(Object term)
    {
        boolean rtnval = false;
        Term otherTerm = (Term)term;
        if(this.getJpns().equalsIgnoreCase(otherTerm.getJpns())&&
                this.getEng().equals(otherTerm.getEng())&&
                this.getKanji().equals(otherTerm.getKanji()) &&
                ( this.getLesson() == otherTerm.getLesson() ))
        {
            rtnval = true;
        }

        return rtnval;

    }



}
