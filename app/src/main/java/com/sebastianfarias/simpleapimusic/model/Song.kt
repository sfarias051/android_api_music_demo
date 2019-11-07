package com.sebastianfarias.simpleapimusic.model

data class Song(
    val resultCount: Int,
    val results: List<Result>
)