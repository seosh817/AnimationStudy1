package io.github.slflfl12.customviewanimation.view_property

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import io.github.slflfl12.customviewanimation.R
import io.github.slflfl12.customviewanimation.databinding.FragmentViewPropertyBinding
import io.github.slflfl12.customviewanimation.interpolator.Interpolators

class ViewPropertyFragment: Fragment(), ViewAnimation {

    private lateinit var binding: FragmentViewPropertyBinding

    private var maxTranslationX: Float = 0f
    private var maxTranslationY: Float = 0f


    private val uiHandler = Handler(Handler.Callback {
        updateUi()
        true
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_property, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.doOnLayout {
            maxTranslationX = view.measuredWidth - resources.getDimension(R.dimen.icon_size)
            maxTranslationY = resources.getDimension(R.dimen.icon_size) * -2
            uiHandler.sendEmptyMessage(UPDATE_UI)
        }
    }

    private fun updateUi() {
        binding.icon.run {
            animate().cancel()
            val targetTransX: Float
            val targetTransY: Float
            val targetRotation: Float
            val targetScaleX: Float
            val targetScaleY: Float
            val targetAlpha: Float
            if(binding.icon.isChecked) {
                targetTransX = 0f
                targetTransY = 0f
                targetRotation = 0f
                targetScaleX = 1f
                targetScaleY = 1f
                targetAlpha = 1f
            } else {
                targetTransX = maxTranslationX
                targetTransY = maxTranslationY
                targetRotation = 360f
                targetScaleX = 2f
                targetScaleY = .5f
                targetAlpha = .5f
            }
            animate()
                .setDuration(1000)
                .setInterpolator(Interpolators.ACCELERATE_DECELERATE)
                .rotation(targetRotation)
                .scaleX(targetScaleX)
                .scaleY(targetScaleY)
                .alpha(targetAlpha)
                .translationX(targetTransX)
                .translationY(targetTransY)
                .withEndAction {
                    isChecked = !isChecked
                    uiHandler.sendEmptyMessage(UPDATE_UI)
                }
            if(binding.icon.isChecked) {
                binding.dim.animateOutForDim()
                binding.btnFlash.animateOutForHeader()
                binding.starButton.animateOutForHeader()
                binding.btnToys.animateOutForHeader()
                binding.btnClose.animateOutForCloseButton()
            } else {
                binding.dim.animateInForDim()
                binding.btnFlash.animateInForHeader(0)
                binding.starButton.animateInForHeader(70)
                binding.btnToys.animateInForHeader(140)
                binding.btnClose.animateInForCloseButton()
            }
        }
    }

    override fun onDestroyView() {
        uiHandler.removeMessages(UPDATE_UI)
        super.onDestroyView()
    }


    companion object {
        private const val UPDATE_UI = 1
    }
}