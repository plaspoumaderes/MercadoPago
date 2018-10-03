package com.plaspa.mercadopago.model

import org.json.JSONArray

/**
 * Created by Pedro on 02/10/2018.
 */
class PayerCosts(var installments: Int,
               var installment_rate: Int,
               var discount_rate: Int,
               var labels: JSONArray,
               var installment_rate_collector: JSONArray,
               var min_allowed_amount: Int,
               var max_allowed_amount: Int,
               var recommended_message: String,
               var installment_amount: Int,
               var total_amount: Int)