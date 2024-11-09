package com.mohammed.mosa.eg.dailylabor.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Upsert
import com.mohammed.mosa.eg.dailylabor.data.module.Labor
import com.mohammed.mosa.eg.dailylabor.data.module.LaborWithPayments
import com.mohammed.mosa.eg.dailylabor.data.module.MonthlyPayment
import kotlinx.coroutines.flow.Flow

@Dao
interface LaborDao {

    @Query("SELECT * FROM labor")
    fun getAllLabor(): Flow<List<Labor>>

    @Query("SELECT * FROM labor WHERE id = :id")
    fun getLaborById(id: Int): Flow<Labor?>

    @Query("SELECT * FROM labor WHERE name LIKE '%' || :query || '%'")
    fun searchLaborByName(query: String): Flow<List<Labor>>

    @Upsert
    suspend fun upsertLabor(labor: Labor)

    @Delete
    suspend fun deleteLabor(labor: Labor)

    @Query("DELETE FROM labor")
    suspend fun deleteAllLabor()

    @Transaction
    @Query("""
        SELECT id AS laborId, laborName, strftime('%Y-%m', payDate / 1000, 'unixepoch') AS month, SUM(amount) AS totalPayment
        FROM payments
        GROUP BY laborId, month
        ORDER BY laborId, month DESC
    """)
    fun getTotalPaymentPerLaborPerMonth(): Flow<List<MonthlyPayment>>


    @Transaction
    @Query("SELECT * FROM labor WHERE id = :laborId")
    fun getLaborWithPayments(laborId: Int): Flow<LaborWithPayments>

    @Transaction
    @Query("SELECT * FROM labor")
    fun getLaborWithPayments(): Flow<List<LaborWithPayments>>




}