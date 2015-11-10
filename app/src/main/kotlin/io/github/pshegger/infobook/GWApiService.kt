package io.github.pshegger.infobook

import io.github.pshegger.infobook.model.CharacterData
import io.github.pshegger.infobook.model.FileData
import retrofit.Call
import retrofit.http.GET
import retrofit.http.Path
import retrofit.http.Query

/**
 * Created by pshegger on 2015. 11. 09..
 */
interface GWApiService {
    @GET("v2/characters") fun getCharactersList(): Call<MutableList<String>>
    @GET("v2/characters?page=0") fun getAllCharacters(): Call<MutableList<CharacterData>>

    @GET("v2/files") fun getAvailableItems(): Call<MutableList<String>>
    @GET("v2/files/{id}") fun getItem(@Path("id") id: String): Call<FileData>
    @GET("v2/files") fun getItemsById(@Query("ids") ids: String): Call<MutableList<FileData>>
}