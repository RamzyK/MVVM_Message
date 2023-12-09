package com.example.messagingapp.model

import com.example.messagingapp.model.conversation_model.MessageData
import com.example.messagingapp.model.user_model.UserData


data class CompleteUserDto(
    val infos: UserData,
    val conversations: MutableList<MessageData>
) {
    fun getFormattedConversationCount(): String = if (this.conversations.size > 99)  "+99" else  this.conversations.size.toString()

    fun getFormattedFullUserName(): String = this.infos.lastName + " " + this.infos.name
}
