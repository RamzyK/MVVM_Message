package com.example.messagingapp.model.conversation_model

data class FakeMessagesDataDto(
    val code: Int,
    val data: List<MessageDataDto>,
    val status: String,
    val total: Int
)