package edu.bbu.webshop.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import edu.bbu.webshop.api.WebShopService
import edu.bbu.webshop.util.SharedPreferenceManager
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class AppModule(private val app:Application) {

    @Singleton @Provides
    fun provideContext(): Context = app.applicationContext

    @Singleton @Provides
    fun provideWebShopService(sharePref: SharedPreferenceManager): WebShopService {
        val ip: String = "192.168.1.122"
        val port: String = "80"


//        val ip: String = sharePref.getString(SharedPreferenceManager.SERVER_IP_KEY)
//        val port: String = sharePref.getString(SharedPreferenceManager.SERVER_PORT_KEY)

        return Retrofit.Builder()
                .baseUrl("http://$ip:$port/webshopwebapi/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(WebShopService::class.java)
    }

}