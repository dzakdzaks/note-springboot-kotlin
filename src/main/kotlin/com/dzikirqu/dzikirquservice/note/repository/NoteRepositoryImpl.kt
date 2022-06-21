package com.dzikirqu.dzikirquservice.note.repository

import com.dzikirqu.dzikirquservice.database.DatabaseComponent
import com.dzikirqu.dzikirquservice.note.entity.Note
import com.mongodb.client.MongoCollection
import com.mongodb.client.model.Filters
import org.litote.kmongo.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.util.Date

@Repository
class NoteRepositoryImpl : NoteRepository {

    @Autowired
    private lateinit var databaseComponent: DatabaseComponent

    private val noteCollection: MongoCollection<Note> by lazy {
        databaseComponent.database.getCollection()
    }

    override fun getNoteById(id: String): Pair<Note?, String> {
        return try {
            noteCollection.findOneById(id) to ""
        } catch (e: Exception) {
            null to e.localizedMessage
        }
    }

    override fun getNotes(): Pair<List<Note>, String> {
        return try {
            noteCollection.find().toList() to ""
        } catch (e: Exception) {
            emptyList<Note>() to e.localizedMessage
        }
    }

    override fun addNote(note: Note): Pair<Note?, String> {
        return try {
            val insert = noteCollection.insertOne(note)
            return if (insert.wasAcknowledged()) {
                getNoteById(note.id)
            } else {
                null to ""
            }
        } catch (e: Exception) {
            null to e.localizedMessage
        }
    }

    override fun updateNote(id: String, note: Note): Pair<Note?, String> {
        return try {
            val result = noteCollection.findOneAndUpdate(
                Note::id eq id,
                set(Note::content setTo note.content, Note::updatedDate setTo Date())
            )
            if (result != null) {
                result to ""
            } else {
                null to "Note Not Found"
            }
        } catch (e: Exception) {
            null to e.localizedMessage
        }

    }

    override fun deleteNote(id: String): Pair<Boolean, String> {
        return try {
            val result = noteCollection.findOneAndDelete(Note::id eq id)
            if (result != null) {
                true to ""
            } else {
                false to "Note Not Found"
            }
        } catch (e: Exception) {
            false to e.localizedMessage
        }
    }
}