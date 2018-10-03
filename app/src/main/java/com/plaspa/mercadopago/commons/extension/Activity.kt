package com.plaspa.mercadopago.commons.extension

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.replaceFragment(withAnimation: Boolean, container: Int, fragment: Fragment, args: Bundle?,
                                      addBackStack: Boolean) {
    supportFragmentManager.inTransaction {
        when {
//            withAnimation -> fragmentTransaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left,
//                android.R.anim.slide_in_left, android.R.anim.slide_out_right)
            args != null -> fragment.arguments = args
            addBackStack -> addToBackStack(fragment.tag+Math.random())
        }

        replace(container, fragment)
    }
}