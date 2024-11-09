package com.mohammed.mosa.eg.dailylabor.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.mohammed.mosa.eg.dailylabor.data.module.Payment
import kotlinx.coroutines.flow.Flow


@Dao
interface PaymentDao {

    @Upsert
    suspend fun upsertPayment(payment: Payment)

    @Delete
    suspend fun deletePayment(payment: Payment)

    @Query("SELECT * FROM payments WHERE laborId = :laborId")
    fun getPaymentsByLaborId(laborId: Int): Flow<List<Payment>>

    @Query("SELECT * FROM payments WHERE id = :paymentId")
    fun getPaymentById(paymentId: Int): Flow<Payment?>


    @Query("SELECT * FROM payments")
    fun getAllPayments(): Flow<List<Payment>>

    @Query("SELECT SUM(amount) FROM payments")
    fun getTotalPayment(): Flow<Double?>

    @Query("SELECT SUM(amount) FROM payments WHERE strftime('%Y-%m', payDate / 1000, 'unixepoch') = :currentMonth ")
    fun getTotalPaymentForCurrentMonth(currentMonth: String): Flow<Double?>

}