package com.sebastianfarias.simpleapimusic.service

import com.sebastianfarias.simpleapimusic.model.Song
import com.sebastianfarias.simpleapimusic.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SongService {

    @GET("search")
    suspend fun getSongs(@Query(Constants.WS_PARAMETER_KEY_TERM) term: String,
                         @Query(Constants.WS_PARAMETER_KEY_ENTITY) entity: String): Response<Song>
}