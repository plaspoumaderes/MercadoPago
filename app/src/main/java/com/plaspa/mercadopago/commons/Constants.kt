package com.plaspa.film.service

/**
 * Created by Pedro on 13/06/2018.
 */
class Constants {
    companion object {

        const val PUBLIC_KEY :String = "444a9ef5-8a6b-429f-abdf-587639155d88"
        const val END_POINT : String = "https://api.mercadopago.com/"

        const val GET_PAYMENT_METHODS : String = "v1/payment_methods"
        const val GET_PAYMENT_INSTALLMENTS : String = "v1/payment_methods/installments"
        const val GET_CARD_ISSUERS : String = "v1/payment_methods/card_issuers?public_key=$PUBLIC_KEY&payment_method_id={method}"
    }
}