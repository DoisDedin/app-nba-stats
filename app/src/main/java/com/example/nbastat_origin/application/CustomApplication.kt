package com.example.nbastat_origin.application

import android.app.Application
import com.example.nbastat_origin.data.PlayersRepository
import com.example.nbastat_origin.data.remote.api.NbaApiService
import com.example.nbastat_origin.data.remote.client.MyRetrofitClient
import com.example.nbastat_origin.model.PlayersConverter
import com.example.nbastat_origin.ui.list_players.home.list.PlayersListViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton
import retrofit2.Retrofit

class CustomApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        bind<Retrofit>() with singleton { MyRetrofitClient.create() }
        bind<NbaApiService>() with singleton {
            instance<Retrofit>().create(NbaApiService::class.java)
        }
        bind<PlayersConverter>() with singleton { PlayersConverter() }
        bind<PlayersRepository>() with singleton { PlayersRepository(instance()) }
        bind() from provider { PlayersListViewModel(instance(), instance()) }
    }
}