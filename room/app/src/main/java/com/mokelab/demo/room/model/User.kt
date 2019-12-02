package com.mokelab.demo.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val _id: Int = 0,
    val name: String
)