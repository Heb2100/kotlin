package com.example.viewactivity1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.viewactivity1.databinding.ActivityCamBinding

class CamActivity : AppCompatActivity() {

    lateinit var binding :ActivityCamBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cam)

        binding = ActivityCamBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val imageView = findViewById<ImageView>(R.id.imageView)
        Glide.with(this).load(R.drawable.squat1).into(imageView)
    }
}