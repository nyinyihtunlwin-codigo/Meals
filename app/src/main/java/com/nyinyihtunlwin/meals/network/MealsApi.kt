package com.nyinyihtunlwin.meals.network

import com.nyinyihtunlwin.meals.network.responses.LatestMealResponse
import retrofit2.Call
import retrofit2.http.GET

interface MealsApi {
    @GET("api/json/v1/1/latest.php")
    fun getLatestMeals(): Call<LatestMealResponse>
}