package com.baptistecarlier.kotlin.datagouvfr.app.di

import android.app.Application
import android.content.Context
import com.baptistecarlier.kotlin.datagouvfr.app.OdfApplication
import com.baptistecarlier.kotlin.datagouvfr.app.repository.DgfrRepository
import com.baptistecarlier.kotlin.datagouvfr.app.repository.Storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): OdfApplication {
        return app as OdfApplication
    }

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application

    @Singleton
    @Provides
    fun provideStorage(context: Context): Storage {
        return Storage(context)
    }

    @Singleton
    @Provides
    fun provideDgfrRepository(storage: Storage): DgfrRepository {
        return DgfrRepository(storage)
    }

}