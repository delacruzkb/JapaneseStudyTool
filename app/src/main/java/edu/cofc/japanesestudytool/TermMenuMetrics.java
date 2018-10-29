package edu.cofc.japanesestudytool;

import java.io.Serializable;
import java.util.ArrayList;

public class TermMenuMetrics implements Serializable
{

    private ArrayList<Term> nounList, verbList, adjectiveList, grammarList, otherList,termList;
    private boolean countAll,allTerms,showJpnsFirst, showKanjiFirst,
            showLessonKanjiOnly, useKanjiOnly,useLessonKanjiOnly;
    private ArrayList<Integer> lessons;
    private int nounCount, verbCount, adjectiveCount, otherCount, grammarCount;
    private String mode;



    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }


    public TermMenuMetrics()
    {
        countAll=true;
        allTerms = true;
        showJpnsFirst=false;
        showKanjiFirst=false;
        showLessonKanjiOnly=false;
        useKanjiOnly=false;
        useLessonKanjiOnly = false;
        lessons=null;
        nounCount =0;
        verbCount=0;

        adjectiveCount = 0;
        grammarCount =0;
        mode="mainMenu";
    }

    public ArrayList<Term> getNounList() {
        return nounList;
    }

    public void setNounList(ArrayList<Term> nounList) {
        this.nounList = nounList;
    }

    public ArrayList<Term> getVerbList() {
        return verbList;
    }

    public void setVerbList(ArrayList<Term> verbList) {
        this.verbList = verbList;
    }

    public ArrayList<Term> getAdjectiveList() {
        return adjectiveList;
    }

    public void setAdjectiveList(ArrayList<Term> adjectiveList) {
        this.adjectiveList = adjectiveList;
    }

    public ArrayList<Term> getGrammarList() {
        return grammarList;
    }

    public void setGrammarList(ArrayList<Term> grammarList) {
        this.grammarList = grammarList;
    }

    public ArrayList<Term> getOtherList() {
        return otherList;
    }

    public void setOtherList(ArrayList<Term> otherList) {
        this.otherList = otherList;
    }

    public ArrayList<Term> getTermList() {
        return termList;
    }

    public void setTermList(ArrayList<Term> termList) {
        this.termList = termList;
    }

    public boolean isCountAll() {
        return countAll;
    }

    public void setCountAll(boolean countAll) {
        this.countAll = countAll;
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

    public ArrayList<Integer> getLessons() {
        return lessons;
    }

    public int[] getLessonsArray()
    {
        int[] rtnval = new int[lessons.size()];
        for(int i =0; i<rtnval.length;i++)
        {
            rtnval[i] = lessons.get(i);
        }
        return rtnval;
    }
    public void setLessons(ArrayList<Integer> lessons) {
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
