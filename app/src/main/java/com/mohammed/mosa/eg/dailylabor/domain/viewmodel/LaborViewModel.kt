package com.mohammed.mosa.eg.dailylabor.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohammed.mosa.eg.dailylabor.data.module.Labor
import com.mohammed.mosa.eg.dailylabor.data.repostory_impl.LaborRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaborViewModel @Inject constructor(private val laborRepository: LaborRepositoryImpl) : ViewModel() {

    val labors = laborRepository.getAllLabor()

    fun getLaborById(id: Int) = laborRepository.getLaborById(id)

    fun searchLaborByName(query: String) = laborRepository.searchLaborByName(query)


    fun upsertLabor(labor: Labor) {
      viewModelScope.launch {
          laborRepository.upsertLabor(labor)
      }

    }

    fun deleteLabor(labor: Labor) {
        viewModelScope.launch {
            laborRepository.deleteLabor(labor)
        }
    }

    fun deleteAllLabor() {
        viewModelScope.launch {
            laborRepository.deleteAllLabor()
        }
    }

    fun getTotalPaymentPerLaborPerMonth() = laborRepository.getTotalPaymentPerLaborPerMonth()

    fun getLaborWithPayments(laborId: Int) = laborRepository.getLaborWithPayments(laborId)

    fun getLaborWithPayments() = laborRepository.getLaborWithPayments()





}