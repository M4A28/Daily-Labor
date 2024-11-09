package com.mohammed.mosa.eg.dailylabor.domain.repostory


import com.mohammed.mosa.eg.dailylabor.data.module.Labor
import com.mohammed.mosa.eg.dailylabor.data.module.LaborWithPayments
import com.mohammed.mosa.eg.dailylabor.data.module.MonthlyPayment
import kotlinx.coroutines.flow.Flow

interface LaborRepository {

    fun getAllLabor(): Flow<List<Labor>>

    fun getLaborById(id: Int): Flow<Labor?>

    fun searchLaborByName(query: String): Flow<List<Labor>>

    suspend fun upsertLabor(labor: Labor)

    suspend fun deleteLabor(labor: Labor)

    suspend fun deleteAllLabor()

    fun getTotalPaymentPerLaborPerMonth(): Flow<List<MonthlyPayment>>

    fun getLaborWithPayments(laborId: Int): Flow<LaborWithPayments>

    fun getLaborWithPayments(): Flow<List<LaborWithPayments>>

}