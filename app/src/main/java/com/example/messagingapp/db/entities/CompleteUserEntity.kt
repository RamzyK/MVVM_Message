package com.example.messagingapp.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class CompleteUserEntity(
    @PrimaryKey(autoGenerate = false)
    val userId: String = "",
    @ColumnInfo(name = "user_name")
    val userName: String,
    @ColumnInfo(name = "user_last_name")
    val userLastName: String,
    @ColumnInfo(name = "user_phone_number")
    val phoneNumber: String,
    @ColumnInfo(name = "user_is_profile_pic_id")
    val profilePicId: Int,
    /*@ColumnInfo(name = "user_is_premium") val isUserPremium: Boolean,*/
    /*@ColumnInfo(name = "user_last_seen_date") val lastSeenDate: String,*/
    /*@ColumnInfo(name = "user_status_description") val statusDescription: String,*/
)
