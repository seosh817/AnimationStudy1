package io.github.slflfl12.customviewanimation.viewanim

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import io.github.slflfl12.customviewanimation.R
import io.github.slflfl12.customviewanimation.databinding.FragmentViewanimBinding

class ViewAnimationFragment: Fragment() {

    private lateinit var binding: FragmentViewanimBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_viewanim, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}