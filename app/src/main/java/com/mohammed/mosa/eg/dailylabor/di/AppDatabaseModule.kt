package com.mohammed.mosa.eg.dailylabor.di

import android.app.Application
import androidx.room.Room
import com.mohammed.mosa.eg.dailylabor.data.converter.DateTypeConverter
import com.mohammed.mosa.eg.dailylabor.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppDatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {

        return Room.databaseBuilder(
            context = app,
            klass = AppDatabase::class.java,
            name = "app_database")
            //.addTypeConverter(LocalDateTimeConverter())
            .addTypeConverter(DateTypeConverter())
            .fallbackToDestructiveMigration()
            .build()
    }

}