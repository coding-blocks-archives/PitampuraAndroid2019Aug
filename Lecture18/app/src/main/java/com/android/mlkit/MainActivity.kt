package com.android.mlkit

import android.content.pm.PackageManager
import android.graphics.Matrix
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.util.Rational
import android.view.Surface
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.camera.core.*
import androidx.core.app.ActivityCompat
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.Executor

class MainActivity : AppCompatActivity(), Executor {
    override fun execute(p0: Runnable) {
        p0.run()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            //Start your work for camera
            textureView.post {
                startCamera()
            }
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CAMERA), 1234
            )
        }
    }

    private fun startCamera() {


        val anaLyzerConfig = ImageAnalysisConfig.Builder().apply {
            val thread = HandlerThread("Label").apply {
                start()
            }
            setCallbackHandler(
                Handler(thread.looper)
            )
            setImageReaderMode(ImageAnalysis.ImageReaderMode.ACQUIRE_LATEST_IMAGE)
        }.build()


        val analyzerCase = ImageAnalysis(anaLyzerConfig).apply {
            setAnalyzer(LabelAnalyzer())
        }


        val previewConfig = PreviewConfig.Builder().apply {
            setTargetAspectRatio(Rational(1, 1))
            setLensFacing(CameraX.LensFacing.BACK)
        }.build()

        val preview = Preview(previewConfig)

        preview.setOnPreviewOutputUpdateListener {
            val parent = textureView.parent as ViewGroup
            parent.removeView(textureView)
            parent.addView(textureView, 0)
            updateTransform()
            textureView.surfaceTexture = it.surfaceTexture
        }

        CameraX.bindToLifecycle(this, preview, analyzerCase)
    }

    inner class LabelAnalyzer : ImageAnalysis.Analyzer {
        override fun analyze(image: ImageProxy, rotationDegrees: Int) {
            val x = image.planes[0]
            val y = image.planes[1]
            val z = image.planes[2]

            val xb = x.buffer.remaining()
            val yb = y.buffer.remaining()
            val zb = z.buffer.remaining()

            val result: Int = when (rotationDegrees) {
                0 -> FirebaseVisionImageMetadata.ROTATION_0
                90 -> FirebaseVisionImageMetadata.ROTATION_90
                180 -> FirebaseVisionImageMetadata.ROTATION_180
                270 -> FirebaseVisionImageMetadata.ROTATION_270
                else -> FirebaseVisionImageMetadata.ROTATION_0

            }
            val data = ByteArray(xb + yb + zb)
            val metaData = FirebaseVisionImageMetadata.Builder()
                .setFormat(FirebaseVisionImageMetadata.IMAGE_FORMAT_YV12)
                .setHeight(image.height)
                .setWidth(image.width)
                .setRotation(result)
                .build()

            val labelImage = FirebaseVisionImage.fromByteArray(data, metaData)

            FirebaseVision.getInstance().getOnDeviceImageLabeler()
                .processImage(labelImage)
                .addOnSuccessListener {
                    if (it.isNotEmpty()) {
                        label.text = it[0].text + "   " + it[0].confidence
                    }
                }

        }

    }

    private fun updateTransform() {
        val matrix = Matrix()

        val centerX = textureView.width / 2f
        val centerY = textureView.height / 2f

        val rotationDegress = when (textureView.display.rotation) {
            Surface.ROTATION_0 -> 0
            Surface.ROTATION_90 -> 90
            Surface.ROTATION_180 -> 180
            Surface.ROTATION_270 -> 270
            else -> return
        }

        matrix.postRotate(-rotationDegress.toFloat(), centerX, centerY)

        textureView.setTransform(matrix)
    }
}
