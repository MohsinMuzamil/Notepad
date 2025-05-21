package com.mohsin.notesapp.repository

import androidx.lifecycle.LiveData
import com.mohsin.notesapp.data.Note
import com.mohsin.notesapp.data.NoteDao

class NoteRepository(private val noteDao: NoteDao) {
    val allNotes: LiveData<List<Note>> = noteDao.getAllNotes()

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }

    suspend fun delete(note: Note) {
        noteDao.delete(note)
    }
}