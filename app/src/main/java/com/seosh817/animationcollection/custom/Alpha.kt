package com.seosh817.animationcollection.custom

import android.animation.TimeInterpolator
import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.AnimationUtils
import androidx.constraintlayout.widget.ConstraintHelper
import androidx.constraintlayout.widget.ConstraintLayout
import com.seosh817.animationcollection.R
import com.seosh817.animationcollection.interpolator.Interpolators

class Alpha @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): ConstraintHelper(context, attrs, defStyleAttr), ProgressHelper{

    private var interpolator: TimeInterpolator = Interpolators.LINEAR
    private val reverse: Boolean

    private val views = ArrayList<View>()
    private var progress: Float = 0f


    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.Alpha, 0, 0)

        val interpolatorId = a.getResourceId(R.styleable.Alpha_interpolator, 0)
        if (interpolatorId > 0) {
            interpolator = AnimationUtils.loadInterpolator(context, interpolatorId)
        }
        reverse = a.getBoolean(R.styleable.Alpha_reverse, false)

        a.recycle()
    }


    override fun updatePreLayout(container: ConstraintLayout) {
        super.updatePreLayout(container)
        views.clear()
        repeat(mCount) {
            val view = container.getViewById(mIds[it])
            if (view != null) {
                views.add(view)
            }
        }
    }


    override fun setProgress(progress: Float) {
        this.progress = progress

        views.forEach {
            it.alpha = interpolator.getInterpolation(
                if (reverse) {
                    progress
                } else {
                    1f - progress
                }
            )
        }
    }

    override fun getProgress(): Float = progress

}