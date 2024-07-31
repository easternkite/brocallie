package com.blucky8649.room.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "callies")
data class CallieEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String = "",
    val personality: String = "",
    val job: String = "",
    val tone: String = "",
    val voice: String = "",
    val hobby: String = "",
    val gender: String = "",
    val age: Int = 0,
    val image: String = "",
)