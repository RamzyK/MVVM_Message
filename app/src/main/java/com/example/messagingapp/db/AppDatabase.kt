package com.example.messagingapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.messagingapp.db.daos.CompleteUserDao
import com.example.messagingapp.db.daos.MessagesDao
import com.example.messagingapp.db.entities.CompleteUser
import com.example.messagingapp.db.entities.Messages

private const val LOCAL_ROOM_DB_NAME = "messaging_app_room_db"
@Database(
    entities = [CompleteUser::class, Messages::class],
    version = 2,
    exportSchema = true)
abstract class AppDatabase: RoomDatabase() {
    abstract fun completeUserDao(): CompleteUserDao
    abstract fun messagesDao(): MessagesDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, "messaging_app_room_db").fallbackToDestructiveMigration().build()
                    instance = INSTANCE
                }
                return instance!!
            }
        }
    }
}