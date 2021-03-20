package io.github.slflfl12.customviewanimation.drawable

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.widget.AppCompatImageView

open class AnimatedImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private var anim: AnimationDrawable? = null
    private var isAttached: Boolean = false
    private var allowAnimation = true

    private var drawableId: Int = 0

    fun setAllowAnimation(allowAnimation: Boolean) {
        if (this.allowAnimation != allowAnimation) {
            this.allowAnimation = allowAnimation
            updateAnim()
            if(this.allowAnimation.not()) {
                anim?.setVisible(visibility == View.VISIBLE, true)
            }
        }
    }

    private fun updateAnim() {
        val drawable = drawable
        if (isAttached) {
            anim?.stop()
        }
        if (drawable is AnimationDrawable) {
            setAnimationDrawable(drawable)
            if(isShown && allowAnimation) {
                drawable.start()
            }
        } else {
            setAnimationDrawable(null)
        }
        Log.d("seunghwan", "anim = ${anim}")

    }

    protected open fun setAnimationDrawable(drawable: AnimationDrawable?) {
        this.anim = drawable
    }

    override fun setImageDrawable(drawable: Drawable?) {
        drawableId = if(drawable!= null) {
            if(drawableId == drawable.hashCode()) return
            drawable.hashCode()
        } else {
            0
        }
        super.setImageDrawable(drawable)
        updateAnim()
        Log.d("seunghwan", "imageDrawable = ${drawableId}")

    }

    override fun setImageResource(resId: Int) {
        if(drawableId == resId) return
        drawableId = resId
        super.setImageResource(resId)
        updateAnim()
        Log.d("seunghwan", "imageResource = ${drawableId}")
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        isAttached = true
        updateAnim()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (anim != null) {
            anim!!.stop()
        }
        isAttached = false
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        if(isShown && allowAnimation) {
            anim?.start()
        } else {
            anim?.stop()
        }
    }
}