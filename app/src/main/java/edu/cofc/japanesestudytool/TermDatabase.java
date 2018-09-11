package edu.cofc.japanesestudytool;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;

@Database(entities = {Term.class},version=1)
public abstract class TermDatabase extends RoomDatabase
{
    public abstract TermDAO termDAO();
}
