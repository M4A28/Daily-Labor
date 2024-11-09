package com.mohammed.mosa.eg.dailylabor.di

import android.app.Application
import com.mohammed.mosa.eg.dailylabor.app.DailyLaborApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMyBusinessApplication(application: Application): DailyLaborApplication {
        return application as DailyLaborApplication
    }
}