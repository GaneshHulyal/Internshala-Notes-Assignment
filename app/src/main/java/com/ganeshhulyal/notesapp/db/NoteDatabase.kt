package com.ganeshhulyal.notesapp.db


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ganeshhulyal.notesapp.constants.Constants
import com.ganeshhulyal.notesapp.dao.DaoAccess
import com.ganeshhulyal.notesapp.model.Note


@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun daoAccess(): DaoAccess

    companion object {

        @Volatile
        private var INSTANCE: NoteDatabase? = null

        fun getDatabase(context: Context): NoteDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    Constants.DB_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}