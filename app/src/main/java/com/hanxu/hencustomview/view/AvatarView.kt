package com.hanxu.hencustomview.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.hanxu.hencustomview.R
import com.hanxu.hencustomview.px

private val IMAGE_WIDTH = 200f.px()
private val IMAGE_PADDING = 50f.px()
private val XFERMODE = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

// 圆形图片
class AvatarView(context: Context?, attr: AttributeSet?) : View(context, attr) {

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    // 离屏缓冲区域
    private val bounds = RectF(
        IMAGE_PADDING, IMAGE_PADDING,
        IMAGE_PADDING + IMAGE_WIDTH, IMAGE_PADDING + IMAGE_WIDTH
    )

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        // 设置离屏缓冲
        val count = canvas.saveLayer(bounds, null)
        canvas.drawOval(
            IMAGE_PADDING, IMAGE_PADDING,
            IMAGE_PADDING + IMAGE_WIDTH, IMAGE_PADDING + IMAGE_WIDTH, paint
        )
        paint.xfermode = XFERMODE
        canvas.drawBitmap(getAvatar(IMAGE_WIDTH.toInt()), IMAGE_PADDING, IMAGE_PADDING, paint)

        paint.xfermode = null
        // 恢复离屏缓冲
        canvas.restoreToCount(count)
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