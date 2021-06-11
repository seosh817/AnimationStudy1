package io.github.slflfl12.customviewanimation.dynamic.spring

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.animation.doOnRepeat
import androidx.core.view.doOnLayout
import androidx.databinding.DataBindingUtil
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import androidx.dynamicanimation.animation.withSpringForceProperties
import androidx.fragment.app.Fragment
import io.github.slflfl12.customviewanimation.R
import io.github.slflfl12.customviewanimation.databinding.FragmentDynamicSpringBinding
import io.github.slflfl12.customviewanimation.interpolator.Interpolators

class SpringFragment : Fragment(), ViewAnimation {

    private var maxTranslationX: Float = 0f
    private var maxTranslationY: Float = 0f

    private val animator = ValueAnimator.ofFloat(0f, 1f).apply {
        repeatMode = ValueAnimator.REVERSE
        repeatCount = ValueAnimator.INFINITE
        duration = 1000L
        interpolator = Interpolators.LINEAR
    }

    private lateinit var binding: FragmentDynamicSpringBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_dynamic_spring, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val iconTransX = binding.icon.springAnimationOf(DynamicAnimation.TRANSLATION_X)
        val iconTransY = binding.icon.springAnimationOf(DynamicAnimation.TRANSLATION_Y)
        val iconTransZ = binding.icon.springAnimationOf(DynamicAnimation.TRANSLATION_Z)
        val iconScaleX = binding.icon.springAnimationOf(DynamicAnimation.SCALE_X)
        val iconScaleY = binding.icon.springAnimationOf(DynamicAnimation.SCALE_Y)
        val iconRotation = binding.icon.springAnimationOf(DynamicAnimation.ROTATION)
        val iconAlpha = binding.icon.springAnimationOf(DynamicAnimation.ALPHA)

        val bugSpring = binding.bug.springAnimationOf(DynamicAnimation.TRANSLATION_X)

        view.doOnLayout {
            maxTranslationX = view.measuredWidth - resources.getDimension(R.dimen.icon_size)
            maxTranslationY = resources.getDimension(R.dimen.icon_size) * -2

            var reverse = false
            animator.doOnRepeat {
                animateHeaderUi(reverse)
                if(reverse) {
                    iconTransX.animateToFinalPosition(0f)
                    iconTransY.animateToFinalPosition(0f)
                    iconRotation.animateToFinalPosition(0f)
                    iconScaleX.animateToFinalPosition(1f)
                    iconScaleY.animateToFinalPosition(1f)
                    iconAlpha.animateToFinalPosition(1f)
                    bugSpring.animateToFinalPosition(0f)
                } else {
                    iconTransX.animateToFinalPosition(maxTranslationX)
                    iconTransY.animateToFinalPosition(maxTranslationY)
                    iconRotation.animateToFinalPosition(360f)
                    iconScaleX.animateToFinalPosition(2f)
                    iconScaleY.animateToFinalPosition(.5f)
                    iconAlpha.animateToFinalPosition(.5f)
                    bugSpring.animateToFinalPosition(maxTranslationX)
                }
                reverse = !reverse
            }
            animator.start()
        }


    }

    override fun onDestroyView() {
        animator.removeAllListeners()
        animator.removeAllUpdateListeners()
        animator.cancel()
        super.onDestroyView()
    }

    private fun View.springAnimationOf(viewProperty: DynamicAnimation.ViewProperty): SpringAnimation {
        return SpringAnimation(this, viewProperty)
            .withSpringForceProperties {
                dampingRatio = SpringForce.DAMPING_RATIO_LOW_BOUNCY
                stiffness = SpringForce.STIFFNESS_LOW
            }
    }

    private fun animateHeaderUi(reverse: Boolean) {
        if(reverse) {
            binding.dim.animateOutForDim()
            binding.flashButton.animateOutForHeader()
            binding.startButton.animateOutForHeader()
            binding.toyButton.animateOutForHeader()
            binding.closeButton.animateOutForCloseButton()
        } else {
            binding.dim.animateInForDim()
            binding.flashButton.animateInForHeader()
            binding.startButton.animateInForHeader()
            binding.toyButton.animateInForHeader()
            binding.closeButton.animateInForCloseButton()
        }
    }

}