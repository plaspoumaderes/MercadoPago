package com.plaspa.mercadopago.ui.repositories

import com.plaspa.film.service.Constants
import com.plaspa.mercadopago.model.PaymentMethod
import com.plaspa.mercadopago.ui.services.PayServices
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import java.net.HttpURLConnection
import java.util.HashMap
import javax.inject.Inject

/**
 * Created by Pedro on 02/10/2018.
 */
class PayRepository @Inject constructor(retrofit: Retrofit) {

    private val payApi by lazy { retrofit.create(PayServices::class.java) }

    fun getPaymentMethods(): Observable<List<PaymentMethod>> {
        return payApi.getPaymentMethods(Constants.PUBLIC_KEY)
                .concatMap { httpValidation(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun <T> httpValidation(response: Response<T>): Observable<T> {
        return Observable.fromCallable {
            if (response.isSuccessful) {
                response.body()?.let {
                    it
                }
            } else throw getExceptionHandler(response.code(), response.errorBody())
        }
    }

    private fun getExceptionHandler(code: Int, errorBody: ResponseBody?): Throwable {
        when(code) {
            HttpURLConnection.HTTP_NOT_FOUND -> throw Exception("Service request not found")
            HttpURLConnection.HTTP_UNAUTHORIZED -> throw Exception("User not authorized to access services")
            HttpURLConnection.HTTP_CONFLICT -> throw Exception("User token auth still available")
            else -> throw Exception("Unknown error")
        }
    }
}