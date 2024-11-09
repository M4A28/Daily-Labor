package com.mohammed.mosa.eg.dailylabor.di

import com.mohammed.mosa.eg.dailylabor.data.dao.LaborDao
import com.mohammed.mosa.eg.dailylabor.data.db.AppDatabase
import com.mohammed.mosa.eg.dailylabor.data.repostory_impl.LaborRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LaborModule {

    @Provides
    @Singleton
    fun provideLaborDao(db: AppDatabase): LaborDao {
        return db.laborDao
    }

    @Provides
    @Singleton
    fun provideLaborRepository(laborDao: LaborDao): LaborRepositoryImpl {
        return LaborRepositoryImpl(laborDao)
    }


}