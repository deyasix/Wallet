package com.example.wallet

import android.app.Application
import com.example.wallet.di.DaggerApplicationComponent

class MainApplication : Application() {
    val appComponent by lazy {
        DaggerApplicationComponent.builder().context(applicationContext).build()
    }
}