package com.example.nbastat_origin.di

import com.example.nbastat_origin.data.PlayersRepository
import com.example.nbastat_origin.data.remote.api.NbaApiServiceImp
import com.example.nbastat_origin.data.remote.client.MyRetrofitClient
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.singleton
import retrofit2.Retrofit

class AppModule {
    val kodein = DI.Module("appModule") {
        bind<Retrofit>() with singleton { MyRetrofitClient.create() }
        bind<NbaApiServiceImp>() with singleton { NbaApiServiceImp(instance()) }
        bind<PlayersRepository>() with singleton { PlayersRepository(instance()) }
    }
}