package com.hanxu.hencustomview.view

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import kotlin.math.min

class SquareImageView(context: Context, attrs: AttributeSet?) : AppCompatImageView(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        /**
         *   measuredWidth, measuredHeight 测量阶段期望尺寸
           一般情况与width, height相同 有可能会被父布局修改
           width, height 实际尺寸 测量阶段获取不到
         */

        val size = min(measuredWidth, measuredHeight)
        setMeasuredDimension(size, size)
    }
}