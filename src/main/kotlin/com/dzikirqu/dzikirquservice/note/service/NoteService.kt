package com.dzikirqu.dzikirquservice.note.service

import com.dzikirqu.dzikirquservice.note.entity.Note

interface NoteService {
    fun getNoteById(id: String): Pair<Note?, String>
    fun getNotes(): Pair<List<Note>, String>
    fun addNote(note: Note): Pair<Note?, String>
    fun updateNote(id: String, note: Note): Pair<Note?, String>
    fun deleteNote(id: String): Pair<Note?, String>
}