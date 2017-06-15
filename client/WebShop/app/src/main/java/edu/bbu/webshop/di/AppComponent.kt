package edu.bbu.webshop.di

import dagger.Component
import edu.bbu.webshop.feature.product.ProductPagerAdapter
import edu.bbu.webshop.feature.settings.SettingsFragment
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(adapter: ProductPagerAdapter)
    fun inject(settingsFragment: SettingsFragment)
}