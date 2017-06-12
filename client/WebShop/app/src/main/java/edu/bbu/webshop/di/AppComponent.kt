package edu.bbu.webshop.di

import dagger.Component
import edu.bbu.webshop.feature.product.ProductPagerAdapter
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(adapter: ProductPagerAdapter)
}