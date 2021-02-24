package com.prueba.superheroes

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BackgroundWorker (context: Context){

    private var retrofit: Retrofit? = null
    private val baseUrl = context.getString(R.string.base_url)

    fun getRetrofitInstance(): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit
    }
}