package com.example.drawlayoutdemo

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.widget.AppCompatImageView
import android.text.TextPaint
import android.util.AttributeSet


/**
 * TextImageView
 *
 * @author  joker
 * @date    2018/5/3
 * @since   1.0
 */
class TextImageView : AppCompatImageView {

    private val mTextPaint by lazy {
        TextPaint(Paint.ANTI_ALIAS_FLAG)
    }

    private var mLabel: CharSequence? = null

    private var mTextSize: Float = 12F

    private var mTexColor: Int = -1

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context, attrs)
    }

    init {
        mTextPaint.textAlign = Paint.Align.CENTER
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        if (attrs == null) return

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.TextImageView)

        mTextSize = typedArray.getDimensionPixelSize(R.styleable.TextImageView_tiv_TextSize, resources.getDimensionPixelSize(R.dimen.sp_18)).toFloat()
        mTexColor = typedArray.getColor(R.styleable.TextImageView_tiv_TextColor, Color.BLACK)

        typedArray.recycle()
    }

    fun setLabel(label: CharSequence) {
        mLabel = label
        invalidate()
    }

    fun getLabel(): CharSequence? {
        return mLabel
    }

    fun setTextSize(textSize: Int) {
        mTextSize = context.dip(textSize).toFloat()
        invalidate()
    }

    private fun Context.dip(value: Int): Int = (value * resources.displayMetrics.density).toInt()
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (!mLabel.isNullOrEmpty()) {

            mTextPaint.color = mTexColor
            mTextPaint.textSize = mTextSize
            // 文字宽
            //val textWidth = mTextPaint.measureText(mLabel.toString())

            //默认居中绘制
            val startX = width / 2F
            // 文字baseline在y轴方向的位置
            val baseLineY = height / 2 + Math.abs(mTextPaint.ascent() + mTextPaint.descent()) / 2

            canvas.drawText(mLabel.toString(), startX, baseLineY, mTextPaint)
        }
    }
}