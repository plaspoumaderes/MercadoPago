package com.plaspa.mercadopago.ui

import android.os.Bundle
import com.plaspa.mercadopago.R
import com.plaspa.mercadopago.commons.base.BaseActivity
import com.plaspa.mercadopago.commons.extension.replaceFragment

class PayActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay)
    }

    override fun initializeFragment() {
        replaceFragment(false, R.id.fragment_container, PayMainFragment.newInstance(),
                Bundle(), true)
    }
}