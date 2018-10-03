package com.plaspa.mercadopago.model

/**
 * Created by Pedro on 02/10/2018.
 */
class Installments(
        var payment_method_id: String,
        var payment_type_id: String,
        var issuer: CardIssuers,
        var processing_mode: String,
        var merchant_account_id: String,
        var payer_costs: List<PayerCosts>
)