package com.seosh817.animationcollection.drawable.gradient

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.util.AttributeSet
import com.seosh817.animationcollection.drawable.AnimatedImageView

class AnimatedFadeImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?= null,
    defStyleAttrs: Int = 0
) : AnimatedImageView(context, attrs, defStyleAttrs){

    override fun setAnimationDrawable(drawable: AnimationDrawable?) {
        super.setAnimationDrawable(drawable)
        drawable?.run {
            setEnterFadeDuration(0)
            setExitFadeDuration(1000)
        }
    }
}