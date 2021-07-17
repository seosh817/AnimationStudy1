package io.github.slflfl12.customviewanimation.practice.textinput

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.slflfl12.customviewanimation.databinding.FragmentTextInputLayoutBinding

class TextInputLayoutFragment: Fragment() {

    private lateinit var binding: FragmentTextInputLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTextInputLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }


}