package io.github.slflfl12.customviewanimation.dynamic.fling

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.databinding.DataBindingUtil
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.FlingAnimation
import androidx.fragment.app.Fragment
import io.github.slflfl12.customviewanimation.R
import io.github.slflfl12.customviewanimation.databinding.FragmentDynamicFlingBinding
import io.github.slflfl12.customviewanimation.drawable.CheckedImageView

class FlingFragment: Fragment() {

    private lateinit var binding: FragmentDynamicFlingBinding
    private var maxTranslationX: Float = 0f


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dynamic_fling, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.doOnLayout {
            maxTranslationX = view.measuredWidth - resources.getDimension(R.dimen.icon_size)
        }
        binding.ivIcon.setOnClickListener { binding.ivIcon.toggle() }
        binding.ivIcon.setOnCheckedChangeListener(object: CheckedImageView.OnCheckedChangeListener {
            override fun onCheckedChanged(checkedImageView: CheckedImageView, isChecked: Boolean) {
                if(isChecked) {
                    binding.ivIcon.flingAnimationOf(DynamicAnimation.TRANSLATION_X)
                        .setStartVelocity(5000f)
                        .start()
                } else {
                    binding.ivIcon.flingAnimationOf(DynamicAnimation.TRANSLATION_X)
                        .setStartVelocity(-5000f)
                        .start()
                }
            }
        })
    }

    private fun View.flingAnimationOf(viewProperty: DynamicAnimation.ViewProperty): FlingAnimation {
        return FlingAnimation(this, viewProperty)
            .setFriction(1f)
            .setMinValue(0f)
            .setMaxValue(maxTranslationX)
    }

}