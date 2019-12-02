package com.mokelab.demo.room.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDAO(): UserDAO

    companion object {
        private const val DB_NAME = "user.db"
        private lateinit var instance: UserDatabase

        fun getInstance(context: Context): UserDatabase {
            if (!::instance.isInitialized) {
                instance = Room.databaseBuilder(
                    context, UserDatabase::class.java, DB_NAME
                ).build()
            }
            return instance
        }
    }
}