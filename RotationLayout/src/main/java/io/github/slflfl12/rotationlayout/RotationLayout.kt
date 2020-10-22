package io.github.slflfl12.rotationlayout

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.annotation.StyleRes


// https://developer.android.com/reference/android/view/animation/Animation
class RotationLayout : FrameLayout {

    private var isStarted: Boolean = false
    private var autoStart: Boolean = true
    private lateinit var rotationAnimation: RotationAnimation

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

        //defStyleAttr -> 해당 뷰 클래스의 기본 속성, 기본 스타일을 받아옴 이를 통해 버튼 스타일은 모든 기본 View의 속성을 바꿀 수 있음
        //defStyleRes와의 차이는 R.attr.XXX 이냐 or R.style.XXX이냐의 차이
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    ) {
        //매개변수 defStyleRes는 View의 defStyleAttr가 0이거나 테마에서 찾을 수 없는 경우에만 기본값을 제공하는 Style 리소스 ID다. 기본값을 찾지 않으려면 0 으로 지정한다.
    }

    private fun getAttrs(attrs: AttributeSet) {
        //attr 속성값들을 typedArray 형태로 가져옴
        //xml 에서 작성한 것들이 attributeSet이다.
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RotationLayout)
        setTypedArray(typedArray)
    }

    private fun getAttrs(attrs: AttributeSet, defStyle: Int) {
        //defStyle이 있을 경우 defStyle을 우선으로 가져옴
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RotationLayout, defStyle, 0)
        setTypedArray(typedArray)
    }

    private fun setTypedArray(typedArray: TypedArray) {
        try {
            val builder = RotationAnimation.Builder()
                .setDegreeX(
                    typedArray.getInt(R.styleable.RotationLayout_degreeX, 0)
                )
                .setDegreeZ(typedArray.getInt(R.styleable.RotationLayout_degreeZ, 0))
                .setSpeed(typedArray.getInt(R.styleable.RotationLayout_speed, 2500))
                .setLoop(typedArray.getInt(R.styleable.RotationLayout_loop, 0))
                .setTurn(typedArray.getInt(R.styleable.RotationLayout_turn, 1))

            val target = typedArray.getInt(R.styleable.RotationLayout_target, 0)
            var medalTarget = RotationTarget.CHILDREN
            if (target == 1) medalTarget = RotationTarget.PARENT
            builder.setTarget(medalTarget)

            val direction = typedArray.getInt(R.styleable.RotationLayout_direction, 0)
            var rotationDirection = RotationDirection.LEFT
            if (direction == 1) rotationDirection = RotationDirection.RIGHT
            builder.setDirection(rotationDirection)

            this.rotationAnimation = builder.build()
            this.autoStart = typedArray.getBoolean(R.styleable.RotationLayout_autoStart, autoStart)
        } finally {
            typedArray.recycle()
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        if (this.autoStart) {
            startAnimation()
        }
    }

    fun startAnimation() {
        if (!isStarted()) {
            this.rotationAnimation.startAnimation(this)
            this.isStarted = true
        }
    }

    fun isStarted(): Boolean {
        return this.isStarted
    }
}