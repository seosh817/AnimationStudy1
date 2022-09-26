package com.seosh817.animationcollection.view_property.recycler_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.SlideInUpAnimator
import com.seosh817.animationcollection.R
import com.seosh817.animationcollection.databinding.FragmentViewPropertyRecyclerViewBinding

class RecyclerViewFragment: Fragment() {

    private lateinit var binding: FragmentViewPropertyRecyclerViewBinding
    private val listAdapter : ViewPropertyRecyclerAdapter by lazy {
        ViewPropertyRecyclerAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_property_recycler_view, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.listView.itemAnimator = SlideInUpAnimator()
        binding.listView.adapter = listAdapter
        binding.fab.setOnClickListener {
            listAdapter.addItemToLast()
        }
    }
}