package edu.cofc.japanesestudytool;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Terms
{
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String jpns;
    private String eng;
    private String kanji;
    private String type;
    private int[] chapters;
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


    public int[] getChapters() {
        return chapters;
    }

    public void setChapters(String input)
    {
        String[] inputs = input.split(";");
        chapters = new int[inputs.length];
        for( int i =0; i< chapters.length ; i++)
        {
            chapters[i] = Integer.parseInt(inputs[i]);
        }
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
