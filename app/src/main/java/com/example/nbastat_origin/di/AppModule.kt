package com.example.nbastat_origin.di

import org.kodein.di.DI

class AppModule {
    val kodein = DI.Module("appModule") {
//       bindProvider<Random> { SecureRandom() }
//       bindSingleton<Database> { SQLiteDatabase() }
    }
}

