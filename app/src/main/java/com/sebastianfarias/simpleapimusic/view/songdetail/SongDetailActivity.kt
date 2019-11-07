package com.sebastianfarias.simpleapimusic.view.songdetail

import android.media.MediaPlayer
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.sebastianfarias.simpleapimusic.R
import com.sebastianfarias.simpleapimusic.model.Result
import com.sebastianfarias.simpleapimusic.utils.BaseActivity
import com.sebastianfarias.simpleapimusic.utils.Constants
import kotlinx.android.synthetic.main.song_detail.*

class SongDetailActivity : BaseActivity(){
    private val mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.song_detail)

        sendAnalyticsData(this.localClassName)

        val factoryCrossFade = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()

        val intent = intent
        if (this.intent.hasExtra(Constants.KEY_SONG_OBJECT)){
            val song = intent.getParcelableExtra<Result>(Constants.KEY_SONG_OBJECT)
            tv_song_list_name.text = song?.trackName
            tv_song_list_artist.text = song?.artistName
            tv_song_list_album.text = song?.collectionName

            mediaPlayer.setDataSource(song?.previewUrl)

            button_play_media.setOnClickListener { playMedia() }
            button_stop_media.setOnClickListener { stopMedia() }

            Glide.with(this)
                .load(song?.artworkUrl100)
                .transition(DrawableTransitionOptions.withCrossFade(factoryCrossFade))
                .fitCenter()
                .placeholder(R.drawable.baseline_photo_black_48)
                .error(R.drawable.baseline_broken_image_black_48)
                .fallback(R.drawable.baseline_broken_image_black_48)
                .into(iv_song_detail_image)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }


    private fun stopMedia(){
        if (mediaPlayer.isPlaying)
            mediaPlayer.stop()
    }

    private fun playMedia(){
        if (!mediaPlayer.isPlaying){
            mediaPlayer?.prepare()
            mediaPlayer.start()
        }
    }

}