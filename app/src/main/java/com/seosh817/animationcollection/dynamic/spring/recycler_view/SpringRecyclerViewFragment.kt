package com.seosh817.animationcollection.dynamic.spring.recycler_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.SpringSlideInAnimator
import com.seosh817.animationcollection.R
import com.seosh817.animationcollection.databinding.FragmentDynamicSpringRecyclerViewBinding

class SpringRecyclerViewFragment: Fragment() {

    private lateinit var binding: FragmentDynamicSpringRecyclerViewBinding
    private val listAdapter: SpringRecyclerViewAdapter by lazy {
        SpringRecyclerViewAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dynamic_spring_recycler_view, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listView.itemAnimator = SpringSlideInAnimator()
        binding.listView.adapter = listAdapter
        binding.fab.setOnClickListener {
            listAdapter.shuffle()
        }
    }
}