package edu.cofc.japanesestudytool;

import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

import java.io.Serializable;


@Entity(primaryKeys = {"jpns","eng"})
public class Term implements Serializable
{
    @NonNull
    private String jpns;
    @NonNull
    private String eng;
    private String kanji;
    private String type;
    private Lessons lessons;
    private boolean reqKanji;
    private boolean checked;

    public Term()
    {
        jpns ="a";
        eng= "ア";
        kanji = "a";
        type = "u-verb";
        lessons = new Lessons();
        reqKanji=false;
        checked = false;
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

    public String getKanji()
    {
        String rtnval = kanji;
        if(rtnval==null)
        {
            rtnval="";
        }
        return rtnval;
    }

    public void setKanji(String kanji)
    {
        if(kanji == null || kanji.equalsIgnoreCase("null") || kanji.equalsIgnoreCase(""))
        {
            this.kanji=null;
        }
        else
        {
            this.kanji = kanji.toLowerCase();
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type.toLowerCase();
    }

    public Lessons getLessons() {
        return lessons;
    }

    public void setLessons(Lessons lessons) {
        this.lessons = lessons;
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
        if(jpns.equalsIgnoreCase(otherTerm.getJpns()) && eng.equalsIgnoreCase(otherTerm.getEng()))
        {
            rtnval = true;
        }

        return rtnval;

    }

    public String getLessonString()
    {
        return lessons.getLessonString();
    }

    public void addLesson(int lesson)
    {
        lessons.addLesson(lesson);
    }

    public void removeLesson(int lesson)
    {
        lessons.removeLesson(lesson);
    }

}
