package edu.cofc.japanesestudytool;

import android.arch.persistence.room.Entity;

@Entity(primaryKeys = {"lesson,jpnsID,engID"})
public class LessonTerm
{
    private int lesson;
    private String jpnsID;
    private String engID;

    public LessonTerm(int lesson, String jpnsID, String engID) {
        this.lesson = lesson;
        this.jpnsID = jpnsID;
        this.engID = engID;
    }

    public int getLesson() {
        return lesson;
    }

    public void setLesson(int lesson) {
        this.lesson = lesson;
    }

    public String getJpnsID() {
        return jpnsID;
    }

    public void setJpnsID(String jpnsID) {
        this.jpnsID = jpnsID;
    }

    public String getEngID() {
        return engID;
    }

    public void setEngID(String engID) {
        this.engID = engID;
    }

    @Override
    public boolean equals(Object obj)
    {
        boolean rtnval = false;
        LessonTerm otherLessonTerm = (LessonTerm)obj;
        if(otherLessonTerm.getEngID().equalsIgnoreCase(engID) &&
                otherLessonTerm.getJpnsID().equalsIgnoreCase(jpnsID) &&
                otherLessonTerm.getLesson() == lesson)
        {
            rtnval = true;
        }

        return rtnval;

    }
}
