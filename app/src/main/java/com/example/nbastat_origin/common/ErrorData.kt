package com.example.nbastat_origin.common

data class ErrorData(
    val throwable: Throwable? = null,
    val code: Int? = null,
    val errorBody: String? = null,
    val type: String? = null
)
