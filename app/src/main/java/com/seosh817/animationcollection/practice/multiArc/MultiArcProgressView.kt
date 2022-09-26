package com.seosh817.animationcollection.practice.multiArc

import android.animation.ValueAnimator
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.annotation.FloatRange
import androidx.core.content.ContextCompat
import com.seosh817.animationcollection.R

class MultiArcProgressView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attrs, defStyle) {


    private var progress: Float = 0.5f
    private var progressColor: Int = ContextCompat.getColor(context, R.color.cyan)
    private var emptyColor: Int = ContextCompat.getColor(context, R.color.grey_navy)
    private var progressWidth: Float = 20f
    private var startAngle: Int = 135
    private var endAngle: Int = 270
    private var barCount: Int = 1
    private var barSpace: Float = 0f
    private var radius: Int = 20

    val animator = ValueAnimator().apply {

    }

    private var progressPaint = Paint().apply {
        color = progressColor
        strokeWidth = progressWidth
        strokeCap = Paint.Cap.ROUND
        style = Paint.Style.STROKE
        isAntiAlias = true
    }

    private var emptyPaint = Paint().apply {
        color = emptyColor
        strokeWidth = progressWidth
        strokeCap = Paint.Cap.ROUND
        style = Paint.Style.STROKE
        isAntiAlias = true
    }

    var rectF = RectF()

    init {
        if (attrs != null) {
            val typedArray =
                context.obtainStyledAttributes(attrs, R.styleable.MultiArcProgressView, defStyle, 0)
            setTypedArray(typedArray)
            typedArray.recycle()
        }
    }

    private fun setTypedArray(typedArray: TypedArray) {
        progress = typedArray.getFloat(R.styleable.MultiArcProgressView_percentage, progress)
        progressColor =
            typedArray.getColor(R.styleable.MultiArcProgressView_progressColor, progressColor)
        progressWidth =
            typedArray.getDimension(R.styleable.MultiArcProgressView_progressWidth, progressWidth)
        startAngle = typedArray.getInteger(R.styleable.MultiArcProgressView_startArc, startAngle)
        endAngle = typedArray.getInteger(R.styleable.MultiArcProgressView_endArc, endAngle)
        barCount = typedArray.getInteger(R.styleable.MultiArcProgressView_barCount, barCount)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension((radius * 2 + progressWidth).toInt(), radius * 2 + progressWidth.toInt())
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.let {
            it.drawColor(ContextCompat.getColor(context, android.R.color.transparent))

//            val rectLeft = left.toFloat() + paddingLeft
//            val rectRight = right.toFloat() - paddingRight
//            val rectTop = height / 2f - rectRight / 2f + paddingTop
//            val rectBottom = height / 2f + rectRight / 2f - paddingRight
//            val rectF = RectF(rectLeft, rectTop, rectRight, rectBottom)



//            val rectLeft = left + paddingLeft
//            val rectRight = measuredWidth / 2f
//            val rectTop = 0f
//            val rectBottom = measuredHeight / 2f
//
//            val rectF = RectF(
//                rectLeft.toFloat(),
//                rectTop.toFloat(),
//                rectRight.toFloat(),
//                rectBottom.toFloat()
//            )
            val rectLeft = paddingLeft.toFloat()
            val rectRight = right.toFloat() - paddingRight + progressWidth / 2
            val rectTop = measuredHeight / 2f - rectRight / 2f + paddingTop + progressWidth / 2
            val rectBottom = measuredHeight / 2f + rectRight / 2f - paddingRight + progressWidth / 2
            val rectF = RectF(rectLeft, rectTop, rectRight, rectBottom)

            it.drawArc(rectF, 135f, 270f, false, emptyPaint)
//            it.drawArc(rectF, 0f, 180f, true, progressPaint)
        }


    }


    private fun setProgress(@FloatRange(from = 0.0, to = 1.0) progress: Float) {
        this.progress = progress
    }

}