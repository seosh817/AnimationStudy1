package com.seosh817.animationcollection.drawable.research

import android.os.Bundle
import android.view.animation.BounceInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.seosh817.animationcollection.R
import com.seosh817.animationcollection.databinding.ActivityResearchBinding

class ResearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResearchBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_research
        )

        /*       val rotationTextView = RotationAnimation.Builder().setSpeed(3000).setTurn(1).build()
               rotationTextView.startAnimation(binding.rotationTextView)

               binding.ivSimple.setOnClickListener {
                   val animation = AnimationUtils.loadAnimation(this, R.anim.animate_translate)
                   binding.ivSimple.startAnimation(animation)
               }*/


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