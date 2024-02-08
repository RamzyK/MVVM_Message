package com.example.messagingapp.utils

import com.example.messagingapp.db.entities.CompleteUserEntity
import com.example.messagingapp.db.entities.MessageEntity
import com.example.messagingapp.db.relations.ConversationWithMessages
import com.example.messagingapp.model.CompleteUserDto
import com.example.messagingapp.model.conversation_model.MessageDataDto
import com.example.messagingapp.model.user_model.UserDataDto

fun mapConversationsWithMessagesRelationToCompleteUserDto(conversations: ConversationWithMessages): CompleteUserDto {
    val infos = UserDataDto(
        conversations.completeUserEntity.userId,
        conversations.completeUserEntity.profilePicId,
        conversations.completeUserEntity.userName,
        conversations.completeUserEntity.userLastName,
        conversations.completeUserEntity.phoneNumber
    )

    return CompleteUserDto(infos, mapMessagesEntitiesToMessagesDtos(conversations.messages))
}

fun mapMessagesEntitiesToMessagesDtos(messages: List<MessageEntity>): MutableList<MessageDataDto> {
    return messages.map {
        MessageDataDto(
            it.isMyMessage,
            it.content
        )
    }.toMutableList()
}

fun mapMessagesFromRemoteToRoomMessagesEntities(userId: String, messageDatumModels: List<MessageDataDto>): List<MessageEntity> {
    return messageDatumModels.map {
        MessageEntity(
            isMyMessage = it.isMyMessage,
            userId = userId,
            content = it.message
        )
    }
}

fun mapOneMessageDtoToRoomDbEntity(userId: String, message: MessageDataDto): MessageEntity {
    return MessageEntity(
        isMyMessage = message.isMyMessage,
        content = message.message,
        userId = userId
    )
}


fun mapCompleteUserFromRemoteToRoomEntity(userRemoteData: CompleteUserDto): CompleteUserEntity {

    return CompleteUserEntity(
        userId = userRemoteData.infos.id,
        userName = userRemoteData.infos.name,
        profilePicId = userRemoteData.infos.profilePicture,
        userLastName = userRemoteData.infos.lastName,
        phoneNumber = userRemoteData.infos.phoneNumber
    )
}

