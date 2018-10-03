package com.plaspa.mercadopago.commons.utils

import android.content.Context
import com.plaspa.mercadopago.commons.extension.networkState
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Pedro on 02/10/2018.
 */
@Singleton
class NetworkHandler @Inject constructor(private val context: Context) {
    val isConnected get() = context.networkState?.isConnectedOrConnecting
}