package com.example.messagingapp.model.user_model

data class UserDataDto(
    val id: String,
    val profilePicture: Int,
    val lastName: String,
    val name: String,
    val phoneNumber: String,
)