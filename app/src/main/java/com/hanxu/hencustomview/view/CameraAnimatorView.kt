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

class CameraAnimatorView(context: Context?, attr: AttributeSet?) : View(context, attr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val bitmap = getAvatar(BITMAP_SIZE.toInt())
    private val camera = Camera()

    var topFlip = 0f
        set(value) {
            field = value
            invalidate()
        }
    var bottomFlip = 0f
        set(value) {
            field = value
            invalidate()
        }
    var flipRotation = 0f
        set(value) {
            field = value
            invalidate()
        }

    init {
        //沿X轴旋转

        camera.setLocation(0f, 0f, -8 * resources.displayMetrics.density)
    }

    override fun onDraw(canvas: Canvas) {
        // 翻页效果  上半部分绘制
        canvas.save()
        canvas.translate(BITMAP_PADDING + BITMAP_SIZE / 2, BITMAP_PADDING + BITMAP_SIZE / 2)
        canvas.rotate(-flipRotation)
        camera.save()
        camera.rotateX(topFlip)
        // Camera应用于Canvas
        camera.applyToCanvas(canvas)
        camera.restore()
        // 扩大剪裁区域
        canvas.clipRect(-BITMAP_SIZE, -BITMAP_SIZE, BITMAP_SIZE, 0f)
        canvas.rotate(flipRotation)
        canvas.translate(-(BITMAP_PADDING + BITMAP_SIZE / 2), -(BITMAP_PADDING + BITMAP_SIZE / 2))
        canvas.drawBitmap(bitmap, BITMAP_PADDING, BITMAP_PADDING, paint)
        canvas.restore()

        // 翻页效果  下半部分绘制
        // canvas.withSave 等同于canvas.save() canvas.restore()
        canvas.withSave {
            // 移动坐标系
            canvas.translate(BITMAP_PADDING + BITMAP_SIZE / 2, BITMAP_PADDING + BITMAP_SIZE / 2)
            canvas.rotate(-flipRotation)
            camera.save()
            camera.rotateX(bottomFlip)
            // Camera应用于Canvas
            camera.applyToCanvas(canvas)
            camera.restore()
            canvas.clipRect(-BITMAP_SIZE, 0f, BITMAP_SIZE, BITMAP_SIZE)
            canvas.rotate(flipRotation)
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