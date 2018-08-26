package edu.cofc.japanesestudytool;

import android.os.Parcel;
import android.os.Parcelable;

public class TermMenuMetrics implements Parcelable
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

    public TermMenuMetrics(Parcel in)
    {
        readFromParcel(in);
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        boolean[] switches = {allTerms,JapaneseFirst,Kanji,LessonKanjiOnly,KanjiFirst};
        dest.writeBooleanArray(switches);
        dest.writeInt(nounCount);
        dest.writeInt(verbCount);
        dest.writeInt(adjectiveCount);
        dest.writeInt(otherCount);
        dest.writeInt(grammarCount);
        dest.writeString(mode);
        dest.writeIntArray(lessons);
    }

    public static final Parcelable.Creator CREATOR =
            new Parcelable.Creator<TermMenuMetrics>() {
                public TermMenuMetrics createFromParcel(Parcel in) {
                    return new TermMenuMetrics(in);
                }

                public TermMenuMetrics[] newArray(int size) {
                    return new TermMenuMetrics[size];
                }
            };

    private void readFromParcel(Parcel in)
    {
        boolean[] switches= new boolean[5];
        in.readBooleanArray(switches);
        nounCount = in.readInt();
        verbCount =in.readInt();
        adjectiveCount =in.readInt();
        otherCount =in.readInt();
        grammarCount =in.readInt();
        mode = in.readString();
        if(lessons != null)
        {
            in.readIntArray(lessons);
        }

        allTerms = switches[0];
        JapaneseFirst = switches[1];
        Kanji = switches[2];
        LessonKanjiOnly = switches[3];
        KanjiFirst = switches[4];
    }
}
