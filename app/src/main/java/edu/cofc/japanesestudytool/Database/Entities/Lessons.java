package edu.cofc.japanesestudytool.Database.Entities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Lessons implements Serializable
{
    private ArrayList<Integer> lessons;

    public Lessons()
    {
        lessons= new ArrayList<>();
    }

    public void addLesson(int lesson)
    {
        if(!lessons.contains(lesson))
        {
            lessons.add(lesson);
        }
    }

    public void addLessons(Lessons newLessons)
    {
        ArrayList<Integer> newLessonsList = newLessons.getLessons();
        for(int i =0; i<newLessonsList.size();i++)
        {
            addLesson(newLessonsList.get(i));
        }
        Collections.sort(lessons);
    }

    public void removeLesson(int lesson)
    {
        lessons.remove(lessons.indexOf(lesson));
    }

    public void setLessons(ArrayList<Integer> lessons)
    {
        this.lessons = lessons;
    }

    public ArrayList<Integer> getLessons() {
        return lessons;
    }

    public String getLessonString()
    {
        String rtnval ="";

        for(int i=0; i<lessons.size();i++)
        {
            rtnval+= lessons.get(i).toString() +",";
        }
        rtnval = rtnval.substring(0,rtnval.length()-1);
        return rtnval;
    }
}
