package com.prueba.superheroes

import okhttp3.ResponseBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GetDataService {
    @GET("search/a")
    fun getHeroes() : Call<Hero>

    @GET("search/{name}")
    fun getSearchHero(@Path("name") name: String) : Call<Hero>
}