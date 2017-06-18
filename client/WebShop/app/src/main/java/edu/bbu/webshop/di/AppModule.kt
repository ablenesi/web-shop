package edu.bbu.webshop.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import edu.bbu.webshop.api.WebShopService
import edu.bbu.webshop.util.SharedPreferenceManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class AppModule(private val app:Application) {

    @Singleton @Provides
    fun provideContext(): Context = app.applicationContext


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
    }


    @Singleton @Provides
    fun provideWebShopService(okHttpClient : OkHttpClient,sharePref: SharedPreferenceManager): WebShopService {
        return Retrofit.Builder()
                .baseUrl("http://${sharePref.getIP()}:${sharePref.getPort()}/webshopwebapi/api/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(WebShopService::class.java)
    }

}