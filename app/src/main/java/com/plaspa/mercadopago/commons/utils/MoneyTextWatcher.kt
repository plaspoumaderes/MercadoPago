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
        val editText = editTextWeakReference.get() ?: return
        var s = editable.toString().replace("$","").replace(",","")
        if (s.isEmpty()) return
        val addPoint : Boolean = (s[s.lastIndex] == '.')
        val addFirstDecimal : Boolean = (s[s.lastIndex - 1] == '.')
        if (addPoint) s =  s.replace(".","")
        var value = s.toFloat()
        if (value.toInt().toString().length > 10) return
        editText.removeTextChangedListener(this)
        val format = NumberFormat.getCurrencyInstance(Locale.CANADA)
        //var amount = format.format(String.format("%.2f", value)).replace(".00","")
        var amount = format.format(value).replace(".00","")
        if (addPoint) amount += "."
        if (addFirstDecimal) amount = amount.substring(0,amount.lastIndex - 1)
        editText.setText(amount)
        editText.setSelection(amount.length)
        editText.addTextChangedListener(this)
    }
}
