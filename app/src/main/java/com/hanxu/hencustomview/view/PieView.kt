package com.hanxu.hencustomview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.hanxu.hencustomview.px
import kotlin.math.cos
import kotlin.math.sin

private val RADIUS = 150f.px()
private val ANGLES = floatArrayOf(60f, 90f, 50f, 100f, 60f)
private val COLORS = listOf(
    Color.parseColor("#5580FF"),
    Color.parseColor("#FAAC58"), Color.parseColor("#0A2A29"),
    Color.parseColor("#FE2E64"), Color.parseColor("#F3F781")
)

// 扇形偏移距离
private val OFFSET_LENGTH = 20f.px()

class PieView(context: Context?, attr: AttributeSet?) : View(context, attr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#5580FF")
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {

    }

    override fun onDraw(canvas: Canvas) {
        var startAngle = 0f
        // 利用循环 画出不同颜色的扇形
        for ((index, angle) in ANGLES.withIndex()) {
            paint.color = COLORS[index]

            if (index == 3) {
                canvas.save()
                // 三角函数计算扇形偏移 X Y
                canvas.translate(
                    OFFSET_LENGTH * cos(Math.toRadians(startAngle + angle / 2f.toDouble())).toFloat(),
                    OFFSET_LENGTH * sin(Math.toRadians(startAngle + angle / 2f.toDouble())).toFloat()
                )
            }

            canvas.drawArc(
                width / 2 - RADIUS, height / 2 - RADIUS, width / 2 + RADIUS,
                height / 2 + RADIUS, startAngle, angle, true, paint
            )
            startAngle += angle

            if (index == 3) {
                canvas.restore()
            }
        }
    }

}