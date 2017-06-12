package edu.bbu.webshop

import android.app.Application
import edu.bbu.webshop.di.AppInjector
import timber.log.Timber

class WebShopApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())

        // Initialize injection
        AppInjector.init(this)
    }
}