package com.example.messagingapp.di

import android.content.Context
import com.example.messagingapp.BuildConfig
import com.example.messagingapp.di.modules.FakerJsonConf
import com.example.messagingapp.di.modules.coreModules
import com.example.messagingapp.di.modules.remoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.error.KoinAppAlreadyStartedException
import org.koin.dsl.module

fun injectModuleDependencies(context: Context) {
    try {
        startKoin {
            androidContext(context)
            modules(modules)
        }
    } catch (alreadyStart: KoinAppAlreadyStartedException) {
        loadKoinModules(modules)
    }
}

fun parseConfigurationAndAddItToInjectionModules() {
    val fakeApiConf = FakerJsonConf(baseUrl = BuildConfig.BASE_URL)
    modules.add(module {
        single { fakeApiConf }
    })
}

private val modules = mutableListOf(coreModules, remoteModule)