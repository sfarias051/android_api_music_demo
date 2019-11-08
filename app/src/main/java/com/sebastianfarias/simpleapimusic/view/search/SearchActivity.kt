package com.sebastianfarias.simpleapimusic.view.search

import android.content.Intent
import android.os.Bundle
import com.sebastianfarias.simpleapimusic.R
import com.sebastianfarias.simpleapimusic.utils.BaseActivity
import com.sebastianfarias.simpleapimusic.view.song.SongActivity
import kotlinx.android.synthetic.main.search_song.*

class SearchActivity : BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_song)

        sendAnalyticsData(this.localClassName)

        iv_search_song_list.setOnClickListener { searchClicked() }
    }


    private fun searchClicked(){
        val songActivity = Intent(this, SongActivity::class.java)
        songActivity.putExtra(Intent.EXTRA_TEXT, et_search_song_list.text.toString())
        startActivity(songActivity)
    }
}