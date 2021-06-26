package io.github.slflfl12.customviewanimation.transition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import io.github.slflfl12.customviewanimation.R
import io.github.slflfl12.customviewanimation.databinding.FragmentTransitionBinding

class TransitionFragment: Fragment() {

    private lateinit var binding: FragmentTransitionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_transition, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fab.setOnClickListener {
            val transition = AutoTransition()
            TransitionManager.beginDelayedTransition(binding.container, transition)
            binding.outerBackground.switchVisibility()
            binding.innerBackground.switchVisibility()
        }
    }

    private fun View.switchVisibility() {
        visibility = if (this.isVisible) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }
}