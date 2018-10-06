package com.plaspa.mercadopago.di.module

import com.plaspa.mercadopago.di.annotations.FragmentScope
import com.plaspa.mercadopago.ui.fragments.PayBankFragment
import com.plaspa.mercadopago.ui.fragments.PayCompleteFragment
import com.plaspa.mercadopago.ui.fragments.PayMainFragment
import com.plaspa.mercadopago.ui.fragments.PayMethodFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule{

    @ContributesAndroidInjector
    @FragmentScope
    abstract
    fun contributePayMainFragment(): PayMainFragment

    @ContributesAndroidInjector
    @FragmentScope
    abstract
    fun contributePayMethodFragment(): PayMethodFragment

    @ContributesAndroidInjector
    @FragmentScope
    abstract
    fun contributePayBankFragment(): PayBankFragment

    @ContributesAndroidInjector
    @FragmentScope
    abstract
    fun contributePayCompleteFragment(): PayCompleteFragment
}