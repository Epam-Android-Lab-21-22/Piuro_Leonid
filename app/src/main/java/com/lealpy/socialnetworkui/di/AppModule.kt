package com.lealpy.socialnetworkui.di

import android.content.Context
import android.content.SharedPreferences
import android.os.Environment
import androidx.room.Room
import com.lealpy.socialnetworkui.data.database.AppDatabase
import com.lealpy.socialnetworkui.data.database.MessageDao
import com.lealpy.socialnetworkui.data.repository.*
import com.lealpy.socialnetworkui.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    /** Room */

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext : Context) : AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            ROOM_DATABASE_FILE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideHelpDao(appDatabase: AppDatabase) : MessageDao {
        return appDatabase.messageDao()
    }

    /** Shared preferences */

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext appContext: Context): SharedPreferences {
        return appContext.getSharedPreferences(SHARED_PREFERENCES_FILE_NAME, Context.MODE_PRIVATE)
    }

    /** Internal storage */

    @Provides
    @Singleton
    @Named("internalFile")
    fun provideInternalFile(@ApplicationContext appContext: Context): File {
        return File(appContext.filesDir, INTERNAL_STORAGE_FILE_NAME)
    }

    /** External storage */

    @Provides
    @Singleton
    @Named("externalFile")
    fun provideExternalFile(@ApplicationContext appContext: Context): File? {
        return if(Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            File(appContext.getExternalFilesDir(null), EXTERNAL_STORAGE_FILE_NAME)
        }
        else null
    }

    /** Use cases */

    @Provides
    @Singleton
    fun provideGetMessageFromDbUseCase(
        messageRepository: MessageRepositoryDatabase
    ) : GetMessageFromDbUseCase {
        return GetMessageFromDbUseCase(messageRepository)
    }

    @Provides
    @Singleton
    fun provideInsertMessageToDbUseCase(
        messageRepository: MessageRepositoryDatabase
    ) : InsertMessageToDbUseCase {
        return InsertMessageToDbUseCase(messageRepository)
    }

    @Provides
    @Singleton
    fun provideGetMessageFromPrefsUseCase(
        messageRepository: MessageRepositorySharedPreferences
    ) : GetMessageFromPrefsUseCase {
        return GetMessageFromPrefsUseCase(messageRepository)
    }

    @Provides
    @Singleton
    fun provideInsertMessageToPrefsUseCase(
        messageRepository: MessageRepositorySharedPreferences
    ) : InsertMessageToPrefsUseCase {
        return InsertMessageToPrefsUseCase(messageRepository)
    }

    @Provides
    @Singleton
    fun provideGetMessageFromInternalStorageUseCase(
        messageRepository: MessageRepositoryInternalStorage
    ) : GetMessageFromInternalStorageUseCase {
        return GetMessageFromInternalStorageUseCase(messageRepository)
    }

    @Provides
    @Singleton
    fun provideInsertMessageToInternalStorageUseCase(
        messageRepository: MessageRepositoryInternalStorage
    ) : InsertMessageToInternalStorageUseCase {
        return InsertMessageToInternalStorageUseCase(messageRepository)
    }

    @Provides
    @Singleton
    fun provideGetMessageFromExternalStorageUseCase(
        messageRepository: MessageRepositoryExternalStorage
    ) : GetMessageFromExternalStorageUseCase {
        return GetMessageFromExternalStorageUseCase(messageRepository)
    }

    @Provides
    @Singleton
    fun provideInsertMessageToExternalStorageUseCase(
        messageRepository: MessageRepositoryExternalStorage
    ) : InsertMessageToExternalStorageUseCase {
        return InsertMessageToExternalStorageUseCase(messageRepository)
    }

    companion object {
        private const val ROOM_DATABASE_FILE_NAME = "database.db"
        private const val SHARED_PREFERENCES_FILE_NAME = "prefs"
        private const val INTERNAL_STORAGE_FILE_NAME = "message.txt"
        private const val EXTERNAL_STORAGE_FILE_NAME = "message.txt"
    }

}
