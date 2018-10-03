package com.plaspa.mercadopago.di.module

import com.plaspa.mercadopago.di.annotations.ActivityScope
import com.plaspa.mercadopago.ui.PayActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule{
    @ContributesAndroidInjector(modules = [(FragmentModule::class)])
    @ActivityScope
    abstract fun contributePayActivity(): PayActivity
}