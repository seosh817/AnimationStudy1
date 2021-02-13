package io.github.slflfl12.customviewanimation.drawable.loading

import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import io.github.slflfl12.customviewanimation.R
import io.github.slflfl12.customviewanimation.databinding.ViewStageBinding
import io.github.slflfl12.customviewanimation.interpolator.Interpolators


class StageView(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attrs, defStyle){

    private var progressAnimator: ValueAnimator? = null

    private val binding: ViewStageBinding = ViewStageBinding.inflate(LayoutInflater.from(context), this, false)


    init {
        View.inflate(context, R.layout.view_stage, null)
    }

    fun startStageAnimation(currentOffSet: Float) {
        resetStageEventAnimation()
        startAnimation(currentOffSet)
    }

    private fun resetStageEventAnimation() {
        binding.pbStage.setMutateImageResource(R.drawable.stage_progress)
        binding.progressThumb.run {
            scaleX = .3f
            scaleY = .3f
        }

    }

    private fun ImageView.setMutateImageResource(@DrawableRes resId: Int) {
        ContextCompat.getDrawable(context, resId)?.let {
            setImageDrawable(it.mutate()) // mutate -> 동일한 Constant 참조로 인한 drawable변경을 막음
        }
    }

    private fun startAnimation(offSet: Float) {
        progressAnimator = ValueAnimator.ofFloat(0f, offSet).apply {
            startDelay = 350
            duration = (1000 * offSet).toLong()
            interpolator = Interpolators.EASE_IN_CUBIC
            addUpdateListener {
                renderProgress(it.animatedFraction)
            }
            doOnEnd {
                renderEnd()
            }
            start()
        }
    }

    private fun renderProgress(fraction: Float) {
        binding.pbStage.setImageLevel((1000*fraction).toInt())
        binding.progressThumb.translationX = binding.pbStage.width * fraction
        when {
            fraction >= 1f -> binding.count3.startBounceAnimation()
            fraction >= .66f -> binding.count2.startBounceAnimation()
            fraction >= .33f -> binding.count1.startBounceAnimation()
        }
    }

    private fun renderEnd() {
        binding.progressThumb.animate()
            .setDuration(700)
            .setInterpolator(Interpolators.EASE_OUT_EXPO)
            .scaleX(1f)
            .scaleY(1f)
            .withEndAction {
                animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(330)
                    .start()
            }
    }

    private fun TextView.startBounceAnimation() {
        if(isSelected) return
        isSelected = true
        animate()
            .scaleX(1.2f)
            .scaleY(1.2f)
            .setDuration(70)
            .setInterpolator(Interpolators.EASE_OUT_EXPO)
            .withLayer()
            .withEndAction {
                animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(330)
                    .start()
            }
    }

    override fun onDetachedFromWindow() {
        stopStageEventAnimation()
        super.onDetachedFromWindow()
    }

    private fun stopStageEventAnimation() {
        binding.count1.animate()?.cancel()
        binding.count2.animate()?.cancel()
        binding.count3.animate()?.cancel()


        progressAnimator?.run {
            cancel()
            removeAllUpdateListeners()
            removeAllListeners()
        }
        progressAnimator = null
    }
}