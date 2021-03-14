package com.antoniovieira.dogsapp.utils

import com.antoniovieira.dogsapp.R
import retrofit2.HttpException
import java.net.UnknownHostException

object ExceptionHelper {

    enum class ExceptionType {
        NETWORK_CONNECTION,
        SERVER_SIDE,
        CLIENT_SIDE,
        GENERIC
    }

    private fun getExceptionType(throwable: Throwable): ExceptionType {
        if (throwable is UnknownHostException) {
            return ExceptionType.NETWORK_CONNECTION
        }

        if (throwable is HttpException) {
            return when (throwable.code()) {
                in 400..500 -> ExceptionType.CLIENT_SIDE
                in 500..600 -> ExceptionType.SERVER_SIDE
                else -> ExceptionType.GENERIC
            }
        }

        return ExceptionType.GENERIC
    }

    fun getExceptionMessage(throwable: Throwable): Pair<Int, Int> {
        return when (getExceptionType(throwable)) {
            ExceptionType.NETWORK_CONNECTION -> Pair(
                R.string.network_error_title,
                R.string.network_error_description
            )

            ExceptionType.SERVER_SIDE, ExceptionType.CLIENT_SIDE, ExceptionType.GENERIC -> Pair(
                R.string.generic_error_title,
                R.string.generic_error_description
            )
        }
    }

}