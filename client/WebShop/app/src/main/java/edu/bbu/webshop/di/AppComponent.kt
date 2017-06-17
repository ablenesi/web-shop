package edu.bbu.webshop.di

import dagger.BindsInstance
import dagger.Component
import edu.bbu.webshop.WebShopApp
import edu.bbu.webshop.feature.settings.SettingsFragment
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: WebShopApp): Builder
        fun build(): AppComponent
    }

    fun inject(settingsFragment: SettingsFragment)
}