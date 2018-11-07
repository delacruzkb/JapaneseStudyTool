package edu.cofc.japanesestudytool.Database.Converters;

import android.arch.persistence.room.TypeConverter;

import java.util.ArrayList;

import edu.cofc.japanesestudytool.Database.Entities.Lessons;

public class LessonsConverter
{
    private static int letterOffset= 'a';
    @TypeConverter
    public static Lessons toLessons(String value)
    {
        Lessons lessons = new Lessons();

        for(int i=0;i<value.length();i++)
        {
            char ch = value.charAt(i);
            lessons.addLesson((ch - letterOffset));
        }
        return lessons;
    }

    @TypeConverter
    public static String toString(Lessons value)
    {
        ArrayList<Integer> lessons= value.getLessons();
        String lessonString="";

        for (int i=0; i< lessons.size();i++)
        {
            lessonString+= String.valueOf(Character.toChars(lessons.get(i) + letterOffset));
        }

        return lessonString;
    }
}
