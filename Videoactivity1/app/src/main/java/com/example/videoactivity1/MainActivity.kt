package com.example.videoactivity1

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import android.widget.VideoView
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

//https://developer.android.com/training/camera/videobasics
//https://guides.codepath.com/android/Video-Playback-and-Recording
class MainActivity : AppCompatActivity() {
    val REQUEST_VIDEO_CAPTURE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_btn_camera_open.setOnClickListener {
            dispatchTakeVideoIntent()
        }

//        main_btn_camera_open.setOnClickListener {
////            버전체크 이후 동영상을 캡처하는 인챈트를 호출
//            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
//                Intent(MediaStore.ACTION_VIDEO_CAPTURE).also { takeVideoIntent ->
//                    takeVideoIntent.resolveActivity(packageManager)?.also {
//                        startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE)
//                    }
//                }
//        }
    }

    private fun dispatchTakeVideoIntent() {
        Intent(MediaStore.ACTION_VIDEO_CAPTURE).also { takeVideoIntent ->
            takeVideoIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE)
            }
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        val videoUri: Uri? = intent?.data
        videoView.setVideoURI(videoUri)
    }
}