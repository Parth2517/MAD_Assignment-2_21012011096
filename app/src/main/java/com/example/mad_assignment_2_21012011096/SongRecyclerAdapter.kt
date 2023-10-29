package com.example.mad_assignment_2_21012011096

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SongRecyclerAdapter(private val mainActivity: MainActivity, private val arr_songs: List<Songs>, private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<SongRecyclerAdapter.ViewHolder>() {
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SongRecyclerAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.song_card, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txt_name: TextView = itemView.findViewById(R.id.txt_songName)
        val txt_singer: TextView = itemView.findViewById(R.id.txt_singer)
        val img_song: ImageView = itemView.findViewById(R.id.img_song)
    }
    override fun onBindViewHolder(holder: SongRecyclerAdapter.ViewHolder, position: Int) {
        holder.txt_name.text = arr_songs[position].title
        holder.txt_singer.text = arr_songs[position].singer
        holder.img_song.setImageResource(arr_songs[position].img)

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return arr_songs.size
    }
}