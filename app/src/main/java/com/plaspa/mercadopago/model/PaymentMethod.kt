package com.plaspa.mercadopago.model

import org.json.JSONArray

/**
 * Created by Pedro on 02/10/2018.
 */
class PaymentMethod(var id: String, var name: String,
                    var payment_type_id: String,
                    var status: String,
                    var secure_thumbnail: String,
                    var thumbnail: String,
                    var eferred_capture: String,
                    var settings: JSONArray,
                    var additional_info_needed: JSONArray,
                    var min_allowed_amount: Int,
                    var max_allowed_amount: Int,
                    var accreditation_time: Int,
                    var financial_institutions: JSONArray,
                    var processing_modes: JSONArray)