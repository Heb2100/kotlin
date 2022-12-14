package com.example.viewactivity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.viewactivity.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btOpenintent.setOnClickListener {
//          다이얼
//            val intent = Intent(Intent.ACTION_DIAL, Uri.parse(
//              "tel:5313568431"
//            ))

//            지도
//            val intent = Intent(Intent.ACTION_VIEW,
//            Uri.parse(
//                "geo:20.42222, -100.83984?z=10"
//            ))

//          링크
            val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse(
                    "https://facebook.com"
                ))


            startActivity(intent)
        }
    }
}