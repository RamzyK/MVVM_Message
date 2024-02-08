package com.example.messagingapp.model.user_model

data class FakeUserDto(
    val code: Int,
    val data: List<UserDataDto>,
    val status: String,
    val total: Int
)