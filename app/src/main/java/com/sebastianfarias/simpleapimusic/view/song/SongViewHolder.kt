package com.sebastianfarias.simpleapimusic.view.song

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.sebastianfarias.simpleapimusic.R
import com.sebastianfarias.simpleapimusic.model.Result
import kotlinx.android.synthetic.main.song_list_item.view.*

class SongViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.song_list_item, parent, false)) {

    private lateinit var result: Result

    fun bind(result: Result, clickListener: (Result) -> Unit) {
        this.result = result
        itemView.tv_song_list_artist.text = result.artistName
        itemView.tv_song_list_name.text = result.trackName
        Glide.with(itemView)
            .load(result.artworkUrl60)
            .centerCrop()
            .placeholder(R.drawable.baseline_photo_black_48)
            .error(R.drawable.baseline_broken_image_black_48)
            .fallback(R.drawable.baseline_broken_image_black_48)
            .transform(RoundedCorners(20))
            .into(itemView.iv_song_list_image)
        itemView.setOnClickListener {clickListener(result)}
    }
}