package edu.bbu.webshop.di

import dagger.Provides
import edu.bbu.webshop.api.WebShopService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

class AppModule {

    @Singleton @Provides
    fun provideWebShopService() = Retrofit.Builder()
            .baseUrl("http://192.168.1.132/WebShopWebAPI/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(WebShopService::class.java)

}