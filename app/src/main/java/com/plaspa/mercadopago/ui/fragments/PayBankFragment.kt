package com.plaspa.mercadopago.ui.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.plaspa.mercadopago.R
import com.plaspa.mercadopago.commons.base.BaseFragment
import com.plaspa.mercadopago.databinding.FragmentPayBankBinding
import com.plaspa.mercadopago.model.CardIssuers
import com.plaspa.mercadopago.ui.PayActivity
import com.plaspa.mercadopago.ui.adapter.PayBankViewAdapter
import com.plaspa.mercadopago.ui.viewmodel.PayViewModel
import kotlinx.android.synthetic.main.fragment_pay_bank.view.*
import kotlin.properties.Delegates

/**
 * Created by Pedro on 04/10/2018.
 */
class PayBankFragment: BaseFragment() {

    private lateinit var viewModel: PayViewModel

    override fun layoutId(): Int = R.layout.fragment_pay_method

    private lateinit var adapterPay: PayBankViewAdapter

    private var mBinding: FragmentPayBankBinding by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PayViewModel::class.java)
        (activity as PayActivity).paymentMethod?.let {
            viewModel.getCardIssuers(it.id)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_pay_bank, container, false)
        mBinding.viewModel = viewModel
        val rootView = mBinding.root
        loadAdapter(rootView)
        return rootView
    }

    private fun loadAdapter(rootView: View) {
        adapterPay = PayBankViewAdapter(ArrayList()) { item: CardIssuers -> onItemClick(item) }
        rootView.fr_pay_bank_recycler.adapter = adapterPay
        rootView.fr_pay_bank_recycler.layoutManager = LinearLayoutManager(activity)
        rootView.fr_pay_bank_recycler.isVerticalScrollBarEnabled = true
        rootView.fr_pay_bank_recycler.isNestedScrollingEnabled = true
    }

    private fun onItemClick(item: CardIssuers) {
        Log.i("Test" , "itemOnClick: $item")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.cardIssuers.reObserve(this, Observer { loadCardsIssuers(it) })
        viewModel.showProgress.reObserve(this, Observer { showHideProgressBar(it) })
        viewModel.errorConnection.reObserve(this, Observer { showErrorHandler(it) })
        viewModel.errorService.reObserve(this, Observer { showToast(getString(R.string.errorService), Toast.LENGTH_SHORT) })
    }

    private fun loadCardsIssuers(list: List<CardIssuers>?) {
        list?.let {
            adapterPay.refreshList(it)
        }
    }

    private fun showHideProgressBar(showPB: Boolean?) {
        showPB?.let {
            viewModel.showProgressObs.set(it)
        }
    }

    companion object {
        fun newInstance() : PayBankFragment {
            return PayBankFragment()
        }
    }

}