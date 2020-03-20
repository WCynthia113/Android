package com.wcynthia.bitmaploading

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.io.BufferedReader

class MainActivity : AppCompatActivity() {

    private fun decodeSampledBitmapFromResource(
        r: Resources,
        resId: Int,
        reqWidth: Int,
        reqHeight: Int
    ):Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(r, resId, options)
        options.inSampleSize = calculateInSampleSize(options, reqHeight, reqWidth)
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeResource(r,resId,options)
    }

    private fun calculateInSampleSize(
        options: BitmapFactory.Options,
        reqHeight: Int,
        reqWidth: Int
    ):Int {
        val height = options.outHeight
        val width = options.outWidth
        var inSampleSize = 1

        if(height>reqHeight||width>reqWidth){
            val halfHeight = height/2
            val halfWidth = width/2
            while ((halfHeight/inSampleSize)>=reqHeight&&(halfWidth/inSampleSize)>=reqWidth){
                inSampleSize *=2
            }
        }
        return inSampleSize
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
