package edu.bbu.webshop

import android.app.Application
import edu.bbu.webshop.di.AppComponent
import edu.bbu.webshop.di.DaggerAppComponent
import timber.log.Timber

class WebShopApp : Application() {

    companion object{
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        appComponent = DaggerAppComponent.builder()
                .application(this)
                .build()
    }
}