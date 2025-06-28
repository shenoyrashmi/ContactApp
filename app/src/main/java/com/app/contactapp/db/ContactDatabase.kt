package com.app.contactapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.contactapp.dao.ContactDao
import com.app.contactapp.entities.Contact

@Database(entities = [Contact::class], version = 1, exportSchema = false)
abstract class ContactDatabase: RoomDatabase() {
    abstract fun contactDao(): ContactDao
}