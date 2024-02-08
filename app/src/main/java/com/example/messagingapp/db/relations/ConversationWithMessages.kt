package com.example.messagingapp.db.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.messagingapp.db.entities.CompleteUserEntity
import com.example.messagingapp.db.entities.MessageEntity

data class ConversationWithMessages(
    @Embedded val completeUserEntity: CompleteUserEntity,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userId"
    )
    val messages: List<MessageEntity>
)
