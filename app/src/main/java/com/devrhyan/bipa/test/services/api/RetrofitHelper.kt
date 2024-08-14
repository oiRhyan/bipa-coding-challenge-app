package com.devrhyan.bipa.test.services.api

import retrofit2.converter.gson.GsonConverterFactory

class RetrofitHelper {
    //Retrofit client configuration for API calls
    companion object {
        val retrofit = retrofit2.Retrofit.Builder()
            .baseUrl("https://mempool.space/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}