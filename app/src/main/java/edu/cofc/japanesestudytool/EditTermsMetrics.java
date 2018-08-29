package edu.cofc.japanesestudytool;

public class EditTermsMetrics
{
    String japanese;
    String english;
    String kanji;
    int lesson;
    String termType;
    String typeSpecial;
    boolean reqKanji;

    public EditTermsMetrics()
    {
        japanese="";
        english="";
        kanji="";
        lesson = 0;
        termType ="";
        typeSpecial="";
        reqKanji=false;
    }

    public String getJapanese() {
        return japanese;
    }

    public void setJapanese(String japanese) {
        this.japanese = japanese;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getKanji() {
        return kanji;
    }

    public void setKanji(String kanji) {
        this.kanji = kanji;
    }

    public int getLesson() {
        return lesson;
    }

    public void setLesson(int lesson) {
        this.lesson = lesson;
    }

    public String getTermType() {
        return termType;
    }

    public void setTermType(String termType) {
        this.termType = termType;
    }

    public String getTypeSpecial() {
        return typeSpecial;
    }

    public void setTypeSpecial(String typeSpecial) {
        this.typeSpecial = typeSpecial;
    }

    public boolean isReqKanji() {
        return reqKanji;
    }

    public void setReqKanji(boolean reqKanji) {
        this.reqKanji = reqKanji;
    }
}
