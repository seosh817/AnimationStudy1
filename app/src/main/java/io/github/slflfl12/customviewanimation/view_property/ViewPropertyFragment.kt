package io.github.slflfl12.customviewanimation.view_property

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import io.github.slflfl12.customviewanimation.R
import io.github.slflfl12.customviewanimation.databinding.FragmentViewPropertyBinding

class ViewPropertyFragment: Fragment() {

    private lateinit var binding: FragmentViewPropertyBinding

    private var maxTranslationX: Float = 0f
    private var maxTranslationY: Float = 0f


    private val uiHandler = Handler(Handler.Callback {
        updateUi()
        true
    })

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_view_property, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.doOnLayout {

        }
    }

    private fun updateUi() {
    }


    companion object {
        private const val UPDATE_UI = 1
    }
}