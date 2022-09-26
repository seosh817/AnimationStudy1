package com.seosh817.animationcollection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.seosh817.animationcollection.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {

    private val homeListAdapter: HomeListAdapter by lazy {
        HomeListAdapter().apply {
            onItemClickListener = object: HomeListAdapter.OnItemClickListener {
                override fun onItemClick(navItem: NavItem) {
                    findNavController().navigate(navItem.direction)
                }
            }
        }
    }

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvHome.adapter = homeListAdapter
    }


}