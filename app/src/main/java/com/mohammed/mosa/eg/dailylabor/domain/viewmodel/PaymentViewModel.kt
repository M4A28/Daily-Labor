package com.mohammed.mosa.eg.dailylabor.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohammed.mosa.eg.dailylabor.data.module.Payment
import com.mohammed.mosa.eg.dailylabor.data.repostory_impl.PaymentRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(private val paymentRepository: PaymentRepositoryImpl) : ViewModel() {

    val payments = paymentRepository.getAllPayments()


    fun getPaymentById(id: Int) = paymentRepository.getPaymentById(id)


    fun getPaymentByLaborId(laborId: Int) = paymentRepository.getPaymentsByLaborId(laborId)

    fun upsertPayment(payment: Payment) {
        viewModelScope.launch {
            paymentRepository.upsertPayment(payment)
        }
    }

    fun deletePayment(payment: Payment) {
        viewModelScope.launch {
            paymentRepository.deletePayment(payment)
        }
    }


     val totalPayment = paymentRepository.getTotalPayment()

    fun getTotalPaymentForCurrentMonth(currentMonth: String) = paymentRepository.getTotalPaymentForCurrentMonth(currentMonth)







}