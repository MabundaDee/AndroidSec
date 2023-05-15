package com.example.androidsec

sealed class BaseRespond<out T> {

    data class Success<out T>(val data: T? = null) :BaseRespond<T>()
    data class Loading(val nothing: Nothing?=null) :BaseRespond<Nothing>()
    data class Error(val msg: String?) : BaseRespond<Nothing>()

}