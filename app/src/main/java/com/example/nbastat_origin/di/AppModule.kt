package com.example.nbastat_origin.di

import com.example.nbastat_origin.data.PlayersRepository
import com.example.nbastat_origin.data.remote.api.NbaApiService
import com.example.nbastat_origin.data.remote.client.MyRetrofitClient
import com.example.nbastat_origin.model.PlayersConverter
import com.example.nbastat_origin.ui.home.PlayersListViewModel

import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

import retrofit2.Retrofit

val apppModule = Kodein.Module("appModule", true) {
    bind<Retrofit>() with singleton { MyRetrofitClient.create() }
    bind<NbaApiService>() with singleton {
        instance<Retrofit>().create(NbaApiService::class.java)
    }

    bind<PlayersConverter>() with provider { PlayersConverter() }

    bind<PlayersRepository>() with singleton { PlayersRepository(instance()) }

    bind<PlayersListViewModel>() with singleton { PlayersListViewModel(instance(), instance()) }

}