package com.sumie.testview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

val RADIUS = 100f.dp2px

class TestView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    // 默认给抗锯齿属性
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path = Path()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        path.reset()
        path.addCircle(width / 2f, height / 2f, RADIUS, Path.Direction.CCW)
        path.addRect(width / 2f - RADIUS, height / 2f, width / 2f + RADIUS, height / 2f + 2 * RADIUS, Path.Direction.CW)
        // 需要管方向，左+1，右-1，最终为0 为外部，其他为内部
        path.fillType = Path.FillType.WINDING
        // 不管方向，每遇到一个交点+1，最终结果 偶数为外部，基数为内部
        path.fillType = Path.FillType.EVEN_ODD
        // clockwise
        // counter-clockwise
    }

    override fun onDraw(canvas: Canvas) {
//        canvas.drawLine(100f, 100f, 200f, 200f, paint)
//        canvas.drawCircle(width / 2f, height / 2f, RADIUS, paint)
        canvas.drawPath(path, paint)
    }
}