package com.sumie.testview.view

import android.content.res.Resources
import android.util.TypedValue

object Utils {
    fun dp2px(value: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value,
            Resources.getSystem().displayMetrics
        )
    }
}

val Float.dp2px
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )