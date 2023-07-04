package com.example.nbastat_origin.application

import android.app.Application
import com.example.nbastat_origin.di.apppModule
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule

class CustomApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@CustomApplication))
        import(apppModule)
    }
}