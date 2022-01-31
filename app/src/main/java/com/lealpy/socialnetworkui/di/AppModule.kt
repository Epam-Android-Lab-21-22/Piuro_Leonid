package com.lealpy.socialnetworkui.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.lealpy.socialnetworkui.data.database.AppDatabase
import com.lealpy.socialnetworkui.data.database.MessageDao
import com.lealpy.socialnetworkui.data.file_providers.ExternalStorageFileProvider
import com.lealpy.socialnetworkui.data.file_providers.InternalStorageFileProvider
import com.lealpy.socialnetworkui.data.repository.*
import com.lealpy.socialnetworkui.domain.use_cases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
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

    /** Internal storage file provider */

    @Provides
    @Singleton
    fun provideInternalStorageFileProvider(@ApplicationContext appContext: Context): InternalStorageFileProvider {
        return InternalStorageFileProvider(appContext)
    }

    /** External storage file provider */

    @Provides
    @Singleton
    fun provideExternalStorageFileProvider(@ApplicationContext appContext: Context): ExternalStorageFileProvider {
        return ExternalStorageFileProvider(appContext)
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
    }

}
