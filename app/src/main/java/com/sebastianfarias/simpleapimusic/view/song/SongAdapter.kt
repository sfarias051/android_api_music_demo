package com.sebastianfarias.simpleapimusic.view.song

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sebastianfarias.simpleapimusic.model.Result

class SongAdapter (private val clickListener: (Result) -> Unit) : RecyclerView.Adapter<SongViewHolder>() {
    private var songList : List<Result> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return SongViewHolder(inflater, parent)
    }

    override fun getItemCount(): Int {
        return songList.size
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        holder.bind(songList[position], clickListener)
    }

    fun setAlbumListItems(songList: List<Result>?){
        this.songList = songList!!
        notifyDataSetChanged()
    }

}
