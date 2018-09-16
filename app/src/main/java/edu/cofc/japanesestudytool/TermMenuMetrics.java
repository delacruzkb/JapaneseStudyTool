package edu.cofc.japanesestudytool;

import java.io.Serializable;

public class TermMenuMetrics implements Serializable
{
    boolean allTerms,showJpnsFirst, showKanji, showKanjiFirst,
            showLessonKanjiOnly, useKanjiOnly,useLessonKanjiOnly;
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
        showJpnsFirst=false;
        showKanji=false;
        showKanjiFirst=false;
        showLessonKanjiOnly=false;
        useKanjiOnly=false;
        useLessonKanjiOnly = false;
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

    public boolean showJpnsFirst() {
        return showJpnsFirst;
    }

    public void setShowJpnsFirst(boolean showJpnsFirst) {
        this.showJpnsFirst = showJpnsFirst;
    }

    public boolean showKanji() {
        return showKanji;
    }

    public void setShowKanji(boolean showKanji) {
        this.showKanji = showKanji;
    }

    public boolean showKanjiFirst() {
        return showKanjiFirst;
    }

    public void setShowKanjiFirst(boolean showKanjiFirst) {
        this.showKanjiFirst = showKanjiFirst;
    }

    public boolean showLessonKanjiOnly() {
        return showLessonKanjiOnly;
    }

    public void setShowLessonKanjiOnly(boolean showLessonKanjiOnly) {
        this.showLessonKanjiOnly = showLessonKanjiOnly;
    }

    public boolean useKanjiOnly() {
        return useKanjiOnly;
    }

    public void setUseKanjiOnly(boolean useKanjiOnly) {
        this.useKanjiOnly = useKanjiOnly;
    }

    public boolean useLessonKanjiOnly() {
        return useLessonKanjiOnly;
    }

    public void setUseLessonKanjiOnly(boolean useLessonKanjiOnly) {
        this.useLessonKanjiOnly = useLessonKanjiOnly;
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
