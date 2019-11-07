package com.sebastianfarias.simpleapimusic.data.song

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers

class SongViewModel(private val term: String,
                    private val entity: String) : ViewModel(){

    private val repository: SongTask =
        SongTask()

    val getSongList = liveData(Dispatchers.IO){
        val responseAlbum = repository.getSongs(
            term,entity)
        emit(responseAlbum)
    }

}