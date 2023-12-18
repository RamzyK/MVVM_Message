package com.example.messagingapp.repositories

import com.example.messagingapp.db.AppDatabase
import com.example.messagingapp.db.entities.CompleteUser
import com.example.messagingapp.model.CompleteUserDto
import com.example.messagingapp.model.user_model.UserData
import com.example.messagingapp.network.services.UserApiService
import com.example.messagingapp.utils.mapCompleteUserDataFromServer
import io.reactivex.rxjava3.core.Flowable

class UsersRepository(
    private val userService: UserApiService,
    private val database: AppDatabase
) {
    fun getRandomListOfUsers(size: Int): Flowable<List<UserData>> {
        return userService.getOneRandomUserData(
            "fr_FR",
            size,
            "uuid",
            "counter",
            "name",
            "lastName",
            "date",
            "text",
            "phone",
            "boolean"
        ).map {
            it.data
        }
    }

    fun getLocalUserFromRoomDb(): List<CompleteUser> {
        return this.database.completeUserDao().getAll()
    }

    fun saveUsersToLocalStorage(users: List<CompleteUserDto>) {
        this.database.completeUserDao()
            .insertAll(
                users.map {
                    mapCompleteUserDataFromServer(it)
                }
            )
    }
}