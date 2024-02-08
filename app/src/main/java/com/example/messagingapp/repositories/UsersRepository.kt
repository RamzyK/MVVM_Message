package com.example.messagingapp.repositories

import com.example.messagingapp.db.AppDatabase
import com.example.messagingapp.db.relations.ConversationWithMessages
import com.example.messagingapp.model.CompleteUserDto
import com.example.messagingapp.model.conversation_model.MessageDataDto
import com.example.messagingapp.model.user_model.UserDataDto
import com.example.messagingapp.network.services.UserApiService
import com.example.messagingapp.utils.mapCompleteUserFromRemoteToRoomEntity
import com.example.messagingapp.utils.mapOneMessageDtoToRoomDbEntity
import io.reactivex.rxjava3.core.Flowable

class UsersRepository(
    private val userService: UserApiService,
    private val database: AppDatabase
) {
    fun getRandomListOfUsers(size: Int): Flowable<List<UserDataDto>> {
        return userService.getOneRandomUserData(
            "fr_FR",
            size,
            "uuid",
            "counter",
            "name",
            "lastName",
            "phone"
        ).map {
            it.data
        }
    }

    fun getLocalUserFromRoomDb(): List<ConversationWithMessages> {
        return this.database.completeUserDao().getAllConversationsWithMessages()
    }

    fun saveUsersToLocalStorage(users: List<CompleteUserDto>) {
        users.forEach {
            this.database.completeUserDao().insertUser(
                mapCompleteUserFromRemoteToRoomEntity(it)
            )
            this.saveUserConversationMessagesToRoom(it.infos.id, it.conversations)
        }
    }

    private fun saveUserConversationMessagesToRoom(userId: String, messages: List<MessageDataDto>) {
        messages.forEach {
            this.database
                .messagesDao()
                .insertMessage(
                    mapOneMessageDtoToRoomDbEntity(userId, it)
                )
        }
    }
}