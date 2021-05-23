package com.ganeshhulyal.notesapp.repo
import androidx.lifecycle.LiveData
import com.ganeshhulyal.notesapp.dao.DaoAccess
import com.ganeshhulyal.notesapp.model.Note

class NoteRepository(private val daoAccess: DaoAccess) {

    suspend fun insertNotes(note: Note) {
        daoAccess.insertTask(note)
    }

    suspend fun deleteNotes(id: Int) {
        daoAccess.deleteByNoteId(id)
    }

    suspend fun updateTitle(id: Int, newTitle: String) {
        daoAccess.updateTitle(id, newTitle)
    }

    suspend fun updateDescription(id: Int, note: String) {
        daoAccess.updateDescription(id, note)
    }

    fun getAlNotes(): LiveData<List<Note>> = daoAccess.fetchAllNotes()
}