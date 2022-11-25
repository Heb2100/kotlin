package com.example.gifactivity

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val imageView = findViewById<ImageView>(R.id.imageView)
        Glide.with(this).load(R.drawable.squat1).into(imageView)
    }

//    fun showgif(view: ImageView){
//        val imageView = findViewById<ImageView>(R.id.imageView)
//        Glide.with(this)
//    }
//val options = BitmapFactory.Options().apply {
//    inJustDecodeBounds = true
//}
//    BitmapFactory.decodeResource(resources, R.id.myimage, options)
}