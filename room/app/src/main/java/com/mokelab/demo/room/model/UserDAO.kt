package com.mokelab.demo.room.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDAO {
    @Query("select * from user")
    fun getAll(): LiveData<List<User>>

    @Insert
    fun create(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)
}