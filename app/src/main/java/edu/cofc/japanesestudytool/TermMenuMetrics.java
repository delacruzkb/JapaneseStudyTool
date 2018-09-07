package edu.cofc.japanesestudytool;

import java.io.Serializable;

public class TermMenuMetrics implements Serializable
{
    boolean allTerms,japaneseFirst,kanji,lessonKanjiOnly,kanjiFirst, kanjiOnly;
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
        japaneseFirst = false;
        kanji = false;
        lessonKanjiOnly = false;
        kanjiFirst = false;
        kanjiOnly = false;
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
        return japaneseFirst;
    }

    public void setJapaneseFirst(boolean japaneseFirst) {
        this.japaneseFirst = japaneseFirst;
    }

    public boolean isKanji() {
        return kanji;
    }

    public void setKanji(boolean kanji) {
        this.kanji = kanji;
    }

    public boolean isLessonKanjiOnly() {
        return lessonKanjiOnly;
    }

    public void setLessonKanjiOnly(boolean lessonKanjiOnly) {
        this.lessonKanjiOnly = lessonKanjiOnly;
    }

    public boolean isKanjiFirst() {
        return kanjiFirst;
    }

    public void setKanjiFirst(boolean kanjiFirst) {
        this.kanjiFirst = kanjiFirst;
    }

    public boolean isKanjiOnly() {
        return kanjiOnly;
    }

    public void setKanjiOnly(boolean kanjiOnly) {
        this.kanjiOnly = kanjiOnly;
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
