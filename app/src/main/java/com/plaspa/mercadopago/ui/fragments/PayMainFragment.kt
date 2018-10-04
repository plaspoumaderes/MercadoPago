package com.plaspa.mercadopago.ui.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.plaspa.mercadopago.R
import com.plaspa.mercadopago.commons.base.BaseActivity
import com.plaspa.mercadopago.commons.base.BaseFragment
import com.plaspa.mercadopago.commons.extension.replaceFragment
import com.plaspa.mercadopago.commons.utils.AlertUtil
import com.plaspa.mercadopago.commons.utils.MoneyTextWatcher
import com.plaspa.mercadopago.model.CardIssuers
import com.plaspa.mercadopago.model.Installments
import com.plaspa.mercadopago.model.PaymentMethod
import com.plaspa.mercadopago.ui.PayActivity
import com.plaspa.mercadopago.ui.viewmodel.PayViewModel
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
                activity?.let { act -> AlertUtil.showPopup(act, R.mipmap.ic_launcher, R.string.app_name, R.string.invalid_amount) }
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.paymentMethods.reObserve(this, Observer { loadPaymentMethod(it) })
        viewModel.cardIssuers.reObserve(this, Observer { loadCardIssuers(it) })
        viewModel.installments.reObserve(this, Observer { loadInstallments(it) })

        viewModel.errorConnection.reObserve(this, Observer { showErrorHandler(it) })
        viewModel.errorService.reObserve(this, Observer { showToast(getString(R.string.errorService), Toast.LENGTH_SHORT) })

    }

    private fun loadInstallments(list: List<Installments>?) {
        list?.let {

        }
    }

    private fun loadCardIssuers(list: List<CardIssuers>?) {
        list?.let {

        }
    }

    private fun loadPaymentMethod(list: List<PaymentMethod>?) {
        list?.let {

        }
    }

    private fun showHideProgressBar(showPB: Boolean?) {
        showPB?.let {

        }
    }

    companion object {
        fun newInstance(): PayMainFragment {
            return PayMainFragment()
        }
    }

}