package com.example.drawlayoutdemo

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import androidx.fragment.app.Fragment
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions


/**
 * ImageLoader
 *
 * @author  joker
 * @date    2018/4/23
 * @since   1.0
 */

object ImageLoader {

    //---------------------------------------context-----------------------------------------------//

    fun loadImage(context: Context?, url: String, imageView: ImageView) {
//        Glide.with(context).load(url).apply(RequestOptions.centerCropTransform()).into(imageView)
        if (context != null)
            Glide.with(context).load(url).transition(withCrossFade()).into(imageView)
    }

    fun loadCircleImage(context: Context?, url: String, imageView: ImageView) {
        if (context != null)
            Glide.with(context).load(url).apply(RequestOptions.circleCropTransform()).into(imageView)
    }

    fun loadCircleImage(context: Context?, drawable: Drawable, imageView: ImageView) {
        if (context != null)
            Glide.with(context).load(drawable).apply(RequestOptions.circleCropTransform()).into(imageView)
    }

    fun loadRoundedImage(context: Context?, url: String, imageView: ImageView, roundRadius: Int) {
        if (context != null)
            Glide.with(context).load(url).apply(RequestOptions()
                    .transforms(CenterCrop(), RoundedCorners(roundRadius)))
                    .into(imageView)
    }

    fun loadChatImage(context: Context?, url: String, imageView: ImageView, roundRadius: Int) {
        if (context != null)
            Glide.with(context).load(url).apply(RequestOptions()
                    .transforms(RoundedCorners(roundRadius)))
                    .into(imageView)
    }

    fun loadImageFitCenter(context: Context?, url: String, imageView: ImageView) {
        if (context != null)
            Glide.with(context).load(url).apply(RequestOptions.fitCenterTransform()).into(imageView)
    }


    //---------------------------------------v4 fragment--------------------------------------------//

    fun loadImage(fragment: Fragment?, url: String, imageView: ImageView) {
//        Glide.with(context).load(url).apply(RequestOptions.centerCropTransform()).into(imageView)
        if (fragment != null)
            Glide.with(fragment).load(url).transition(withCrossFade()).into(imageView)
    }

    fun loadCircleImage(fragment: Fragment?, url: String, imageView: ImageView) {
        if (fragment != null)
            Glide.with(fragment).load(url).apply(RequestOptions.circleCropTransform()).into(imageView)
    }

    fun loadCircleImage(fragment: Fragment?, drawable: Drawable, imageView: ImageView) {
        if (fragment != null)
            Glide.with(fragment).load(drawable).apply(RequestOptions.circleCropTransform()).into(imageView)
    }

    fun loadRoundedImage(fragment: Fragment?, url: String, imageView: ImageView, roundRadius: Int) {
        if (fragment != null)
            Glide.with(fragment).load(url).apply(RequestOptions()
                    .transforms(CenterCrop(), RoundedCorners(roundRadius)))
                    .into(imageView)
    }

    fun loadImageFitCenter(fragment: Fragment?, url: String, imageView: ImageView) {
        if (fragment != null)
            Glide.with(fragment).load(url).apply(RequestOptions.fitCenterTransform()).into(imageView)
    }

    //-----------------------------------------Activity--------------------------------------------//

    fun loadImage(activity: Activity?, url: String, imageView: ImageView) {
//        Glide.with(context).load(url).apply(RequestOptions.centerCropTransform()).into(imageView)
        if (activity != null)
            Glide.with(activity).load(url).transition(withCrossFade()).into(imageView)
    }

    fun loadCircleImage(activity: Activity?, url: String, imageView: ImageView) {
        if (activity != null)
            Glide.with(activity).load(url).apply(RequestOptions.circleCropTransform()).into(imageView)
    }

    fun loadCircleImage(activity: Activity?, drawable: Drawable, imageView: ImageView) {
        if (activity != null)
            Glide.with(activity).load(drawable).apply(RequestOptions.circleCropTransform()).into(imageView)
    }

    fun loadRoundedImage(activity: Activity?, url: String, imageView: ImageView, roundRadius: Int) {
        if (activity != null)
            Glide.with(activity).load(url).apply(RequestOptions()
                    .transforms(CenterCrop(), RoundedCorners(roundRadius)))
                    .into(imageView)
    }

    fun loadImageFitCenter(activity: Activity?, url: String, imageView: ImageView) {
        if (activity != null)
            Glide.with(activity).load(url).apply(RequestOptions.fitCenterTransform()).into(imageView)
    }
}