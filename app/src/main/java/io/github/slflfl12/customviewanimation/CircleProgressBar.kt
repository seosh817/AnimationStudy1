package io.github.slflfl12.customviewanimation

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import kotlin.properties.Delegates

class CircleProgressBar : View {

    private var progress = 0
        set(value) {
            field = value
            invalidate()
        }
    private var space = 0
    private var radius by Delegates.notNull<Float>()

    var measureWidth = 0
    var measureHeight = 0

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        getAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        getAttrs(attrs, defStyleAttr)
    }


    private fun getAttrs(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressBar)
        setTypedArray(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet, defStyleAttr: Int) {
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.CircleProgressBar, defStyleAttr, 0)
        setTypedArray(typedArray)
    }

    private fun setTypedArray(typedArray: TypedArray) {
        try {
            progress = typedArray.getInt(R.styleable.CircleProgressBar_progress, 0)
        } finally {
            typedArray.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
/*        var width = 0
        val height = getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)
        when(MeasureSpec.getMode(widthMeasureSpec)) {
            MeasureSpec.EXACTLY -> {
                width = getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
            }
        }



        setMeasuredDimension(width, height)*/


        /*       when(MeasureSpec.getMode(widthMeasureSpec)) {
                   MeasureSpec.AT_MOST -> {
                       Log.d("seunghwanWidth", "WIDTH AT_MOST ${MeasureSpec.getSize(widthMeasureSpec)}")
                   }
                   MeasureSpec.EXACTLY -> {
                       Log.d("seunghwanWidth", "WIDTH EXACTLY ${MeasureSpec.getSize(widthMeasureSpec)}")
                   }
                   MeasureSpec.UNSPECIFIED -> {
                       Log.d("seunghwanWidth", "UNSPECIFIED ${MeasureSpec.getSize(widthMeasureSpec)}")
                   }
               }

               when(MeasureSpec.getMode(heightMeasureSpec)) {
                   MeasureSpec.AT_MOST -> {
                       Log.d("seunghwanHeight", "WIDTH AT_MOST ${MeasureSpec.getSize(heightMeasureSpec)}")
                   }
                   MeasureSpec.EXACTLY -> {
                       Log.d("seunghwanHeight", "WIDTH EXACTLY ${MeasureSpec.getSize(heightMeasureSpec)}")
                   }
                   MeasureSpec.UNSPECIFIED -> {
                       Log.d("seunghwanHeight", "UNSPECIFIED ${MeasureSpec.getSize(heightMeasureSpec)}")
                   }
               }*/
        // space * (progress - 1) + radius * (progress * 2) == widthMeasureSpec
        Log.d("seunghwanWidth", "WIDTH${MeasureSpec.getSize(widthMeasureSpec)}")
        space = MeasureSpec.getSize(widthMeasureSpec) / (progress * 10)
        radius = (MeasureSpec.getSize(widthMeasureSpec) - (progress * space)) / (progress * 2) * 1f
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), (radius * 2).toInt())
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.let {
            it.drawColor(resources.getColor(android.R.color.transparent))

            val paint = Paint(Paint.ANTI_ALIAS_FLAG)
            paint.color = resources.getColor(R.color.black)

            for (num in 1..progress) {
                it.drawCircle(radius * ((num * 2) - 1) + (space * (num - 1)), radius, radius, paint)
            }


        }
    }

}