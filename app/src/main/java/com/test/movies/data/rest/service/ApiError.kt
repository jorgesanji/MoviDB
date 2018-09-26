package com.test.movies.data.rest.service

import com.google.gson.annotations.SerializedName

class ApiError {

    //{"status_code":25,"status_message":"Your request count (41) is over the allowed limit of 40."}

    @SerializedName("statusCode")
    protected var statusCode: Int? = 0

    @SerializedName("status_message")
    protected var message: String? = null

    fun setMessage(message: String): ApiError {
        this.message = message
        return this
    }

    fun setStatusCode(code: Int): ApiError {
        this.statusCode = code
        return this
    }

    companion object {

        val API_ERROR_UNDEFINED = 0x01
        val API_ERROR_MESSAGE = "UNDEFINED ERROR"

        val defaultError: ApiError
            get() = ApiError().setStatusCode(API_ERROR_UNDEFINED).setMessage(API_ERROR_MESSAGE)
    }
}
