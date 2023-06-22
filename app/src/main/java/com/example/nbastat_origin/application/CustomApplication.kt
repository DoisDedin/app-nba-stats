package com.example.nbastat_origin.application

import android.app.Application
import com.example.nbastat_origin.di.AppModule
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.androidCoreModule

class CustomApplication : Application(), DIAware {

    override val di: DI by DI.lazy {
        import(androidCoreModule(this@CustomApplication))
        import(AppModule().kodein)
    }
}