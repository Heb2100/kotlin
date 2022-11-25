//  https://stickode.tistory.com/302
package com.example.camactivity2

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.util.jar.Manifest
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    val cameraBtn = findViewById<Button>(R.id.buttonCamera) as Button
    cameraBtn.setOnClickListener(View.OnClickListener {
        requirePermissions(arrayOf(Manifest.permission.CAMERA), PERMISSION_CAMERA)
    })

    fun requirePermissions(permissions: Array<String>, requestCode: Int) {
        Logger.d("권한 요청")
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            permissionGranted(requestCode)
        } else {
            // isAllPermissionsGranted : 권한이 모두 승인 되었는지 여부 저장
            // all 메서드를 사용하면 배열 속에 들어 있는 모든 값을 체크할 수 있다.
            val isAllPermissionsGranted =
                permissions.all { checkSelfPermission(it) == PackageManager.PERMISSION_GRANTED }
            if (isAllPermissionsGranted) {
                permissionGranted(requestCode)
            } else {
                // 사용자에 권한 승인 요청
                ActivityCompat.requestPermissions(this, permissions, requestCode)
            }
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
            permissionGranted(requestCode)
        } else {
            permissionDenied(requestCode)
        }
    }

    private fun permissionGranted(requestCode: Int) {
        when (requestCode) {
            PERMISSION_CAMERA -> openCamera()
        }
    }

    private fun permissionDenied(requestCode: Int) {
        when (requestCode) {
            PERMISSION_CAMERA -> Toast.makeText(
                this,
                "카메라 권한을 승인해야 카메라를 사용할 수 있습니다.",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}

private fun openCamera() {
    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

    createImageUri(newFileName(), "image/jpg")?.let { uri ->
        realUri = uri
        // MediaStore.EXTRA_OUTPUT을 Key로 하여 Uri를 넘겨주면
        // 일반적인 Camera App은 이를 받아 내가 지정한 경로에 사진을 찍어서 저장시킨다.
        intent.putExtra(MediaStore.EXTRA_OUTPUT, realUri)
        startActivityForResult(intent, REQUEST_CAMERA)
    }
}

private fun newFileName(): String {
    val sdf = SimpleDateFormat("yyyyMMdd_HHmmss")
    val filename = sdf.format(System.currentTimeMillis())
    return "$filename.jpg"
}

private fun createImageUri(filename: String, mimeType: String): Uri? {
    var values = ContentValues()
    values.put(MediaStore.Images.Media.DISPLAY_NAME, filename)
    values.put(MediaStore.Images.Media.MIME_TYPE, mimeType)
    return this.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
}
