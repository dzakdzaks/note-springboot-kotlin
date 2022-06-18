package com.dzikirqu.dzikirquservice.note.entity

import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.newId
import java.util.*

data class Note(
    @BsonId
    var id: String = newId<Note>().toString(),
    var content: String,
    var createdDate: Date = Date(),
    var updatedDate: Date = Date()

)