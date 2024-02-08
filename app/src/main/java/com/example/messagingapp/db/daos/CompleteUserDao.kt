package com.example.messagingapp.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.messagingapp.db.entities.CompleteUserEntity
import com.example.messagingapp.db.entities.MessageEntity
import com.example.messagingapp.db.relations.ConversationWithMessages

@Dao
interface CompleteUserDao {
    @Query("SELECT * FROM users")
    fun getAllConversationsWithMessages(): List<ConversationWithMessages>

    @Transaction
    @Query("SELECT * FROM users WHERE userId = :userId ")
    fun getConversationWithMessagesBy(userId: String): List<ConversationWithMessages>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: CompleteUserEntity)

    @Delete
    fun deleteUser(user: CompleteUserEntity)
}