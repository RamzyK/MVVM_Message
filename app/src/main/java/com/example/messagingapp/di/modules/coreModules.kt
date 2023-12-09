package com.example.messagingapp.di.modules

import com.example.messagingapp.network.services.MessagesApiService
import com.example.messagingapp.network.services.UserApiService
import com.example.messagingapp.repositories.ConversationRepository
import com.example.messagingapp.repositories.UsersRepository
import com.example.messagingapp.viewmodel.UsersViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


internal val coreModules = module {
    // Inject a singleton for the user repo
    single { UsersRepository(get()) }
    single { ConversationRepository(get()) }


    // Inject user view model
    single { UsersViewModel(get(), get()) }

    // Webservices
    single {
        createWebService<UserApiService>(
            get(
                named(fakeApiRetrofitClient)
            )
        )
    }

    single {
        createWebService<MessagesApiService>(
            get(
                named(fakeApiRetrofitClient)
            )
        )
    }
}

// Class representing the configuration to parse from the gradle file
data class FakerJsonConf(
    val baseUrl: String
)