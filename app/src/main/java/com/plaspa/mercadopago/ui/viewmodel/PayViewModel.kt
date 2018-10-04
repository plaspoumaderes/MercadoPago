package com.plaspa.mercadopago.ui.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.util.Log
import com.plaspa.mercadopago.commons.utils.NetworkHandler
import com.plaspa.mercadopago.model.CardIssuers
import com.plaspa.mercadopago.model.Installments
import com.plaspa.mercadopago.model.PaymentMethod
import com.plaspa.mercadopago.ui.repositories.PayRepository
import javax.inject.Inject

/**
 * Created by Pedro on 02/10/2018.
 */
class PayViewModel @Inject constructor(private val payRepository: PayRepository, private val networkHandler: NetworkHandler) : ViewModel() {

    var paymentMethods: MutableLiveData<List<PaymentMethod>> = MutableLiveData()
    var cardIssuers: MutableLiveData<List<CardIssuers>> = MutableLiveData()
    var installments: MutableLiveData<List<Installments>> = MutableLiveData()

    var errorConnection: MutableLiveData<Boolean> = MutableLiveData()
    var errorService: MutableLiveData<Boolean> = MutableLiveData()

    var showProgress: MutableLiveData<Boolean> = MutableLiveData()
    var showProgressObs = ObservableField<Boolean>()

    init {
        showProgressObs.set(false)
    }

    fun getPaymentMethods() {
        when (networkHandler.isConnected) {
            true -> {
                showProgress.value = true
                payRepository.getPaymentMethods().subscribe({ paymentList ->
                    showProgress.value = false
                    paymentMethods.value = paymentList
                }, this::handleError)
            }
            else -> errorConnection.value = true
        }
    }

    fun getCardIssuers(payMethodId : String) {
        when (networkHandler.isConnected) {
            true -> {
                showProgress.value = true
                payRepository.getCardIssuers(payMethodId).subscribe({ cardIssuersList ->
                    showProgress.value = false
                    cardIssuers.value = cardIssuersList
                }, this::handleError)
            }
            else -> errorConnection.value = true
        }
    }

    private fun handleError(error: Throwable) {
        showProgress.value = false
        Log.e(PayViewModel::class.java.name, error.message + error.cause)
        errorService.value = true
    }
}