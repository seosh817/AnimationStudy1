package io.github.slflfl12.customviewanimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.animation.BounceInterpolator
import androidx.databinding.DataBindingUtil
import io.github.slflfl12.customviewanimation.databinding.ActivityMainBinding
import io.github.slflfl12.rotationlayout.RotationAnimation

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

/*        val rotationTextView = RotationAnimation.Builder().setSpeed(3000).setTurn(1).build()
        rotationTextView.startAnimation(rotation_text_view)*/

        binding.ivSimple.setOnClickListener {
            val animation = AnimationUtils.loadAnimation(this, R.anim.animate_translate)
            binding.ivSimple.startAnimation(animation)
        }


        binding.tvLeft.isSelected = true
        // px2dp 해주어야함
        binding.tvRight.setOnClickListener {
            binding.itemTab.animate()?.translationX(binding.itemTab.width.toFloat())
                ?.setInterpolator(BounceInterpolator())
                ?.setDuration(500)?.start()
            binding.tvRight.isSelected = true
            binding.tvLeft.isSelected = false
        }
        binding.tvLeft.setOnClickListener {
            binding.itemTab.animate()?.translationX(0f)
                ?.setInterpolator(BounceInterpolator())
                ?.setDuration(500)?.start()
            binding.tvRight.isSelected = false
            binding.tvLeft.isSelected = true
        }
    }
}