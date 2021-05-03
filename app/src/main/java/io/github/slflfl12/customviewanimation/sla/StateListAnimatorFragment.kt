package io.github.slflfl12.customviewanimation.sla

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.TranslateAnimation
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import io.github.slflfl12.customviewanimation.R
import io.github.slflfl12.customviewanimation.databinding.FragmentSlaBinding

class StateListAnimatorFragment: Fragment() {

    private lateinit var binding: FragmentSlaBinding

    private val translation by lazy {
        resources.getDimension(R.dimen.animator_sla_translation)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sla, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.clSlaView.setOnClickListener {
            setExpand(it.isActivated.not())
        }
    }

    private fun setExpand(expand: Boolean) {
        val duration = 200L
        val absTransX = if (expand) translation else 0f
        binding.tvTitle.animate().setDuration(duration).scaleX(-absTransX)
        binding.tvDescription.animate().setDuration(duration).scaleX(-absTransX)
        binding.unfold.animate().setDuration(duration).scaleX(-absTransX)

        TransitionManager.beginDelayedTransition(binding.clContainer, AutoTransition().apply {
            this.duration = duration
        })
        binding.clSlaView.isActivated = expand
        binding.actionGroup.isVisible = expand
    }
}