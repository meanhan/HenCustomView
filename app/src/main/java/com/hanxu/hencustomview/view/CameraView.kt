package com.hanxu.hencustomview.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.withSave
import com.hanxu.hencustomview.R
import com.hanxu.hencustomview.px

private val BITMAP_SIZE = 250f.px()
private val BITMAP_PADDING = 60f.px()

// 三维旋转 是 Camera在坐标系中的投影
class CameraView(context: Context?, attr: AttributeSet?) : View(context, attr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = getAvatar(BITMAP_SIZE.toInt())

    // Camera 三维旋转
    private val camera = Camera()

    init {
        //沿X轴旋转
        camera.rotateX(30f)
        camera.setLocation(0f, 0f, -8 * resources.displayMetrics.density)
    }

    override fun onDraw(canvas: Canvas) {
        // 翻页效果  上半部分绘制
        canvas.save()
        canvas.translate(BITMAP_PADDING + BITMAP_SIZE / 2, BITMAP_PADDING + BITMAP_SIZE / 2)
        canvas.rotate(-30f)
        // 扩大剪裁区域
        canvas.clipRect(-BITMAP_SIZE, -BITMAP_SIZE, BITMAP_SIZE, 0f)
        canvas.rotate(30f)
        canvas.translate(-(BITMAP_PADDING + BITMAP_SIZE / 2), -(BITMAP_PADDING + BITMAP_SIZE / 2))
        canvas.drawBitmap(bitmap, BITMAP_PADDING, BITMAP_PADDING, paint)
        canvas.restore()

        // 翻页效果  下半部分绘制
        // canvas.withSave 等同于canvas.save() canvas.restore()
        canvas.withSave {
            // 移动坐标系
            canvas.translate(BITMAP_PADDING + BITMAP_SIZE / 2, BITMAP_PADDING + BITMAP_SIZE / 2)
            canvas.rotate(-30f)
            // Camera应用于Canvas
            camera.applyToCanvas(canvas)
            canvas.clipRect(-BITMAP_SIZE, 0f, BITMAP_SIZE, BITMAP_SIZE)
            canvas.rotate(30f)
            canvas.translate(
                -(BITMAP_PADDING + BITMAP_SIZE / 2),
                -(BITMAP_PADDING + BITMAP_SIZE / 2)
            )
            canvas.drawBitmap(bitmap, BITMAP_PADDING, BITMAP_PADDING, paint)
        }
    }

    // 优化版本的 读取图片
    private fun getAvatar(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        // 只解码尺寸 效率高
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.avatar_girl, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.avatar_girl, options)
    }

}