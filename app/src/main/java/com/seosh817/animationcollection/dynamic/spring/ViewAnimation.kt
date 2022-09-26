package com.seosh817.animationcollection.dynamic.spring

import android.view.View
import androidx.dynamicanimation.animation.DynamicAnimation.*
import com.seosh817.animationcollection.util.spring

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
        spring(ALPHA).animateToFinalPosition(0f)
        spring(ROTATION).animateToFinalPosition(0f)
        spring(TRANSLATION_Y).animateToFinalPosition(0f)
    }

    fun View.animateOutForCloseButton() {
        spring(ALPHA).animateToFinalPosition(1f)
        spring(ROTATION).animateToFinalPosition(-90f)
        spring(TRANSLATION_Y).animateToFinalPosition((-width).toFloat())
    }
}