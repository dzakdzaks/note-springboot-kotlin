package com.dzikirqu.dzikirquservice.note.repository

import com.dzikirqu.dzikirquservice.note.entity.Note

interface NoteRepository {
    fun getNoteById(id: String): Pair<Note?, String>
    fun getNotes(): Pair<List<Note>, String>
    fun addNote(note: Note): Pair<Note?, String>
    fun updateNote(id: String, note: Note): Pair<Note?, String>
    fun deleteNote(id: String): Pair<Boolean, String>
}