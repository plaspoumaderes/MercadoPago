package com.plaspa.mercadopago.ui.fragments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.plaspa.mercadopago.R
import com.plaspa.mercadopago.commons.base.BaseActivity
import com.plaspa.mercadopago.commons.base.BaseFragment
import com.plaspa.mercadopago.commons.utils.AlertUtil
import com.plaspa.mercadopago.databinding.FragmentPayCompleteBinding
import com.plaspa.mercadopago.databinding.FragmentPayMethodBinding
import com.plaspa.mercadopago.model.CardIssuers
import com.plaspa.mercadopago.model.Installments
import com.plaspa.mercadopago.model.PayerCosts
import com.plaspa.mercadopago.model.PaymentMethod
import com.plaspa.mercadopago.ui.PayActivity
import com.plaspa.mercadopago.ui.adapter.PayBankViewAdapter
import com.plaspa.mercadopago.ui.adapter.PayCompleteViewAdapter
import com.plaspa.mercadopago.ui.adapter.PayMethodViewAdapter
import com.plaspa.mercadopago.ui.viewmodel.PayViewModel
import kotlinx.android.synthetic.main.fragment_pay_bank.view.*
import kotlinx.android.synthetic.main.fragment_pay_complete.view.*
import kotlin.properties.Delegates

/**
 * Created by Pedro on 04/10/2018.
 */
class PayCompleteFragment : BaseFragment() {

    private lateinit var viewModel: PayViewModel

    override fun layoutId(): Int = R.layout.fragment_pay_complete

    private lateinit var adapterPay: PayCompleteViewAdapter

    private var mBinding: FragmentPayCompleteBinding by Delegates.notNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PayViewModel::class.java)
        val payActivity = (activity as PayActivity)
        val payMetId = payActivity.paymentMethod?.id
        val cardIssId = payActivity.cardIssuers?.id
        payMetId?.let { payId ->
            cardIssId?.let { cardId ->
                viewModel.getInstallments(payActivity.amountPay, payId, cardId)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        mBinding.viewModel = viewModel
        val rootView = mBinding.root
        loadAdapter(rootView)
        return rootView
    }

    private fun loadAdapter(rootView: View) {
        adapterPay = PayCompleteViewAdapter(ArrayList()) { item: PayerCosts -> onItemClick(item) }
        rootView.fr_pay_complete_recycler.adapter = adapterPay
        rootView.fr_pay_complete_recycler.layoutManager = LinearLayoutManager(activity)
        rootView.fr_pay_complete_recycler.isVerticalScrollBarEnabled = true
        rootView.fr_pay_complete_recycler.isNestedScrollingEnabled = true
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.installments.reObserve(this, Observer { loadInstallments(it) })
        viewModel.errorConnection.reObserve(this, Observer { showErrorHandler(it) })
        viewModel.errorService.reObserve(this, Observer { showToast(getString(R.string.errorService), Toast.LENGTH_SHORT) })
    }

    private fun onItemClick(payerCosts: PayerCosts) {
        (activity as PayActivity)?.let { act ->
            val body: String = getString(R.string.acept_body)
                    .replace("{pago}", act.amountPay.toString())
                    .replace("{method}", "${act.paymentMethod?.name}")
                    .replace("{issuers}", "${act.cardIssuers?.name}")
                    .replace("{cuotas}", payerCosts.recommended_message)

            AlertUtil.showQuestion(act, R.drawable.ic_payment, R.string.app_name, body, R.string.acept, R.string.cancel,
                    DialogInterface.OnClickListener { _, _ ->
                        act.restartActivity()
                    }
            )
        }
    }

    private fun loadInstallments(list: List<Installments>?) {
        list?.let {
            if (it.isNotEmpty()) adapterPay.refreshList(it[0].payer_costs)
        }
    }

    companion object {
        fun newInstance(): PayCompleteFragment {
            return PayCompleteFragment()
        }
    }

}