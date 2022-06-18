package com.dzikirqu.dzikirquservice.note.service

import com.dzikirqu.dzikirquservice.note.entity.Note
import com.dzikirqu.dzikirquservice.note.repository.NoteRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NoteServiceImpl: NoteService {

    @Autowired
    private lateinit var noteRepository: NoteRepository

    override fun getNoteById(id: String): Pair<Note?, String> {
        return noteRepository.getNoteById(id)
    }

    override fun getNotes(): Pair<List<Note>, String> {
        return noteRepository.getNotes()
    }

    override fun addNote(note: Note): Pair<Note?, String> {
        return noteRepository.addNote(note)
    }

    override fun updateNote(id: String, note: Note): Pair<Note?, String> {
        return noteRepository.updateNote(id, note)
    }

    override fun deleteNote(id: String): Pair<Boolean, String> {
        return noteRepository.deleteNote(id)
    }
}