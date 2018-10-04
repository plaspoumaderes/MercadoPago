package com.plaspa.mercadopago.commons.utils

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface

/**
 * Created by Pedro on 04/10/2018.
 */

class AlertUtil {
    companion object {

        fun showPopup(mContext: Context, imageResource: Int, titleResource: Int, bodyResource: Int) {
            val builder = AlertDialog.Builder(mContext)
                    .setIcon(imageResource)
                    .setTitle(titleResource)
                    .setMessage(bodyResource)
            builder.create().show()
        }

        fun showQuestion(mContext: Context, imageResource: Int, titleResource: Int, bodyResource: Int, positiveButton: Int, negativeButton: Int, onPositiveListener: DialogInterface.OnClickListener, onNegativeListener: DialogInterface.OnClickListener? = null) {
            val builder = AlertDialog.Builder(mContext)
                    .setIcon(imageResource)
                    .setTitle(titleResource)
                    .setMessage(bodyResource)
                    .setPositiveButton(positiveButton, onPositiveListener)
                    .setNegativeButton(negativeButton, onNegativeListener)
            builder.create().show()
        }
    }
}