package com.mohammed.mosa.eg.dailylabor.domain.repostory

import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.mohammed.mosa.eg.dailylabor.data.module.Payment
import kotlinx.coroutines.flow.Flow

interface PaymentRepository {

    suspend fun upsertPayment(payment: Payment)

    suspend fun deletePayment(payment: Payment)

    fun getPaymentsByLaborId(laborId: Int): Flow<List<Payment>>

    fun getPaymentById(paymentId: Int): Flow<Payment?>


    fun getAllPayments(): Flow<List<Payment>>

    fun getTotalPayment(): Flow<Double?>

    fun getTotalPaymentForCurrentMonth(currentMonth: String): Flow<Double?>
}