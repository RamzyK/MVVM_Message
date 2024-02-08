package com.example.messagingapp.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message")
data class Messages(
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    @ColumnInfo(name = "is_mine") val isMyMessage: Boolean,
    val userId: String,
    @ColumnInfo(name = "message_content") val content: String
)
