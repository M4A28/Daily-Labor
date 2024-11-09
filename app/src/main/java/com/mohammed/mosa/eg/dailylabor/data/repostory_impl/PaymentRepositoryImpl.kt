package com.mohammed.mosa.eg.dailylabor.data.repostory_impl

import com.mohammed.mosa.eg.dailylabor.data.dao.PaymentDao
import com.mohammed.mosa.eg.dailylabor.data.module.Payment
import com.mohammed.mosa.eg.dailylabor.domain.repostory.PaymentRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PaymentRepositoryImpl @Inject constructor(private val paymentDao: PaymentDao) : PaymentRepository {
    override suspend fun upsertPayment(payment: Payment) {
        paymentDao.upsertPayment(payment)
    }

    override suspend fun deletePayment(payment: Payment) {
        paymentDao.deletePayment(payment)
    }

    override fun getPaymentsByLaborId(laborId: Int): Flow<List<Payment>> {
        return paymentDao.getPaymentsByLaborId(laborId)
    }

    override fun getPaymentById(paymentId: Int): Flow<Payment?> {
        return paymentDao.getPaymentById(paymentId)
    }

    override fun getAllPayments(): Flow<List<Payment>> {
        return paymentDao.getAllPayments()
    }

    override fun getTotalPayment(): Flow<Double?> {
        return paymentDao.getTotalPayment()
    }

    override fun getTotalPaymentForCurrentMonth(currentMonth: String): Flow<Double?> {
        return paymentDao.getTotalPaymentForCurrentMonth(currentMonth)
    }
}