package com.lealpy.socialnetworkui.di

import com.lealpy.socialnetworkui.data.repository.FavoriteSongsRepositoryImpl
import com.lealpy.socialnetworkui.data.repository.SongRepositoryImpl
import com.lealpy.socialnetworkui.domain.repository.FavoriteSongsRepository
import com.lealpy.socialnetworkui.domain.repository.SongRepository
import com.lealpy.socialnetworkui.domain.use_cases.GetFavoriteSongsUseCase
import com.lealpy.socialnetworkui.domain.use_cases.GetSongUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideSongRepository() : SongRepository = SongRepositoryImpl()

    @Provides
    @Singleton
    fun provideFavoriteSongsRepository() : FavoriteSongsRepository = FavoriteSongsRepositoryImpl()

    @Provides
    @Singleton
    fun provideGetSongUseCase(
        songRepository: SongRepository
    ) : GetSongUseCase = GetSongUseCase(songRepository)

    @Provides
    @Singleton
    fun provideFavoriteSongsUseCase(
        favoriteSongsRepository: FavoriteSongsRepository
    ) : GetFavoriteSongsUseCase = GetFavoriteSongsUseCase(favoriteSongsRepository)

}
