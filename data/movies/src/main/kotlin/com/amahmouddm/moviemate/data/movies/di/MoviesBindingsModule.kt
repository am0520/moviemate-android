package com.amahmouddm.moviemate.data.movies.di

import com.amahmouddm.moviemate.data.movies.datasource.MoviesRemoteDataSource
import com.amahmouddm.moviemate.data.movies.DefaultMoviesRepository
import com.amahmouddm.moviemate.data.movies.MoviesRepository
import com.amahmouddm.moviemate.data.movies.datasource.network.KtorMoviesRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class MoviesBindingsModule {

    @Binds
    @Singleton
    abstract fun bindMoviesRemoteDataSource(
        implementation: KtorMoviesRemoteDataSource,
    ): MoviesRemoteDataSource

    @Binds
    @Singleton
    abstract fun bindMoviesRepository(
        implementation: DefaultMoviesRepository,
    ): MoviesRepository
}
