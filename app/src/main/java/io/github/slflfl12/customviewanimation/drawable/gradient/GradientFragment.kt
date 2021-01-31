package io.github.slflfl12.customviewanimation.drawable.gradient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import io.github.slflfl12.customviewanimation.R
import io.github.slflfl12.customviewanimation.databinding.FragmentGradientBinding

class GradientFragment: Fragment() {

    private lateinit var binding: FragmentGradientBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gradient, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.gradientCircle.clipToOval(true)
        binding.gradientRoundRect.clipToRoundRect(resources.getDimension(R.dimen.gradient_clip_radius))
    }
}