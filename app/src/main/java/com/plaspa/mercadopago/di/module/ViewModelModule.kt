package com.plaspa.mercadopago.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.plaspa.mercadopago.ui.viewmodel.PayViewModel
import com.plaspa.mercadopago.commons.factory.ViewModelFactory
import com.plaspa.mercadopago.di.annotations.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PayViewModel::class)
    abstract fun bindPayViewModel(payViewModel: PayViewModel): ViewModel
}