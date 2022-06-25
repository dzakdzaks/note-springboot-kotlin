package com.dzikirqu.dzikirquservice.database

import com.mongodb.client.MongoDatabase
import org.litote.kmongo.KMongo
import org.springframework.stereotype.Component

@Component
class DatabaseComponent {
    val database: MongoDatabase by lazy {
        val dbUrl = System.getenv("DATABASE_URL")
        KMongo.createClient(dbUrl).getDatabase("note_db")
    }
}