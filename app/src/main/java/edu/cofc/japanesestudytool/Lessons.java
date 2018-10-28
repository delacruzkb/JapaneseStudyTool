package edu.cofc.japanesestudytool;

import java.util.ArrayList;

public class Lessons
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