package com.plaspa.mercadopago.ui.services

import com.plaspa.mercadopago.commons.Constants
import com.plaspa.mercadopago.model.CardIssuers
import com.plaspa.mercadopago.model.Installments
import com.plaspa.mercadopago.model.PaymentMethod
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Pedro on 02/10/2018.
 */
interface PayServices {
    @GET(Constants.GET_PAYMENT_METHODS)
    fun getPaymentMethods(@Query(Constants.QUERY_PUBLIC_KEY) api_key: String)
            : Observable<Response<List<PaymentMethod>>>

    @GET(Constants.GET_PAYMENT_INSTALLMENTS)
    fun getPaymentInstallments(@Query(Constants.QUERY_PUBLIC_KEY) api_key: String, @Query(Constants.QUERY_AMOUNT) amount: Float, @Query(Constants.QUERY_PAYMENT_METHOD) payId: String, @Query("issuer.id") id: Int)
            : Observable<Response<List<Installments>>>

    @GET(Constants.GET_CARD_ISSUERS)
    fun getCardIssuers(@Query(Constants.QUERY_PUBLIC_KEY) api_key: String, @Query(Constants.QUERY_PAYMENT_METHOD) method: String)
            : Observable<Response<List<CardIssuers>>>

}