package com.plaspa.mercadopago.di

/**
 * Created by Pedro on 02/10/2018.
 */

import com.plaspa.mercadopago.MercadoPagoApplication
import com.plaspa.mercadopago.di.module.ActivityModule
import com.plaspa.mercadopago.di.module.ApplicationModule
import com.plaspa.mercadopago.di.module.FragmentModule
import com.plaspa.mercadopago.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApplicationModule::class), (ViewModelModule::class), (ActivityModule::class), (FragmentModule::class), (AndroidInjectionModule::class)])
interface ApplicationComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MercadoPagoApplication): Builder

        fun build(): ApplicationComponent
    }

    fun inject(application: MercadoPagoApplication)
}