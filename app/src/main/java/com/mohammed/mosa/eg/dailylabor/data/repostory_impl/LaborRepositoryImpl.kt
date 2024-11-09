package com.mohammed.mosa.eg.dailylabor.data.repostory_impl

import com.mohammed.mosa.eg.dailylabor.data.dao.LaborDao
import com.mohammed.mosa.eg.dailylabor.data.module.Labor
import com.mohammed.mosa.eg.dailylabor.data.module.LaborWithPayments
import com.mohammed.mosa.eg.dailylabor.data.module.MonthlyPayment
import com.mohammed.mosa.eg.dailylabor.domain.repostory.LaborRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LaborRepositoryImpl @Inject constructor(private val laborDao: LaborDao) : LaborRepository {

    override fun getAllLabor(): Flow<List<Labor>> {
        return laborDao.getAllLabor()
    }

    override fun getLaborById(id: Int): Flow<Labor?> {
        return laborDao.getLaborById(id)
    }

    override fun searchLaborByName(query: String): Flow<List<Labor>> {
        return laborDao.searchLaborByName(query)
    }

    override suspend fun upsertLabor(labor: Labor) {
        laborDao.upsertLabor(labor)
    }

    override suspend fun deleteLabor(labor: Labor) {
        laborDao.deleteLabor(labor)
    }

    override suspend fun deleteAllLabor() {
        laborDao.deleteAllLabor()
    }

    override fun getTotalPaymentPerLaborPerMonth(): Flow<List<MonthlyPayment>> {
        return laborDao.getTotalPaymentPerLaborPerMonth()
    }

    override fun getLaborWithPayments(laborId: Int): Flow<LaborWithPayments> {
        return laborDao.getLaborWithPayments(laborId)
    }

    override fun getLaborWithPayments(): Flow<List<LaborWithPayments>> {
        return laborDao.getLaborWithPayments()
    }
}