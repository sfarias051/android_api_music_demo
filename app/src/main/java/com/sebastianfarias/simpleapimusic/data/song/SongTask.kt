package com.sebastianfarias.simpleapimusic.data.song

import com.sebastianfarias.simpleapimusic.utils.RetrofitClient
import com.sebastianfarias.simpleapimusic.service.SongService

class SongTask {

    var songService = RetrofitClient.getRetrofitClient(SongService::class.java)

    suspend fun getSongs(term: String, entity: String) = songService.getSongs(term, entity)
}