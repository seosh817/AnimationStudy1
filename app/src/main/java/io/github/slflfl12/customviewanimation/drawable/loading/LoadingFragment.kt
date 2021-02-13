package io.github.slflfl12.customviewanimation.drawable.loading

import android.animation.ValueAnimator
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import io.github.slflfl12.customviewanimation.R
import io.github.slflfl12.customviewanimation.databinding.FragmentLoadingBinding
import io.github.slflfl12.customviewanimation.interpolator.Interpolators
import io.github.slflfl12.customviewanimation.util.lerp

class LoadingFragment: Fragment() {

    private lateinit var binding:FragmentLoadingBinding

    private val animator = ValueAnimator.ofFloat(0f, 1f).apply {
        repeatMode = ValueAnimator.REVERSE
        repeatCount = ValueAnimator.INFINITE
        duration = 1000L
        interpolator = Interpolators.ACCELERATE
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_loading, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        animator.addUpdateListener {
            binding.pbLoadingHorizontal.setProgress(it.animatedFraction)
            binding.pbLoadingHorizontalCustom.setProgress(it.animatedFraction)

        }
    }

    private fun ProgressBar.setProgress(fraction: Float) {
        progress = if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            lerp(min, max, fraction).toInt()
        } else {
            lerp(0, max, fraction).toInt()
        }
    }

    override fun onDestroyView() {
        animator.cancel()
        animator.removeAllUpdateListeners()
        animator.removeAllListeners()
        super.onDestroyView()
    }


}