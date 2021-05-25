package com.hanxu.hencustomview

import android.content.res.Resources
import android.util.TypedValue

// 扩展函数 dp转px
fun Float.px(): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        // 获取系统像素密度
        Resources.getSystem().displayMetrics
    )
}

val Float.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

val Int.dp
    get() = this.toFloat().dp