package edu.bbu.webshop.di

import dagger.Provides
import edu.bbu.webshop.api.WebShopService
import edu.bbu.webshop.util.SharedPreferenceManager
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

class AppModule {

    @Singleton @Provides
    fun provideWebShopService(sharePref : SharedPreferenceManager): WebShopService? {
        val port : String = sharePref.getString(SharedPreferenceManager.SERVER_PORT_KEY)
        val ip : String = sharePref.getString(SharedPreferenceManager.SERVER_IP_KEY)
        return Retrofit.Builder()
                .baseUrl("http://$ip:$port/WebShopWebAPI/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(WebShopService::class.java)
    }
}