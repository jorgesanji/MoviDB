package com.test.movies.data.local

import android.content.Context
import com.test.movies.R
import com.test.movies.data.rest.service.RestAdapter

class SessionManager {
    constructor(restAdapter: RestAdapter,  context:Context) {
        this.restAdapter = restAdapter
        this.context = context
    }

    var restAdapter: RestAdapter
    var context:Context

    fun initSession(){
        restAdapter.apikey = context.getString(R.string.api_key)
        restAdapter.language = "en-US"
    }
}