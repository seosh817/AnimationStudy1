package com.seosh817.animationcollection.shared_elements

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.seosh817.animationcollection.R
import com.seosh817.animationcollection.databinding.FragmentSharedElementsDetailBinding

class SharedElementsDetailFragment: Fragment() {
    private lateinit var binding: FragmentSharedElementsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition =
            TransitionInflater.from(context).inflateTransition(R.transition.move)
        returnTransition =
            TransitionInflater.from(context).inflateTransition(R.transition.move)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(R.transition.move_shared_element)
        sharedElementReturnTransition =
            TransitionInflater.from(context).inflateTransition(R.transition.move_shared_element)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shared_elements_detail, container, false)
        return binding.root
    }
}