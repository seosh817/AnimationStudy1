package com.seosh817.animationcollection.viewanim

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.*
import androidx.core.view.doOnLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.seosh817.animationcollection.R
import com.seosh817.animationcollection.databinding.FragmentViewanimBinding

class ViewAnimationFragment: Fragment() {

    private lateinit var binding: FragmentViewanimBinding
    private var maxTranslationX: Float = 0f
    private var maxTranslationY: Float = 0f

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_viewanim, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.doOnLayout {
            maxTranslationX = view.measuredWidth - resources.getDimension(R.dimen.icon_size)
            maxTranslationY = resources.getDimension(R.dimen.icon_size) * -2
            Log.d("seunghwan", maxTranslationY.toString())
            startAnimation()
        }
    }

    override fun onDestroyView() {
        binding.icon.clearAnimation()
        super.onDestroyView()
    }

    private fun startAnimation() {
        val animation = AnimationSet(true).apply {
            duration = 1000
            setInterpolator(context, android.R.interpolator.accelerate_decelerate)
            addAnimation(
                ScaleAnimation(1f, 2f, 1f, .5f, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f)
                    .withRepeat()
            )
            addAnimation(
                AlphaAnimation(1f, .5f)
                    .withRepeat()
            )
            addAnimation(
                RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, .5f, Animation.RELATIVE_TO_SELF, .5f)
                    .withRepeat()
            )
            addAnimation(
                TranslateAnimation(0f, maxTranslationX, 0f, maxTranslationY)
                    .withRepeat()
            )
        }
        binding.icon.startAnimation(animation)
    }
    private fun Animation.withRepeat(): Animation {
        repeatCount = Animation.INFINITE
        repeatMode = Animation.REVERSE
        return this
    }
}