package com.example.messagingapp.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.messagingapp.db.entities.CompleteUser

@Dao
interface CompleteUserDao {
    @Query("SELECT * FROM complete_users")
    fun getAll(): List<CompleteUser>

    @Query("SELECT * FROM complete_users WHERE user_id = :userId ")
    fun getUserById(userId: String): CompleteUser

    @Insert
    fun insertAll(users: List<CompleteUser>)

    @Delete
    fun delete(user: CompleteUser)
}