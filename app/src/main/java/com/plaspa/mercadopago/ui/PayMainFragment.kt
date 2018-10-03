package com.plaspa.mercadopago.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.widget.Toast
import com.plaspa.mercadopago.R
import com.plaspa.mercadopago.commons.base.BaseFragment
import com.plaspa.mercadopago.model.CardIssuers
import com.plaspa.mercadopago.model.Installments
import com.plaspa.mercadopago.model.PaymentMethod
import com.plaspa.mercadopago.ui.viewmodel.PayViewModel

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

        loadData()
    }

    private fun loadData() {
        viewModel.getPaymentMethods()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.paymentMethods.reObserve(this, Observer { loadPaymentMethod(it) })
        viewModel.cardIssuers.reObserve(this, Observer { loadCardIssuers(it) })
        viewModel.installments.reObserve(this, Observer { loadInstallments(it) })

        viewModel.showHideProgress.reObserve(this, Observer { showHideProgressBar(it) })
        viewModel.errorConnection.reObserve(this, Observer { showErrorHandler(it) })
        viewModel.errorService.reObserve(this, Observer { showToast(getString(R.string.errorService), Toast.LENGTH_SHORT) })

    }

    private fun loadInstallments(list: List<Installments>?) {
        list?.let {

        }
    }

    private fun loadCardIssuers(list: List<CardIssuers>?) {
        list?.let{

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

    private fun showErrorHandler(isError: Boolean?) {
        isError?.let {
            showSnackWithAction(R.string.no_internet_connection, R.string.refresh, {} )
        }
    }

    companion object {
        fun newInstance() : PayMainFragment {
            return PayMainFragment()
        }
    }

}