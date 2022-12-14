package com.example.videoactivity2

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.example.videoactivity2.databinding.ActivityMainBinding
import java.io.File

//https://juahnpop.tistory.com/250
class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
// 권한 추가
    private val CAMERA_PERMISSION = arrayOf(Manifest.permission.CAMERA)
    private val CAMERA_PERMISSION_FLAG = 100
    private val STORAGE_PERMISSION = arrayOf(
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE)
    private val STORAGE_PERMISSION_FLAG = 200
// 카메라 실행
    private val REQUEST_VIDEO_CAPTURE_CODE = 1
    private var videoUri : Uri? = null // video 저장될 Uri
// play버튼 동작
    private var isVideoPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val imageView = findViewById<ImageView>(R.id.imageView)
        Glide.with(this).load(R.drawable.squat1).into(imageView)

// 권한 추가
         if(checkPermission(CAMERA_PERMISSION, CAMERA_PERMISSION_FLAG)){
            checkPermission(STORAGE_PERMISSION, STORAGE_PERMISSION_FLAG)
        }

// 카메라 실행
        binding.btnRecord.setOnClickListener {
            val recordVideoIntent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
            val videoFile = File(
                File("${filesDir}/video").apply {
                    if(!this.exists()){
                        this.mkdirs()
                    }
                },
                newVideoFileName()
            )
            videoUri = FileProvider.getUriForFile(
                this,
                "com.example.videoactivity2.fileprovider",
                videoFile
            )
            recordVideoIntent.resolveActivity(packageManager)?.also{
                recordVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT, videoUri)
                startActivityForResult(recordVideoIntent, REQUEST_VIDEO_CAPTURE_CODE)
            }
        }
// play버튼
        binding.btnPlay.setOnClickListener {
            when(isVideoPlaying){
                true -> {
                    isVideoPlaying = false
                    binding.videoView.stopPlayback()
                    binding.btnPlay.text = "Play"
                    binding.videoView.setVideoURI(videoUri)

                }
                false -> {
                    isVideoPlaying = true
                    binding.btnPlay.text = "Stop"
                    binding.videoView.start()
                }
            }
        }

        binding.videoView.setOnCompletionListener {
            binding.btnPlay.text = "Play"
            isVideoPlaying = false
        }



    }

    private fun newVideoFileName() : String {
        val sdf = SimpleDateFormat("yyyyMMdd_HHmmss")
        val filename = sdf.format(System.currentTimeMillis())
        return "${filename}.mp4"
    }

    private fun checkPermission(permissions : Array<out String>, flag : Int):Boolean{
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            for (permission in permissions) {
                if (ContextCompat.checkSelfPermission(
                        this,
                        permission
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(this, permissions, flag)
                    return false
                }
            }
        }
        return true
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            CAMERA_PERMISSION_FLAG -> {
                for(grant in grantResults) {
                    if(grant != PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "카메라 권한을 승인해야지만 앱을 사용 할 수 있습니다.", Toast.LENGTH_LONG).show()
                        finish()
                    }else{
                        checkPermission(STORAGE_PERMISSION, STORAGE_PERMISSION_FLAG)
                    }
                }
            }
            STORAGE_PERMISSION_FLAG -> {
                for(grant in grantResults) {
                    if(grant != PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "저장소 권한을 승인해야지만 앱을 사용 할 수 있습니다.", Toast.LENGTH_LONG).show()
                        finish()
                    }
                }
            }
        }
    }
    
//    카메라 띄우는 코드
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == RESULT_OK){
            when(requestCode){
                REQUEST_VIDEO_CAPTURE_CODE -> {
                    binding.videoView.setVideoURI(videoUri)
                    binding.videoView.requestFocus()
                    binding.btnPlay.isEnabled = true
                }
            }
        }
    }
}