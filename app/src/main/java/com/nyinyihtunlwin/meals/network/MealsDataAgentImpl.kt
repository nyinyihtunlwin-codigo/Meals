package com.nyinyihtunlwin.meals.network

import com.google.gson.Gson
import com.nyinyihtunlwin.meals.events.RestApiEvents
import com.nyinyihtunlwin.meals.network.responses.LatestMealResponse
import com.nyinyihtunlwin.meals.ui.utils.AppConstants
import okhttp3.OkHttpClient
import org.greenrobot.eventbus.EventBus
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MealsDataAgentImpl private constructor() : MealsDataAgent {

    private var mApi: MealsApi

    companion object {
        private var INSTANCE: MealsDataAgentImpl? = null
        fun getInstance(): MealsDataAgentImpl {
            if (INSTANCE == null) {
                INSTANCE = MealsDataAgentImpl()
            }
            val i = INSTANCE
            return i!!
        }
    }

    init {
        val okHttpClient = OkHttpClient
            .Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(okHttpClient)
            .build()
        mApi = retrofit.create(MealsApi::class.java)
    }

    override fun getLatestMeals() {
        mApi.getLatestMeals().enqueue(object : Callback<LatestMealResponse> {

            override fun onResponse(call: Call<LatestMealResponse>, response: Response<LatestMealResponse>) {
                val latestMealResponse = response.body()
                if(latestMealResponse != null && latestMealResponse.meals.isNotEmpty()){
                    EventBus.getDefault()
                        .post(
                            RestApiEvents.LatestMealsDataLoadedEvent(
                                latestMealResponse.meals
                            )
                        )
                }else{
                    EventBus.getDefault()
                        .post(
                            RestApiEvents.ErrorInvokingAPIEvent(
                                "No data found"
                            )
                        )
                }
            }

            override fun onFailure(call: Call<LatestMealResponse>, t: Throwable) {
                EventBus.getDefault()
                    .post(
                        RestApiEvents.ErrorInvokingAPIEvent(
                            t.localizedMessage
                        )
                    )
            }
        })
    }
}