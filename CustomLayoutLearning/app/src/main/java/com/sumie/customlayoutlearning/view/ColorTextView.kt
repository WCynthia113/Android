package com.sumie.customlayoutlearning.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.sumie.customlayoutlearning.dp
import java.util.*

private val COLORS = arrayOf(
    Color.BLACK,
    Color.CYAN,
    Color.GRAY,
    Color.GREEN,
    Color.YELLOW,
    Color.MAGENTA
)

private val TEXT_SIZES = intArrayOf(16, 22, 20)
private val CORNER_RADIUS = 4.dp
private val X_PADDING = 16.dp.toInt()
private val Y_PADDING = 8.dp.toInt()

class ColorTextView(context: Context, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {
    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val random = Random()

    init {
        setTextColor(Color.WHITE)
        textSize = TEXT_SIZES[random.nextInt(TEXT_SIZES.size)].toFloat()
        paint.color = COLORS[random.nextInt(COLORS.size)]
        setPadding(X_PADDING, Y_PADDING, X_PADDING, Y_PADDING)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawRoundRect(0f,0f,width.toFloat(),height.toFloat(), CORNER_RADIUS, CORNER_RADIUS,paint)
        super.onDraw(canvas)
    }
}