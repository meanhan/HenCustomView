package com.hanxu.hencustomview

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cameraViewAnimation()
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
}