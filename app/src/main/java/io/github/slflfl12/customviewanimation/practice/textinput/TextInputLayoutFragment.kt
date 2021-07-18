package io.github.slflfl12.customviewanimation.practice.textinput

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.github.slflfl12.customviewanimation.R
import io.github.slflfl12.customviewanimation.databinding.FragmentTextInputLayoutBinding

class TextInputLayoutFragment : Fragment() {

    private lateinit var binding: FragmentTextInputLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTextInputLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        with(binding) {
            etMain.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {
                    if (s.toString().isEmpty()) {
                        tilMain.error = "공백은 허용하지 않습니다."
                    } else {
                        s.toString().toIntOrNull()?.let {
                            if (s != null) {
                                if (s.length > 4) {
                                    tilMain.error = "4글자가 초과되었습니다."
                                } else {
                                    tilMain.error = null
                                }
                            }
                        } ?: run {
                            tilMain.error = "숫자를 입력하세요."
                        }
                    }
                }
            })
        }
    }
}