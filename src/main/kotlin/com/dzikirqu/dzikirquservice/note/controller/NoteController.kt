package com.dzikirqu.dzikirquservice.note.controller

import com.dzikirqu.dzikirquservice.base.BaseResponse
import com.dzikirqu.dzikirquservice.note.entity.Note
import com.dzikirqu.dzikirquservice.note.service.NoteService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("v1/note")
class NoteController {

    @Autowired
    private lateinit var noteService: NoteService

    @GetMapping("/{id}")
    fun getNoteById(
        @PathVariable(name = "id") id: String
    ): BaseResponse<Note> {
        val result = noteService.getNoteById(id = id)
        return if (result.first != null && result.second.isEmpty()) {
            BaseResponse(
                status = HttpStatus.OK.value(),
                message = "Get Note Success",
                data = result.first
            )
        } else {
            BaseResponse(
                status = HttpStatus.NOT_FOUND.value(),
                message = "Note Not Found",
                data = null
            )
        }
    }

    @GetMapping
    fun getNotes(): BaseResponse<List<Note>> {
        val result = noteService.getNotes()
        return BaseResponse(
            status = HttpStatus.OK.value(),
            message = "Get Notes Success",
            data = result.first
        )
    }

    @PostMapping
    fun addNote(
        @RequestBody request: Note
    ): BaseResponse<Note> {
        val result = noteService.addNote(note = request)
        return if (result.first != null && result.second.isEmpty()) {
            BaseResponse(
                status = HttpStatus.OK.value(),
                message = "Add Note Success",
                data = result.first
            )
        } else {
            BaseResponse(
                status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message = "Add Note Failed : ${result.second}",
                data = result.first
            )
        }
    }

    @PutMapping("/{id}")
    fun updateNote(
        @PathVariable(name = "id") id: String,
        @RequestBody request: Note
    ): BaseResponse<Note> {
        val result = noteService.updateNote(id = id, note = request)
        return if (result.first != null && result.second.isEmpty()) {
            BaseResponse(
                status = HttpStatus.OK.value(),
                message = "Update Note Success",
                data = result.first
            )
        } else {
            BaseResponse(
                status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message = "Update Note Failed : ${result.second}",
                data = null
            )
        }
    }

    @DeleteMapping("/{id}")
    fun deleteNote(
        @PathVariable(name = "id") id: String
    ): BaseResponse<String> {
        val result = noteService.deleteNote(id = id)
        return if (result.first != null && result.second.isEmpty()) {
            BaseResponse(
                status = HttpStatus.OK.value(),
                message = "${result.first?.content} Deleted",
                data = null
            )
        } else {
            BaseResponse(
                status = HttpStatus.INTERNAL_SERVER_ERROR.value(),
                message = "Delete Note Failed : ${result.second}",
                data = null
            )
        }

    }

}