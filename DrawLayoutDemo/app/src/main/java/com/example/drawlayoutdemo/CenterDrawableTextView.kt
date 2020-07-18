package com.example.drawlayoutdemo

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView


/**
 * @author  fengjunxia
 * @date    2020/4/2
 * @since   1.0
 */
class CenterDrawableTextView : AppCompatTextView {
    constructor(context: Context?, attrs: AttributeSet?,
                defStyle: Int) : super(context, attrs, defStyle) {
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?) : super(context) {}

    override fun onDraw(canvas: Canvas) {
        val drawables = compoundDrawables
        if (drawables != null) {
            val drawableLeft = drawables[0]
            if (drawableLeft != null) {
                val textWidth = paint.measureText(text.toString())
                val drawablePadding = compoundDrawablePadding
                var drawableWidth = 0
                drawableWidth = drawableLeft.intrinsicWidth
                val bodyWidth = textWidth + drawableWidth + drawablePadding
                canvas.translate((width - bodyWidth) / 2, 0F)
            }
        }
        super.onDraw(canvas)
    }
}