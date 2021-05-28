package com.hanxu.hencustomview.view

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Build
import android.util.AttributeSet
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import com.hanxu.hencustomview.R
import com.hanxu.hencustomview.dp
import com.hanxu.hencustomview.px

private val CIRCLE_BACKGROUND = Color.parseColor("#E6E6E6")
private val CIRCLE_COLOR = Color.parseColor("#58ACFA")
private val CIRCLE_WIDTH = 20.dp
private val RADIUS = 150.dp

class SportView(context: Context, attr: AttributeSet?) : View(context, attr) {


    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 45.dp
        typeface = ResourcesCompat.getFont(context, R.font.font)
        textAlign = Paint.Align.CENTER
    }
    private val textBounds = Rect()
    private val fontMetrics = Paint.FontMetrics()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {

    }

    override fun onDraw(canvas: Canvas) {
        // 绘制圆环背景
        paint.style = Paint.Style.STROKE
        paint.color = CIRCLE_BACKGROUND
        paint.strokeWidth = CIRCLE_WIDTH
        canvas.drawCircle(width / 2f, height / 2f, RADIUS, paint)

        // 绘制圆环
        paint.color = CIRCLE_COLOR
        paint.strokeCap = Paint.Cap.ROUND
        canvas.drawArc(
            width / 2f - RADIUS,
            height / 2f - RADIUS,
            width / 2 + RADIUS,
            height / 2 + RADIUS,
            -90f,
            180f,
            false,
            paint
        )

        //绘制文字
        paint.style = Paint.Style.FILL
        /**
         *  获取文字区域 获取文字偏移量 适合静态文字
         *  文字纵向偏移量 (textBounds.top + textBounds.bottom) / 2f 使文字纵向居中
         */
//        paint.getTextBounds("SportView", 0, "SportView".length, textBounds)
//        canvas.drawText(
//            "SportView",
//            width / 2f,
//            height / 2f - (textBounds.top + textBounds.bottom) / 2f,
//            paint
//        )

        /**
         * 动态文字纵向居中方式
         * (fontMetrics.ascent + fontMetrics.descent)
         */
        paint.getFontMetrics(fontMetrics)
        canvas.drawText(
            "SportView",
            width / 2f,
            height / 2f - (fontMetrics.ascent + fontMetrics.descent) / 2f,
            paint
        )
    }

}