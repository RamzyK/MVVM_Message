package com.example.messagingapp.model.user_model

import com.example.messagingapp.model.conversation_model.MessageData

data class UserData(
    val id: String,
    val profilePicture: Int,
    val isPremium: Boolean,
    val lastName: String,
    val lastSeenDate: String,
    val name: String,
    val phoneNumber: String,
    val statusDescription: String,
    val messages: List<MessageData>
)