package com.example.nbastat_origin.common

sealed class UiState<out T>

object UiLoading : UiState<Nothing>()
object UiSmokeLoading : UiState<Nothing>()
class UiSuccess<T>(val data: T) : UiState<T>()
class UiError(val errorData : ErrorData ) : UiState<Nothing>()
