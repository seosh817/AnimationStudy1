package io.github.slflfl12.customviewanimation.dynamic.spring

import android.view.View
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.DynamicAnimation.*
import io.github.slflfl12.customviewanimation.util.spring

interface ViewAnimation {

    fun View.animateInForDim() {
        spring(ALPHA).animateToFinalPosition(1f)
    }

    fun View.animateOutForDim() {
        spring(ALPHA).animateToFinalPosition(0f)
    }

    fun View.animateInForHeader() {
        spring(ALPHA).animateToFinalPosition(1f)
        spring(TRANSLATION_Y).animateToFinalPosition(0f)
    }

    fun View.animateOutForHeader() {
        spring(ALPHA).animateToFinalPosition(0f)
        spring(TRANSLATION_Y).animateToFinalPosition(-height.toFloat())
    }

    fun View.animateInForCloseButton() {
        spring(ROTATION).animateToFinalPosition(0f)
        spring(TRANSLATION_Y).animateToFinalPosition(0f)
    }

    fun View.animateOutForCloseButton() {
        spring(ROTATION).animateToFinalPosition(-90f)
        spring(TRANSLATION_Y).animateToFinalPosition((-width).toFloat())
    }
}