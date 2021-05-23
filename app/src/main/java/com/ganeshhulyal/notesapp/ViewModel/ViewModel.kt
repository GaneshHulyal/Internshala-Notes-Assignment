package com.ganeshhulyal.notesapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.ganeshhulyal.notesapp.db.NoteDatabase
import com.ganeshhulyal.notesapp.model.Note
import com.ganeshhulyal.notesapp.repo.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NoteRepository
    private var readAll: LiveData<List<Note>>

    init {
        val noteDB = NoteDatabase.getDatabase(application).daoAccess()
        repository = NoteRepository(noteDB)
        readAll = repository.getAlNotes()
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return readAll
    }

    fun addNotes(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNotes(note)
        }
    }

    fun updateTitle(id: Int, title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTitle(id, title)
        }
    }

    fun updateDescription(id: Int, desc: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateDescription(id, desc)
        }
    }
}