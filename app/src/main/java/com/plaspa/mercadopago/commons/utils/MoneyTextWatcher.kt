package com.plaspa.mercadopago.commons.utils

import android.widget.EditText
import android.text.Editable
import android.text.TextWatcher
import java.lang.ref.WeakReference
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.*


/**
 * Created by Pedro on 03/10/2018.
 */
class MoneyTextWatcher(editText: EditText) : TextWatcher {

    private val editTextWeakReference: WeakReference<EditText> = WeakReference(editText)

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(editable: Editable) {
        //TODO: Arreglar valores con decimales
        val editText = editTextWeakReference.get() ?: return
        val s = editable.toString().replace("$","").replace(",","")
        if (s.isEmpty()) return
        if (s.toInt().toString().length > 10) return
        editText.removeTextChangedListener(this)
        val format = NumberFormat.getCurrencyInstance(Locale.CANADA)
        val amount = format.format(s.toFloat()).replace(".00","")
        editText.setText(amount)
        editText.setSelection(amount.length)
        editText.addTextChangedListener(this)
    }
}
