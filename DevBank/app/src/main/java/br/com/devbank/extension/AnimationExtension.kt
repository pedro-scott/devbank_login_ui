package br.com.devbank.extension

import android.animation.Animator
import android.view.View
import android.widget.Button
import androidx.core.view.isVisible
import com.airbnb.lottie.LottieAnimationView

fun LottieAnimationView.enableOneTime(button: Button) {
    button.isEnabled = false
    this.visibility = View.VISIBLE
    this.playAnimation()

    this.disable(button)
}

fun LottieAnimationView.disable(button: Button) {
    addAnimatorListener(object : Animator.AnimatorListener {
        override fun onAnimationStart(animation: Animator?) {}

        override fun onAnimationEnd(animation: Animator?) {
            this@disable.isVisible = false
            this@disable.visibility = View.GONE
            button.isEnabled = true
        }

        override fun onAnimationCancel(animation: Animator?) {}

        override fun onAnimationRepeat(animation: Animator?) {}
    })
}