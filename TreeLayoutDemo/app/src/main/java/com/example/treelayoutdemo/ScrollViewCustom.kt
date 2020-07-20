package com.hp.goalgo.ui.views

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.widget.HorizontalScrollView

/*
* 自定义监听水平滑动
* */

class ScrollViewCustom(context: Context, attrs: AttributeSet) : HorizontalScrollView(context, attrs) {
    private var scrollerTask: Runnable? = null
    private var initPosition: Int = 0
    private val newCheck = 100
    private var childWidth = 0

    private var onScrollstopListner: OnScrollStopListner? = null

    interface OnScrollStopListner {
        /**
         * scroll have stoped
         */
        fun onScrollStoped()

        /**
         * scroll have stoped, and is at left edge
         */
        fun onScrollToLeftEdge()

        /**
         * scroll have stoped, and is at right edge
         */
        fun onScrollToRightEdge()

        /**
         * scroll have stoped, and is at middle
         */
        fun onScrollToMiddle()
    }

    init {
        scrollerTask = Runnable {
            val newPosition = scrollX
            if (initPosition - newPosition == 0) {
                if (onScrollstopListner == null) {
                    return@Runnable
                }
                onScrollstopListner !!.onScrollStoped()
                val outRect = Rect()
                getDrawingRect(outRect)
                if (scrollX == 0) {
                    onScrollstopListner !!.onScrollToLeftEdge()
                } else if (childWidth + paddingLeft + paddingRight == outRect.right) {
                    onScrollstopListner !!.onScrollToRightEdge()
                } else {
                    onScrollstopListner !!.onScrollToMiddle()
                }
            } else {
                initPosition = scrollX
                postDelayed(scrollerTask, newCheck.toLong())
            }
        }
    }


    fun setOnScrollStopListner(listner: OnScrollStopListner) {
        onScrollstopListner = listner
    }

    fun startScrollerTask() {
        initPosition = scrollX
        postDelayed(scrollerTask, newCheck.toLong())
        checkTotalWidth()
    }

    private fun checkTotalWidth() {
        if (childWidth > 0) {
            return
        }
        for (i in 0 until childCount) {
            childWidth += getChildAt(i).width
        }
    }


    /*
    * 设置不让外面的:drawerLayout拦截子View的水平滑动事假
    * */
//    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
//        parent.requestDisallowInterceptTouchEvent(true)// 让父类不拦截触摸事件
//        return super.dispatchTouchEvent(ev)
//    }

}

