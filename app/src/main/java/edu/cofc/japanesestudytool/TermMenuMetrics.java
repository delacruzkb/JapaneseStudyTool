package edu.cofc.japanesestudytool;

import java.io.Serializable;

public class TermMenuMetrics implements Serializable
{
    boolean allTerms,JapaneseFirst,Kanji,LessonKanjiOnly,KanjiFirst;
    int[] lessons;
    int nounCount, verbCount, adjectiveCount, otherCount, grammarCount;
    String mode;

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }


    public TermMenuMetrics()
    {
        allTerms = false;
        JapaneseFirst = false;
        Kanji = false;
        LessonKanjiOnly = false;
        KanjiFirst = false;
        lessons=null;
        nounCount =1;
        verbCount=1;

        adjectiveCount = 1;
        grammarCount =1;
        mode="mainMenu";
    }

    public boolean isAllTerms() {
        return allTerms;
    }

    public void setAllTerms(boolean allTerms) {
        this.allTerms = allTerms;
    }

    public boolean isJapaneseFirst() {
        return JapaneseFirst;
    }

    public void setJapaneseFirst(boolean JapaneseFirst) {
        this.JapaneseFirst = JapaneseFirst;
    }

    public boolean isKanji() {
        return Kanji;
    }

    public void setKanji(boolean Kanji) {
        this.Kanji = Kanji;
    }

    public boolean isLessonKanjiOnly() {
        return LessonKanjiOnly;
    }

    public void setLessonKanjiOnly(boolean LessonKanjiOnly) {
        this.LessonKanjiOnly = LessonKanjiOnly;
    }

    public boolean isKanjiFirst() {
        return KanjiFirst;
    }

    public void setKanjiFirst(boolean KanjiFirst) {
        this.KanjiFirst = KanjiFirst;
    }

    public int[] getLessons() {
        return lessons;
    }

    public void setLessons(int[] lessons) {
        this.lessons = lessons;
    }

    public int getNounCount() {
        return nounCount;
    }

    public void setNounCount(int nounCount) {
        this.nounCount = nounCount;
    }

    public int getVerbCount() {
        return verbCount;
    }

    public void setVerbCount(int verbCount) {
        this.verbCount = verbCount;
    }

    public int getAdjectiveCount() {
        return adjectiveCount;
    }

    public void setAdjectiveCount(int adjectiveCount) {
        this.adjectiveCount = adjectiveCount;
    }

    public int getOtherCount() {
        return otherCount;
    }

    public void setOtherCount(int otherCount) {
        this.otherCount = otherCount;
    }

    public int getGrammarCount() {
        return grammarCount;
    }

    public void setGrammarCount(int grammarCount) {
        this.grammarCount = grammarCount;
    }

}
