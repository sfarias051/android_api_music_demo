package com.sebastianfarias.simpleapimusic.view.song

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sebastianfarias.simpleapimusic.R
import com.sebastianfarias.simpleapimusic.data.song.SongViewModel
import com.sebastianfarias.simpleapimusic.data.song.SongViewModelFactory
import com.sebastianfarias.simpleapimusic.model.Result
import com.sebastianfarias.simpleapimusic.utils.BaseActivity
import com.sebastianfarias.simpleapimusic.utils.Constants
import com.sebastianfarias.simpleapimusic.view.songdetail.SongDetailActivity
import kotlinx.android.synthetic.main.song_list.*

class SongActivity : BaseActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: SongAdapter
    private lateinit var songViewModel: SongViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.song_list)

        sendAnalyticsData(this.localClassName)

        recyclerView = findViewById(R.id.recyclerview_song_list)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = SongAdapter{ result: Result -> albumItemClicked(result) }
        recyclerView.adapter = recyclerAdapter

        swipe_refresh_list.setColorSchemeResources(
            android.R.color.holo_blue_bright,
            android.R.color.holo_green_light,
            android.R.color.holo_orange_light,
            android.R.color.holo_red_light)
        swipe_refresh_list.setOnRefreshListener {
            if(hasInternetConnection()) getSongList()
        }

        if (this.intent.hasExtra(Intent.EXTRA_TEXT))
            searchSongViewModel(this.intent.getStringExtra(Intent.EXTRA_TEXT))
    }

    private fun getSongList() {
        swipe_refresh_list.isRefreshing = true
        recyclerAdapter.notifyDataSetChanged()
        songViewModel.getSongList.observe(this,Observer {
                recyclerAdapter.setAlbumListItems(it.body()?.results)
                swipe_refresh_list.isRefreshing = false
            }
        )
    }

    private fun searchSongViewModel(search: String){
        songViewModel = ViewModelProviders.of(this,
            SongViewModelFactory(
                search,
                Constants.WS_PARAMETER_VALUE_ENTITY
            )
        ).get(SongViewModel::class.java)
        if(hasInternetConnection()) getSongList()
    }

    private fun albumItemClicked(result : Result){
        val songDetailActivityIntent = Intent(this, SongDetailActivity::class.java)
        songDetailActivityIntent.putExtra(Constants.KEY_SONG_OBJECT, result)
        startActivity(songDetailActivityIntent)
    }

}