package com.sumie.customlayoutlearning

import android.content.res.Resources
import android.util.TypedValue

var Float.dp: Float
    set(value) {}
    get() {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this,
            Resources.getSystem().displayMetrics
        )
    }

var Int.dp: Float
    set(value) {}
    get() {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            Resources.getSystem().displayMetrics
        )
    }