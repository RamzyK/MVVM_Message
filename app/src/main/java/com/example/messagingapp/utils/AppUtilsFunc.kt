package com.example.messagingapp.utils

import com.example.messagingapp.db.entities.CompleteUser
import com.example.messagingapp.db.entities.Messages
import com.example.messagingapp.model.CompleteUserDto
import com.example.messagingapp.model.conversation_model.MessageData
import com.example.messagingapp.model.user_model.UserData

fun mapDataFromDatabase(userLocalData: CompleteUser): CompleteUserDto {
    val infos = UserData(
        userLocalData.userId,
        userLocalData.profilePicId,
        userLocalData.isUserPremium,
        userLocalData.userLastName,
        userLocalData.userName,
        userLocalData.lastSeenDate,
        userLocalData.statusDescription,
        userLocalData.phoneNumber,
        listOf()
    )

    //val messages = mapMessagesLocalData(userLocalData.messagesList)

    return CompleteUserDto(infos, mutableListOf())
}

fun mapMessagesLocalData(messages: List<Messages>): MutableList<MessageData> {
    return messages.map {
        MessageData(
            it.isMyMessage,
            it.content
        )
    }.toMutableList()
}

fun mapCompleteUserDataFromServer(userRemoteData: CompleteUserDto): CompleteUser {
    return CompleteUser(
        userId = userRemoteData.infos.id,
        isUserPremium = userRemoteData.infos.isPremium,
        userName = userRemoteData.infos.name,
        profilePicId = userRemoteData.infos.profilePicture,
        userLastName = userRemoteData.infos.lastName,
        lastSeenDate = userRemoteData.infos.lastSeenDate,
        phoneNumber = userRemoteData.infos.phoneNumber,
        statusDescription = userRemoteData.infos.statusDescription,
        //messagesList = mapMessagesRemoteData(userRemoteData.infos.messages)
    )
}

/*fun mapMessagesRemoteData(messages: List<MessageData>): List<Messages> {
    return messages.map {
        Messages(
            isMyMessage = it.isMyMessage,
            content = it.message
        )
    }
}*/
