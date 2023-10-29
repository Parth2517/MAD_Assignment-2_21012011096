package com.example.mad_assignment_2_21012011096

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mad_assignment_2_21012011096.R
import com.example.mad_assignment_2_21012011096.databinding.ActivityMainBinding
import java.util.Timer
import java.util.TimerTask

class MainActivity : AppCompatActivity(), SongRecyclerAdapter.OnItemClickListener {
    private var arr_songs = song_list
    private val timer = Timer()

    override fun onItemClick(position: Int) {
        // Implement the behavior you want when an item is clicked
        updateTextViewText(position)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val play_btn: ImageButton = findViewById(R.id.btn_play)
        val btn_next: ImageButton = findViewById(R.id.btn_next)
        val btn_prev: ImageButton = findViewById(R.id.btn_prev)
        val txt_songname: TextView = findViewById(R.id.txt_songname)
        val txt_songsinger: TextView = findViewById(R.id.txt_songsinger)
        val song_img: ImageView = findViewById(R.id.img_song)
        val btn_fav: ImageView = findViewById(R.id.btn_fav)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)
        val seek_songbar: SeekBar = findViewById(R.id.seek_songbar)

        txt_songname.isSelected = true
        var play_state = false
        var songIndex = 0
        var fav_state = 0

        val adapter = SongRecyclerAdapter(this, arr_songs, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        fun setContent(songIndex: Int) {
            txt_songname.text = song_list[songIndex].title
            txt_songsinger.text = song_list[songIndex].singer
            song_img.setImageResource(song_list[songIndex].img)
        }
        setContent(0)
        fun songPlay(songIndex: Int, action: String) {
            val playIntent = Intent(this, MusicService::class.java)
            playIntent.putExtra("SongIndex", songIndex)
            playIntent.putExtra("MusicService", action)
            startService(playIntent)
            setContent(songIndex)
        }
        play_btn.setOnClickListener {
            if (!play_state) {
                songPlay(selected, "Play")
                play_btn.setImageResource(R.drawable.baseline_pause_circle_12)
                play_state = true
                var songProgressInMs = 1000
                timer.scheduleAtFixedRate(object : TimerTask() {
                    override fun run() {
                        val songDurationInMs = 150000
                        songProgressInMs += 1000
                        val songProgressPercentage =
                            (songProgressInMs.toFloat() / songDurationInMs.toFloat()) * 100f
                            seek_songbar.progress = songProgressPercentage.toInt()
                    }
                }, 0, 1000)
            } else {
                songPlay(selected, "Pause")
                play_btn.setImageResource(R.drawable.baseline_play_circle_12)
                play_state = false
            }
        }

        btn_next.setOnClickListener {
            val list_len = song_list.size
            songIndex = (songIndex + 1) % list_len
            play_btn.setImageResource(R.drawable.baseline_pause_circle_12)
            play_state = true
            songPlay(songIndex, "Play")
        }

        btn_prev.setOnClickListener {
            val list_len = song_list.size
            songIndex = (songIndex - 1 + list_len) % list_len
            play_btn.setImageResource(R.drawable.baseline_pause_circle_12)
            play_state = true
            songPlay(songIndex, "Play")
        }

        btn_fav.setOnClickListener {
            if (fav_state == 0) {
                btn_fav.setImageResource(R.drawable.baseline_favorite_red_12)
                fav_state = 1
            } else {
                btn_fav.setImageResource(R.drawable.baseline_favorite_green_12)
                fav_state = 0
            }
        }


    }

    fun updateTextViewText(position: Int) {

        val txt_songname: TextView = findViewById(R.id.txt_songname)
        val txt_songsinger: TextView = findViewById(R.id.txt_songsinger)
        val song_img: ImageView = findViewById(R.id.img_song)
        val play_btn: ImageButton = findViewById(R.id.btn_play)
        val seek_songbar: SeekBar = findViewById(R.id.seek_songbar)
        selected = position


        txt_songname.text = song_list[position].title
        txt_songsinger.text = song_list[position].singer
        song_img.setImageResource(song_list[position].img)

        val playIntent = Intent(this, MusicService::class.java)
        playIntent.putExtra("SongIndex", position)
        playIntent.putExtra("MusicService", position)
        startService(playIntent)
        play_btn.setImageResource(R.drawable.baseline_pause_circle_12)
        var songProgressInMs = 1000
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val songDurationInMs = 150000
                songProgressInMs += 1000
                val songProgressPercentage =
                    (songProgressInMs.toFloat() / songDurationInMs.toFloat()) * 100f
                seek_songbar.progress = songProgressPercentage.toInt()
            }
        }, 0, 1000)
    }
}
