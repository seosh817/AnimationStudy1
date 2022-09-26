package com.seosh817.animationcollection.drawable.gradient

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider

object CircleOutlineProvider : ViewOutlineProvider() {

    override fun getOutline(view: View, outline: Outline) {
        outline.setOval(
            view.paddingLeft, view.paddingTop,
            view.width - view.paddingRight,
            view.height - view.paddingBottom
        )
    }
}

class RoundRectOutlineProvider(private val radius: Float) : ViewOutlineProvider() {
    override fun getOutline(view: View, outline: Outline) {
        outline.setRoundRect(
            view.paddingLeft, view.paddingTop,
            view.width - view.paddingRight,
            view.height - view.paddingBottom,
            radius
        )
    }
}

fun View.clipToOval(clip: Boolean) {
    clipToOutline = clip
    outlineProvider = if (clip) CircleOutlineProvider else null
}

fun View.clipToRoundRect(radius: Float) {
    clipToOutline = true
    outlineProvider = RoundRectOutlineProvider(radius)
}