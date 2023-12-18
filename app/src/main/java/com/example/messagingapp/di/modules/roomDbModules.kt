package com.example.messagingapp.di.modules

import com.example.messagingapp.db.AppDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

internal val roomDbModules = module {
    single { AppDatabase.getInstance(androidContext()) }
}