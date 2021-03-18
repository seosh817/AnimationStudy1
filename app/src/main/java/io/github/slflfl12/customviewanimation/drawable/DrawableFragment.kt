package io.github.slflfl12.customviewanimation.drawable

import android.animation.ValueAnimator
import android.graphics.drawable.Animatable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.animation.doOnRepeat
import androidx.core.content.ContextCompat
import androidx.core.view.postOnAnimationDelayed
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import io.github.slflfl12.customviewanimation.R
import io.github.slflfl12.customviewanimation.databinding.FragmentDrawableBinding

class DrawableFragment: Fragment() {

    private lateinit var binding: FragmentDrawableBinding

    private val animator = ValueAnimator.ofFloat(0f, 100f).apply {
        repeatMode = ValueAnimator.REVERSE
        repeatCount = ValueAnimator.INFINITE
        duration = 1000L
        interpolator = LinearInterpolator()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_drawable, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animator.doOnRepeat {
            updateRippleEffect()
        }
        animator.addUpdateListener {
            Log.d("seunghwan", "${it.animatedFraction}, ${it.animatedValue}")
            updateUI(it.animatedFraction)
        }
        animator.start()
    }

    // 0 ~ 10000
    private fun updateUI(fraction: Float) {
        binding.ivBatteryClip.setImageLevel((fraction * 10000).toInt())
    }

    private fun updateRippleEffect() {
        binding.ivAnimatedCheck.toggle()

        binding.ivBatteryVerticalChecked.performRippleEffect()
        binding.ivBatteryVerticalChecked.toggle()

        binding.ivStarIcon.toggle()

        binding.ivPortrait.run {
            if(isActivated) {
                isActivated = false
                setImageDrawableAndStart(R.drawable.ic_portrait_from_auto_rotate_animation)
            } else {
                isActivated = true
                setImageDrawableAndStart(R.drawable.ic_portrait_to_auto_rotate_animation)
            }
        }
        binding.ivLandscape.run {
            if(isActivated) {
                isActivated = false
                setImageDrawableAndStart(R.drawable.ic_landscape_from_auto_rotate_animation)
            } else {
                isActivated = true
                setImageDrawableAndStart(R.drawable.ic_landscape_to_auto_rotate_animation)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        animator.removeAllUpdateListeners()
        animator.cancel()
    }

    private fun View.performRippleEffect() {
        isPressed = true
        postOnAnimationDelayed(50) {
            isPressed = false
        }
    }

    private fun ImageView.setImageDrawableAndStart(@DrawableRes resId: Int) {
        val drawable = ContextCompat.getDrawable(context, resId)
        setImageDrawable(drawable)
        if (drawable is Animatable) {
            drawable.start()
        }
    }


}