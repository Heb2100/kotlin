package com.example.videoactivity

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

//  https://developerson.tistory.com/88
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val videoView = findViewById<VideoView>(R.id.videoView)

        videoView.setMediaController(MediaController(this))
        //videoView에 MediaController연결

        videoView.setVideoURI(Uri.parse("android.resource://" + packageName + "/"+R.raw.music))
        //resource 폴더 안 raw 폴더 안에 있는 music이라는 동영상 연결

        videoView.bringToFront()

        val imageView = findViewById<ImageView>(R.id.imageView)
        Glide.with(this).load(R.drawable.squat1).into(imageView)

    }
}