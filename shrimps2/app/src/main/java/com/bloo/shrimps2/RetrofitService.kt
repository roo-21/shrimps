package com.bloo.shrimps2

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

data class ApiResponse(
    val diseases: List<String>,
    val symptoms: List<List<String>>
)


data class DiseaseRequest(
    @SerializedName("symptoms")
    val symptoms: List<String>
)

interface ApiService {
    @POST("/api/")
    fun getDisease(@Body requestBody: DiseaseRequest): Call<ApiResponse>
}
