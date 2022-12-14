//https://korean-otter.tistory.com/entry/androidkotlin-%EC%B9%B4%EB%A9%94%EB%9D%BC-%EA%B6%8C%ED%95%9C-%EC%96%BB%EC%96%B4-%EC%82%AC%EC%9A%A9%ED%95%98%EA%B8%B0
package com.example.camactivity

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        camera_on_btn.setOnClickListener {
            val camerapermissioncheck = ContextCompat.checkSelfPermission(
                this@MainActivity,
                android.Manifest.permission.CAMERA
            )
            if(camerapermissioncheck != PackageManager.PERMISSION_GRANTED){ // 권한이 없는 경우
                ActivityCompat.requestPermissions( // 성공시 카메라 요청
                    this,
                    arrayOf(android.Manifest.permission.CAMERA),
                    1000 // 다른거 하고싶으면 이코드로 해
                )
            }
            else{ // 권한이 있음
                openCamera()

            }
        }
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        createImageUri(newFileName(), "image/jpg")?.let { uri ->
            val realUri = uri
            intent.putExtra(MediaStore.EXTRA_OUTPUT, realUri)
            startActivityForResult()


        }

    }

    private fun newFileName(): String {
        val sdf = SimpleDateFormat("yyyyMMdd_HHmmss")
        val filename = sdf.format(System.currentTimeMillis())
        return "$filename.jpg"

    }

    private fun createImageUri(filename : String, mimeType : String) : Uri? {
        var values = ContentValues()
        values.put(MediaStore.Images.Media.DISPLAY_NAME, filename)
        values.put(MediaStore.Images.Media.MIME_TYPE, mimeType)
        return this.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
    }

//        camera_on_btn.setOnClickListener {
//            val cameraPermissionCheck = ContextCompat.checkSelfPermission(
//                this@MainActivity,
//                android.Manifest.permission.CAMERA
//            )
//            if (cameraPermissionCheck != PackageManager.PERMISSION_GRANTED) { // 권한이 없는 경우
//                ActivityCompat.requestPermissions(
//                    this,
//                    arrayOf(android.Manifest.permission.CAMERA),
//                    1000
//                )
//            }
//            else { //권한이 있는 경우
//                val REQUEST_IMAGE_CAPTURE = 1
//                Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
//                    takePictureIntent.resolveActivity(packageManager)?.also {
//                        ActivityResultContracts.StartActivityForResult(
//                            takePictureIntent,
//                            REQUEST_IMAGE_CAPTURE
//                        )
//                    }
//                }
//
//            }
//        }


}

override fun onRequestPermissionsResult( // requestcode 를 붙잡아 무언가를 하고싶을때
    requestCode: Int,
    permissions: Array<out String>,
    grantResults: IntArray
) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    if (requestCode == 1000){
        if (grantResults[0] != PackageManager.PERMISSION_GRANTED){ // 요청 실패시 나오는 텍스트
            Toast.makeText(this, "거부당했엉 ㅠㅜㅜㅜ", Toast.LENGTH_SHORT).show()
        }
    }
}

//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == 1000) {
//            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) { //거부
//                Toast.makeText(this@MainActivity, "거부당했쪙ㅠㅠ", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
}

private fun Intent.also(function: () -> Unit) {

}
