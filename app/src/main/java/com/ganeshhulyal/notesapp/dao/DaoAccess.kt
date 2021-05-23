package com.ganeshhulyal.notesapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ganeshhulyal.notesapp.model.Note


@Dao
interface DaoAccess {
    @Insert
    suspend fun insertTask(note: Note?): Long?

    @Query("SELECT * FROM Note ORDER BY createTimeStamp desc")
    fun fetchAllNotes(): LiveData<List<Note>>

    @Query("UPDATE Note SET title = :title WHERE id =:id")
    suspend fun updateTitle(id: Int, title: String)

    @Query("UPDATE Note SET description = :note WHERE id =:id")
    suspend fun updateDescription(id: Int, note: String)

    @Query("DELETE FROM Note WHERE id = :id")
    suspend fun deleteByNoteId(id: Int)

}