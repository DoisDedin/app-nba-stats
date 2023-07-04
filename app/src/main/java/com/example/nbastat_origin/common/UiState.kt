package com.example.nbastat_origin.common

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

sealed class UiState<out T>

object UiLoading : UiState<Nothing>()
object UiSmokeLoading : UiState<Nothing>()
class UiSuccess<T>(val data: T) : UiState<T>()
class UiError(val errorData: ErrorData) : UiState<Nothing>()



typealias LiveDataState<T> = LiveData<UiState<T>>

fun <RESPONSE> LiveDataState<RESPONSE>.observeOnLoading(
    owner: LifecycleOwner,
    observer: () -> Unit
): LiveDataState<RESPONSE> {
    observe(
        owner,
        Observer {
            if (it is UiLoading) observer.invoke()
        }
    )
    return this
}

fun <RESPONSE> LiveDataState<RESPONSE>.observeOnSmokeLoading(
    owner: LifecycleOwner,
    observer: () -> Unit
): LiveDataState<RESPONSE> {
    observe(
        owner,
        Observer {
            if (it is UiSmokeLoading) observer.invoke()
        }
    )
    return this
}

fun <RESPONSE> LiveDataState<RESPONSE>.observeOnSuccess(
    owner: LifecycleOwner,
    observer: (RESPONSE) -> Unit
): LiveDataState<RESPONSE> {
    observe(
        owner,
        Observer {
            if (it is UiSuccess) observer.invoke(it.data)
        }
    )
    return this
}

fun <RESPONSE> LiveDataState<RESPONSE>.observeOnSuccess(
    owner: LifecycleOwner,
    observer: () -> Unit
): LiveDataState<RESPONSE> {
    observe(
        owner,
        Observer {
            if (it is UiSuccess) observer.invoke()
        }
    )
    return this
}

fun <RESPONSE> LiveDataState<RESPONSE>.observeOnError(
    owner: LifecycleOwner,
    observer: (ErrorData) -> Unit
): LiveDataState<RESPONSE> {
    observe(
        owner,
        Observer {
            if (it is UiError) observer.invoke(it.errorData)
        }
    )
    return this
}
