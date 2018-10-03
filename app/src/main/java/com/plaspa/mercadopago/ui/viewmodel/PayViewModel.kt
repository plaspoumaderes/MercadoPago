package com.plaspa.mercadopago.ui.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
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
class PayViewModel @Inject constructor(private val payRepository: PayRepository,
                                       private val networkHandler: NetworkHandler): ViewModel() {

    var paymentMethods: MutableLiveData<List<PaymentMethod>> = MutableLiveData()
    var cardIssuers: MutableLiveData<List<CardIssuers>> = MutableLiveData()
    var installments: MutableLiveData<List<Installments>> = MutableLiveData()

    var showHideProgress: MutableLiveData<Boolean> = MutableLiveData()
    var errorConnection: MutableLiveData<Boolean> = MutableLiveData()
    var errorService: MutableLiveData<Boolean> = MutableLiveData()

    private var lastPageTaken: Int = 0
    private var lastPageTakenSearch: Int = 0
    var qtyItemsToShow: Int = 0

    fun getPaymentMethods(){
        when (networkHandler.isConnected) {
            true -> payRepository.getPaymentMethods().subscribe(this::handleResponse, this::handleError)
            else -> errorConnection.value = true
        }
    }

//    fun searchMovies(query: String) {
//        when (networkHandler.isConnected){
//            true -> {
//                if(query.isNotEmpty()) {
//                    lastPageTakenSearch += 1
//                    showHideProgress.value = true
//                    moviesRepository.searchMovies(lastPageTakenSearch, query).subscribe(this::handleResponseSearch, this::handleError)}
//            }
//            else -> errorConnection.value = true
//        }
//    }

    private fun handleResponse(paymentList: List<PaymentMethod>) {
        showHideProgress.value = false
        paymentMethods.value = paymentList
    }

//    private fun handleResponseSearch(movieList: List<MovieSearch>) {
//        moviesSearchShow.value = movieList
//        qtyItemsToShow = moviesRepository.qtyItemsRequest
//        showHideProgress.value = false
//    }

    private fun handleError(error: Throwable){
        showHideProgress.value = false
        Log.e(PayViewModel::class.java.name, error.message + error.cause)
        errorService.value = true
    }
}