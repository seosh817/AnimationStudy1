package com.seosh817.animationcollection.animator

import android.animation.AnimatorInflater
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.animation.doOnRepeat
import androidx.core.view.doOnLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.seosh817.animationcollection.R
import com.seosh817.animationcollection.databinding.FragmentAnimatorBinding
import com.seosh817.animationcollection.interpolator.Interpolators
import com.seosh817.animationcollection.util.lerp

class AnimatorFragment : Fragment() {

    private lateinit var binding: FragmentAnimatorBinding

    private var maxTranslationX: Float = 0f
    private var maxTranslationY: Float = 0f

    private val animator = ValueAnimator.ofFloat(0f, 2f, 1f).apply {
        repeatMode = ValueAnimator.REVERSE
        repeatCount = ValueAnimator.INFINITE
        duration = 1_000L
        interpolator = Interpolators.ACCELERATE_DECELERATE
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_animator, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val flipOut = AnimatorInflater.loadAnimator(view.context, R.animator.flip_out)
        val flipIn = AnimatorInflater.loadAnimator(view.context, R.animator.flip_in)
        binding.facingButton.setOnClickListener {
            if (it.isSelected) {
                it.isSelected = false
                flipOut.setTarget(binding.facingBackButton)
                flipIn.setTarget(binding.facingFrontButton)
                flipOut.start()
                flipIn.start()
            } else {
                it.isSelected = true
                flipOut.setTarget(binding.facingBackButton)
                flipIn.setTarget(binding.facingFrontButton)
                flipOut.start()
                flipIn.start()
            }
        }

        view.doOnLayout {
            maxTranslationX = view.measuredWidth - resources.getDimension(R.dimen.icon_size)
            maxTranslationY = resources.getDimension(R.dimen.icon_size).unaryMinus()

            animator.doOnRepeat {
                binding.facingButton.performClick()
            }
            animator.addUpdateListener {
                updateUi(it.animatedFraction, it.animatedValue as Float)
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


    private fun updateUi(fraction: Float, value: Float) {
        binding.icon.run {
            rotation = lerp(0f, 360f, fraction)
            alpha = lerp(1f, .5f, fraction)
            scaleX = lerp(1f, 2f, fraction)
            scaleY = lerp(1f, .5f, fraction)
            translationX = lerp(0f, maxTranslationX, fraction)
            translationY = lerp(0f, maxTranslationY, value)
        }
        binding.bug.run {
            translationX = lerp(0f, maxTranslationX, fraction)
            translationY = lerp(0f, maxTranslationY, fraction)
        }
    }
}