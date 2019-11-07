package com.sebastianfarias.simpleapimusic.data.song

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SongViewModelFactory(private val term: String, private val entity: String) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SongViewModel(term, entity) as T
    }
}