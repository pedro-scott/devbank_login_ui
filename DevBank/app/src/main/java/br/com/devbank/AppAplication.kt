package br.com.devbank

import android.app.Application
import br.com.devbank.di.AppComponent
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppAplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AppAplication)

            modules(AppComponent.getAllModules())
        }
    }

}