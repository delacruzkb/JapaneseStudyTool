package edu.cofc.japanesestudytool;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;


@Entity
public class Term implements Parcelable
{
    @PrimaryKey @NonNull
    private String id;
    private String jpns;
    private String eng;
    private String kanji;
    private String type;
    private int lesson;
    private boolean reqKanji;

    public Term()
    {
        id = "アイディIDNULL0";
        jpns ="a";
        eng= "ア";
        kanji = "a";
        type = "a";
        lesson = 0;
        reqKanji=false;
    }
    public Term(Parcel in)
    {
        readFromParcel(in);
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {

        int i = 0;
        if(reqKanji)
        {
            i=1;
        }
        dest.writeString(id);
        dest.writeString(jpns);
        dest.writeString(eng);
        dest.writeString(kanji);
        dest.writeString(type);
        dest.writeInt(lesson);
        dest.writeInt(i);
    }
    private void readFromParcel(Parcel in) {

        id = in.readString();
        jpns =in.readString();
        eng= in.readString();
        kanji = in.readString();
        type = in.readString();
        lesson = in.readInt();
        reqKanji=(in.readInt() == 1);
    }
    public static final Parcelable.Creator CREATOR =
            new Parcelable.Creator() {
                public Term createFromParcel(Parcel in) {
                    return new Term(in);
                }

                public Term[] newArray(int size) {
                    return new Term[size];
                }
            };
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

    public int getLesson()
    {
        return lesson;
    }

    public void setLesson(int lesson)
    {
        this.lesson = lesson;
    }

    public void setLesson(String lesson)
    {
        this.lesson = Integer.parseInt(lesson);
    }

    public boolean getReqKanji() {
        return reqKanji;
    }

    public void setReqKanji(boolean flag)
    {
        reqKanji = flag;
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
                this.getKanji().equals(otherTerm.getKanji()) &&
                ( this.getLesson() == otherTerm.getLesson() ))
        {
            rtnval = true;
        }

        return rtnval;

    }


    private void renewId()
    {
        id = getJpns() + getEng()+ getKanji() + getLesson();
    }


}
