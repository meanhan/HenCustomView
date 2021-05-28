package com.hanxu.hencustomview.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.hanxu.hencustomview.R
import com.hanxu.hencustomview.dp

private val IMAGE_SIZE = 150.dp
private val IMAGE_PADDING = 50.dp

class MultilineTextView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    val text =
        "Morbi et urna velit. Mauris scelerisque, risus in fermentum pellentesque, justo tellus elementum ligula, sed porta lectus leo eget nisl. Proin sollicitudin, risus in ultrices consectetur, neque purus luctus metus, quis sagittis ligula dui sit amet dui. Etiam in nulla semper, facilisis ipsum nec, tincidunt mauris. Donec maximus nec nisi sit amet egestas. Sed ac fermentum felis. Ut lectus enim, semper non lacus id, sodales posuere dolor. Praesent gravida, nisi ut tempor hendrerit, arcu nunc pharetra risus, in vestibulum libero lectus et tortor. Interdum et malesuada fames ac ante ipsum primis in faucibus. Curabitur lobortis massa leo, vel tempor justo dignissim nec. Sed pretium, ligula ut tincidunt semper, leo sem volutpat augue, sit amet aliquet massa libero at lectus. Curabitur condimentum elementum augue, sit amet cursus lectus lacinia nec."
    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 16.dp
    }
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 16.dp
    }
    private val bitmap = getAvatar(IMAGE_SIZE.toInt())
    private val fontMetrics = Paint.FontMetrics()

    override fun onDraw(canvas: Canvas) {
//    val staticLayout = StaticLayout(text, textPaint, width, Layout.Alignment.ALIGN_NORMAL, 1f, 0f, false)
//    staticLayout.draw(canvas)
        canvas.drawBitmap(bitmap, width - IMAGE_SIZE, IMAGE_PADDING, paint)
        paint.getFontMetrics(fontMetrics)
        val measuredWidth = floatArrayOf(0f)
        var start = 0
        var count: Int
        var verticalOffset = -fontMetrics.top
        var maxWidth: Float
        while (start < text.length) {
            maxWidth = if (verticalOffset + fontMetrics.bottom < IMAGE_PADDING
                || verticalOffset + fontMetrics.top > IMAGE_PADDING + IMAGE_SIZE
            ) {
                width.toFloat()
            } else {
                width.toFloat() - IMAGE_SIZE
            }
            count = paint.breakText(text, start, text.length, true, maxWidth, measuredWidth)
            canvas.drawText(text, start, start + count, 0f, verticalOffset, paint)
            start += count
            verticalOffset += paint.fontSpacing
        }
    }

    fun getAvatar(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.avatar_girl, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.avatar_girl, options)
    }
}