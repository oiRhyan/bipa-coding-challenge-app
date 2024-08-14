package com.devrhyan.bipa.test.services.api

import com.devrhyan.bipa.test.services.models.Nodes
import retrofit2.Response
import retrofit2.http.GET

interface iNodes {

    @GET("api/v1/lightning/nodes/rankings/connectivity")
    suspend fun getNodes() : Response<List<Nodes>>

}