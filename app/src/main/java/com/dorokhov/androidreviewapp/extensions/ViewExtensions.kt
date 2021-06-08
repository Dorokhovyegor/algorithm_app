package com.dorokhov.androidreviewapp.extensions

import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation

fun View.toVisibleOrGone(isVisible: Boolean) {
    if (isVisible) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}

fun View.rotateWithAnimation(from: Float, to: Float) {
    val rotateAnim = RotateAnimation(from, to, this.width / 2f, this.height / 2f)
    rotateAnim.duration = 275
    rotateAnim.start()
    rotateAnim.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation?) {

        }

        override fun onAnimationEnd(animation: Animation?) {
           this@rotateWithAnimation.rotation = to
        }

        override fun onAnimationRepeat(animation: Animation?) {
        }
    })
    this.startAnimation(rotateAnim)
}