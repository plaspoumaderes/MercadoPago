package com.plaspa.mercadopago.ui

import com.plaspa.mercadopago.R
import com.plaspa.mercadopago.commons.base.BaseFragment

/**
 * Created by Pedro on 02/10/2018.
 */
class PayMainFragment : BaseFragment() {

    override fun layoutId(): Int = R.layout.fragment_pay_main

    companion object {
        fun newInstance() : PayMainFragment {
            return PayMainFragment()
        }
    }

}