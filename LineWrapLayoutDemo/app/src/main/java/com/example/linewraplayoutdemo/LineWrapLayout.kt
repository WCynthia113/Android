package com.example.linewraplayoutdemo

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import kotlin.math.max


/**
 * Created by Wenxi on 2020/8/15 09:42
 */
class LineWrapLayout : ViewGroup {

    private var maxWidth = 0

    private var horizontalMargin = 0
    private var verticalMargin = 0

    var mAdapter: Adapter? = null

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val a = context.obtainStyledAttributes(attrs, R.styleable.LineWrapLayout)
        try {
            horizontalMargin = a.getDimensionPixelOffset(
                R.styleable.LineWrapLayout_item_horizontal_margin, 0
            )
            verticalMargin = a.getDimensionPixelOffset(
                R.styleable.LineWrapLayout_item_vertical_margin, 0
            )
            maxWidth =
                a.getDimensionPixelOffset(
                    R.styleable.LineWrapLayout_maxWidth, 0
                )
        } finally {
            a.recycle()
        }

    }

    private val mAllChildViews: ArrayList<ArrayList<View>> = arrayListOf()
    private val mLineHeights: ArrayList<Int> = arrayListOf()

    override fun generateDefaultLayoutParams(): LayoutParams {
        return MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val sizeWidth = MeasureSpec.getSize(widthMeasureSpec)
        val sizeHeight = MeasureSpec.getSize(heightMeasureSpec)

        val modeWidth = MeasureSpec.getMode(widthMeasureSpec)
        val modeHeight = MeasureSpec.getMode(heightMeasureSpec)

        var mWidth = 0
        var mHeight = 0

        var lineWidth = 0
        var lineHeight = 0
        if (childCount == 0) return
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            measureChild(child, widthMeasureSpec, heightMeasureSpec)
            val lp = child.layoutParams as MarginLayoutParams

            val childWidth = child.measuredWidth + horizontalMargin + lp.leftMargin + lp.rightMargin
            val childHeight = child.measuredHeight + verticalMargin + lp.topMargin + lp.bottomMargin

            val tempWidth = lineWidth + childWidth
            if (tempWidth <= (maxWidth - paddingStart - paddingEnd)) {
                lineWidth = tempWidth
                lineHeight = max(lineHeight, childHeight)
            } else {
                mWidth = max(lineWidth, mWidth)
                lineWidth = childWidth
                mHeight += lineHeight
                lineHeight = childHeight
            }
        }
        mHeight += lineHeight
        mWidth = max(lineWidth, mWidth)

        mHeight = mHeight + paddingTop + paddingBottom - verticalMargin
        mWidth = mWidth + paddingStart + paddingEnd - horizontalMargin
        if (modeWidth == MeasureSpec.EXACTLY) {
            mWidth = sizeWidth
        }
        if (modeHeight == MeasureSpec.EXACTLY) {
            mHeight = sizeHeight
        }
        setMeasuredDimension(mWidth, mHeight)

    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        mAllChildViews.clear()
        mLineHeights.clear()

        var lineWidth = 0
        var lineHeight = 0

        var lineViews = arrayListOf<View>()

        if (childCount == 0) return
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val lp = child.layoutParams as MarginLayoutParams

            val childWidth = child.measuredWidth + horizontalMargin + lp.leftMargin + lp.rightMargin
            val childHeight = child.measuredHeight + verticalMargin + lp.topMargin + lp.bottomMargin
            val tempWidth = childWidth + lineWidth
            if (tempWidth <= (maxWidth - paddingStart - paddingEnd)) {
                lineWidth = tempWidth
                lineHeight = max(lineHeight, childHeight)
                lineViews.add(child)
            } else {
                mLineHeights.add(lineHeight)
                mAllChildViews.add(lineViews)
                lineViews = arrayListOf()
                lineViews.add(child)
                lineHeight = childHeight
                lineWidth = childWidth

            }
        }
        mLineHeights.add(lineHeight)
        mAllChildViews.add(lineViews)

        var lineLeft = 0 + paddingStart
        var lineTop = 0 + paddingTop

        for (i in 0 until mAllChildViews.size) {
            lineViews = mAllChildViews[i]
            lineHeight = mLineHeights[i]
            for (j in 0 until lineViews.size) {
                val child = lineViews[j]
                val lp = child.layoutParams as MarginLayoutParams

                val lc = if (j == 0) {
                    lineLeft + lp.leftMargin
                } else {
                    lineLeft + lp.leftMargin + horizontalMargin
                }
                val tc = if (i == 0) {
                    lineTop + lp.topMargin
                } else {
                    lineTop + verticalMargin + lp.topMargin
                }
                val rc = lc + child.measuredWidth
                val bc = tc + child.measuredHeight

                child.layout(lc, tc, rc, bc)
                lineLeft = rc + lp.rightMargin
            }
            lineLeft = 0 + paddingStart
            if (i == 0) {
                lineTop = lineTop + lineHeight - verticalMargin
            } else {
                lineTop += lineHeight
            }
        }
    }
    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams? {
        return MarginLayoutParams(context, attrs)
    }

    fun setAdapter(adapter: Adapter) {
        mAdapter = adapter
        notifyDataSetChange()
    }

    fun notifyDataSetChange() {
        if (mAdapter == null) return
        this.removeAllViews()
        for (i in 0 until mAdapter!!.getItemCount()) {
            this.addView(mAdapter!!.getItemView(this, i))
        }
    }

    abstract class Adapter() {
        abstract fun getItemCount(): Int
        abstract fun getItemView(parent: ViewGroup, position: Int): View
    }
}