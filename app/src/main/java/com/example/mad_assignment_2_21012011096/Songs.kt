package com.example.mad_assignment_2_21012011096

data class Songs(val title: String, val singer: String, val img: Int,val song: Int)
val song_list: List<Songs> = listOf(
    Songs("Jai Shri Ram", "Ajay Atul",R.drawable.song_1,R.raw.song_1),
    Songs("Khalasi", "Aditya Gadhvi", R.drawable.song_2, R.raw.song_2),
    Songs("Heeriye", "Arijit Singh", R.drawable.song_3, R.raw.song_3),
    Songs("Shri Krishna Govind Hare Murari", "Jubin Nautiyal", R.drawable.song_4, R.raw.song_4),
    Songs("Jai Shri Ram", "Ajay Atul",R.drawable.song_1,R.raw.song_1),
    Songs("Khalasi", "Aditya Gadhvi", R.drawable.song_2, R.raw.song_2),
    Songs("Heeriye", "Arijit Singh", R.drawable.song_3, R.raw.song_3),
    Songs("Shri Krishna Govind Hare Murari", "Jubin Nautiyal", R.drawable.song_4, R.raw.song_4),

)
    var selected:Int=0
