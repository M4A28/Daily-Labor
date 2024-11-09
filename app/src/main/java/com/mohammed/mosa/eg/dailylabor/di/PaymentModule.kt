package com.mohammed.mosa.eg.dailylabor.di

import com.mohammed.mosa.eg.dailylabor.data.dao.LaborDao
import com.mohammed.mosa.eg.dailylabor.data.dao.PaymentDao
import com.mohammed.mosa.eg.dailylabor.data.db.AppDatabase
import com.mohammed.mosa.eg.dailylabor.data.repostory_impl.LaborRepositoryImpl
import com.mohammed.mosa.eg.dailylabor.data.repostory_impl.PaymentRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object PaymentModule {

    @Provides
    @Singleton
    fun providePaymentDao(db: AppDatabase): PaymentDao {
        return db.paymentDao
    }

    @Provides
    @Singleton
    fun providePaymentRepository(paymentDao: PaymentDao): PaymentRepositoryImpl {
        return PaymentRepositoryImpl(paymentDao)
    }

}