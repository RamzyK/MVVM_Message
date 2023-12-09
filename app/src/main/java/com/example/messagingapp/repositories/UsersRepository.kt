package com.example.messagingapp.repositories

import com.example.messagingapp.model.user_model.UserData
import com.example.messagingapp.network.services.UserApiService
import io.reactivex.rxjava3.core.Flowable

class UsersRepository(
    private val userService: UserApiService
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
}