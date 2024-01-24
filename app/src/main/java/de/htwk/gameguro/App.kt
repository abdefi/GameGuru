package de.htwk.gameguro

import android.app.Application
import de.htwk.gameguro.di.appModule
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }
    }
}