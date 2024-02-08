package com.example.messagingapp.model

import com.example.messagingapp.model.conversation_model.MessageDataDto
import com.example.messagingapp.model.user_model.UserDataDto


data class CompleteUserDto(
    val infos: UserDataDto,
    val conversations: MutableList<MessageDataDto>
) {
    fun getFormattedConversationCount(): String = if (this.conversations.size > 99)  "+99" else  this.conversations.size.toString()

    fun getFormattedFullUserName(): String = this.infos.lastName + " " + this.infos.name
}
