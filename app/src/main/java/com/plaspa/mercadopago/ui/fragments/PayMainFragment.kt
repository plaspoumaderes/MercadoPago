package com.plaspa.mercadopago.ui.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.transition.AutoTransition
import android.support.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import com.plaspa.mercadopago.R
import com.plaspa.mercadopago.commons.base.BaseActivity
import com.plaspa.mercadopago.commons.base.BaseFragment
import com.plaspa.mercadopago.commons.extension.replaceFragment
import com.plaspa.mercadopago.commons.utils.AlertUtil
import com.plaspa.mercadopago.commons.utils.MoneyTextWatcher
import com.plaspa.mercadopago.ui.PayActivity
import com.plaspa.mercadopago.ui.viewmodel.PayViewModel
import kotlinx.android.synthetic.main.fragment_pay_main.*
import kotlinx.android.synthetic.main.fragment_pay_main.view.*

/**
 * Created by Pedro on 02/10/2018.
 */
class PayMainFragment : BaseFragment() {

    private lateinit var viewModel: PayViewModel

    override fun layoutId(): Int = R.layout.fragment_pay_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PayViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val rootView = super.onCreateView(inflater, container, savedInstanceState)
        savedInstanceState?.let {
            rootView.fr_pay_main_amount.setText(it.get((activity as PayActivity).AMOUNT).toString())
        }
        addListener(rootView)
        return rootView
    }

    private fun addListener(rootView: View) {
        rootView.fr_pay_main_amount.addTextChangedListener(MoneyTextWatcher(rootView.fr_pay_main_amount))
        rootView.fr_pay_main_button.setOnClickListener {
            val amount = rootView.fr_pay_main_amount.text.toString().replace("$", "").replace(",", "").toFloat()
            if (amount > 0.0) {
                (activity as PayActivity).amountPay = amount
                (activity as BaseActivity).replaceFragment(false, R.id.fragment_container, PayMethodFragment.newInstance(), Bundle(), true)
            } else {
                activity?.let { act -> AlertUtil.showPopup(act, R.drawable.ic_payment, R.string.app_name, R.string.invalid_amount) }
            }
        }
    }

    companion object {
        fun newInstance(): PayMainFragment {
            return PayMainFragment()
        }
    }

}