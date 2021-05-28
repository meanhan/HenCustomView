package com.hanxu.hencustomview

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.graphics.PointF
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hanxu.hencustomview.view.ProvinceEvaluator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        cameraViewAnimation()
//        pointFAnimation()
        provinceAnimation()
    }

//    fun circleViewAnimation(){
//        val animator = ObjectAnimator.ofFloat(view,"radius",100.dp)
//        animator.startDelay = 1000
//        animator.start()
//    }

    private fun cameraViewAnimation() {
        val topFlipAnimator = ObjectAnimator.ofFloat(view, "topFlip", -50f)
        topFlipAnimator.startDelay = 1000
        topFlipAnimator.duration = 1000
//        topFlipAnimator.start()

        val bottomFlipAnimator = ObjectAnimator.ofFloat(view, "bottomFlip", 50f)
        bottomFlipAnimator.startDelay = 400
        bottomFlipAnimator.duration = 1000
//        bottomFlipAnimator.start()

        val flipRotationAnimator = ObjectAnimator.ofFloat(view, "flipRotation", 270f)
        flipRotationAnimator.startDelay = 400
        flipRotationAnimator.duration = 1000
//        flipRotationAnimator.start()

        val animatorSet = AnimatorSet()
        animatorSet.playSequentially(topFlipAnimator,bottomFlipAnimator, flipRotationAnimator )
        animatorSet.start()
    }

    private fun pointFAnimation(){
        val animator = ObjectAnimator.ofObject(view, "point", PointFEvaluator(), PointF(200.dp, 300.dp))
        animator.startDelay = 1000
        animator.duration = 2000
        animator.start()
    }

    private fun provinceAnimation(){
        val animator = ObjectAnimator.ofObject(view, "province", ProvinceEvaluator(), "澳门特别行政区")
        animator.startDelay = 500
        animator.duration = 10000
        animator.start()

    }

    class PointFEvaluator : TypeEvaluator<PointF> {
        override fun evaluate(fraction: Float, startValue: PointF, endValue: PointF): PointF {
            val startX = startValue.x
            val endX = endValue.x
            val currentX = startX + (endX - startX) * fraction
            val startY = startValue.y
            val endY = endValue.y
            val currentY = startY + (endY - startY) * fraction
            return PointF(currentX, currentY)
        }
    }
}