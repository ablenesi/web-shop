package edu.bbu.webshop.di

import dagger.Component
import edu.bbu.webshop.feature.order.OrdersFragment
import edu.bbu.webshop.feature.product.ProductPageFragment
import edu.bbu.webshop.feature.product.ProductPagerAdapter
import edu.bbu.webshop.feature.settings.SettingsFragment
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(settingsFragment: SettingsFragment)
    fun inject(productPagerAdapter: ProductPagerAdapter)
    fun inject(productPageFragment: ProductPageFragment)
    fun inject(ordersFragment: OrdersFragment)
}