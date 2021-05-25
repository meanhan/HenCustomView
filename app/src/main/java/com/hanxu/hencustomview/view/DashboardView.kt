package com.hanxu.hencustomview.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.hanxu.hencustomview.px
import kotlin.math.cos
import kotlin.math.sin

private val RADIUS = 150f.px()
private const val OPEN_ANGLE = 120f
private val DASH_WIDTH = 3f.px()
private val DASH_LENGTH = 10f.px()
private val POINTER_LENGTH = 120f.px()

class DashboardView(context: Context?, attr: AttributeSet?) : View(context, attr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val dash = Path()

    // 圆弧path
    private val path = Path()
    private lateinit var pathEffect: PathEffect

    init {
        paint.strokeWidth = 3f.px()
        paint.style = Paint.Style.STROKE
        paint.color = Color.parseColor("#0080FF")
        // 初始化刻度小矩形
        dash.addRect(0f, 0f, DASH_WIDTH, DASH_LENGTH, Path.Direction.CCW)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        path.reset()
        path.addArc(
            width / 2 - RADIUS, height / 2 - RADIUS, width / 2 + RADIUS,
            height / 2 + RADIUS, 90 + OPEN_ANGLE / 2, 360 - OPEN_ANGLE
        )
        // 测量圆弧长度
        val pathMeasure = PathMeasure(path, false)
        pathEffect = PathDashPathEffect(
            dash, (pathMeasure.length - DASH_WIDTH) / 20f, 0f,
            PathDashPathEffect.Style.ROTATE
        )
    }

    override fun onDraw(canvas: Canvas) {
        // 画圆弧
        canvas.drawPath(path, paint)
        // 画刻度
        paint.pathEffect = pathEffect
        canvas.drawPath(path, paint)
        paint.pathEffect = null

        // 画指针  利用三角函数计算位置
        canvas.drawLine(
            width / 2f, height / 2f,
            width / 2 + POINTER_LENGTH * cos(markToRadians(8)).toFloat(),
            height / 2 + POINTER_LENGTH * sin(markToRadians(8)).toFloat(),
            paint
        )
    }

    // 根据指针指向第几个刻度 计算角度
    private fun markToRadians(mark: Int) =
        Math.toRadians((90 + OPEN_ANGLE / 2 + (360 - OPEN_ANGLE) / 20f * mark).toDouble())
}