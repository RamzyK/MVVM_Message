package com.example.messagingapp.model.conversation_model

data class FakeMessagesData(
    val code: Int,
    val data: List<MessageData>,
    val status: String,
    val total: Int
)