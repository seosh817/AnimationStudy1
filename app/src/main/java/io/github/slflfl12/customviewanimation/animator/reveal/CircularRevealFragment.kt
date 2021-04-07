package io.github.slflfl12.customviewanimation.animator.reveal

import android.animation.Animator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.Animation
import androidx.core.animation.doOnEnd
import androidx.core.view.doOnLayout
import androidx.fragment.app.Fragment
import com.google.android.material.circularreveal.CircularRevealCompat
import com.google.android.material.circularreveal.CircularRevealLinearLayout
import com.google.android.material.circularreveal.CircularRevealWidget
import io.github.slflfl12.customviewanimation.databinding.FragmentAnimatorRevealBinding
import io.github.slflfl12.customviewanimation.interpolator.Interpolators
import kotlin.math.hypot

class CircularRevealFragment: Fragment() {

    private lateinit var binding: FragmentAnimatorRevealBinding
    private var radius = 0f


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnimatorRevealBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.doOnLayout {
            radius = it.diagonalLength()
        }
        binding.unfold.setOnClickListener {
            if(it.isSelected) {
                it.isSelected = false
                binding.cardRevealView.foldMenu()
            } else {
                it.isSelected = true
                binding.cardRevealView.unFoldMenu()
            }
        }
        binding.fab.setOnClickListener {
            if(it.isSelected) {
                it.isSelected = false
                binding.revealView.hideContents()
            } else {
                it.isSelected = true
                binding.revealView.showContents()
            }
        }

        binding.transformFab.setOnClickListener {
            binding.transformFab.isExpanded = true
        }

        binding.scrim.setOnClickListener {
            binding.transformFab.isExpanded = false
        }
    }




    private fun View.diagonalLength(): Float {
        return hypot(width.toFloat(), height.toFloat()) // root(x^2 + y^2)
    }

    private inline fun CircularRevealWidget.createCircularRevealCompatOf(
            target: View,
            startRadius: Float,
            endRadius: Float,
            block: Animator.() -> Unit
    ): Animator {
        return CircularRevealCompat.createCircularReveal(
            this,
            target.centerX().toFloat(),
            target.centerY().toFloat(),
            startRadius, endRadius
        ).apply(block)
    }

    private fun View.hideContents() {
        createCircularRevealOf(binding.fab, radius, 0f) {
            duration = 300
            interpolator = Interpolators.ACCELERATE
            doOnEnd {
                visibility = View.GONE
            }
        }.start()
    }

    private fun View.showContents() {
        visibility = View.VISIBLE
        createCircularRevealOf(binding.fab, 0f, radius) {
            duration = 300
            interpolator = Interpolators.DECELERATE
        }.start()
    }



    private fun View.centerX(): Int {
        return (right + left) / 2
    }

    private fun View.centerY(): Int {
        return (bottom + top) / 2
    }

    private fun CircularRevealLinearLayout.foldMenu() {
        createCircularRevealCompatOf(
            binding.unfold, radius, 0f
        ) {
            duration = 500
            doOnEnd {
                visibility = View.GONE
            }
        }.start()
    }

    private fun CircularRevealLinearLayout.unFoldMenu() {
        visibility = View.VISIBLE
        createCircularRevealCompatOf(binding.unfold, 0f, radius) {
            duration = 500
        }.start()
    }

    private inline fun View.createCircularRevealOf(
        target: View,
        startRadius: Float,
        endRadius: Float,
        block: Animator.() -> Unit
    ): Animator {
        return ViewAnimationUtils.createCircularReveal(
            this,
            target.centerX(),
            target.centerY(),
            startRadius,
            endRadius
        ).apply(block)
    }




}
