package com.seosh817.rotationlayout

import android.graphics.Camera
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.Transformation

fun RotationAnimation(block: RotationAnimation.Builder.() -> Unit): RotationAnimation =
    RotationAnimation.Builder().apply(block).build()

//interpolator 애니메이션이 일어나는 동안의 횟수 속도등을 조절
//fillAfter
//duration 지속시간, startOffset 시작시간,
//pivotX, pivotY 좌표의 중심점
class RotationAnimation(private val builder: Builder) : Animation() {

    private val degreeX: Float
    private var degreeY: Float
    private val degreeZ: Float

    private var width = 0
    private var height = 0

    init {
        this.degreeX = builder.degreeX.toFloat()
        this.degreeY = (360 * builder.turn).toFloat()
        this.degreeZ = builder.degreeZ.toFloat()

        if (builder.direction == RotationDirection.LEFT) {
            this.degreeY = -this.degreeY
        }

        this.duration = builder.speed.toLong()

        if (builder.loop == 0) {
            this.repeatCount = INFINITE
        } else {
            this.repeatCount = builder.loop - 1
        }
    }

    override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
        super.initialize(width, height, parentWidth, parentHeight)
        // 변수명 그대로 width, height은 뷰의 width, height
        // parentWidth, parentHeight은 부모 뷰의 width, height
        this.width = width / 2
        this.height = height / 2
    }

    override fun applyTransformation(interpolatedTime: Float, transformation: Transformation) {
        super.applyTransformation(interpolatedTime, transformation)
        val xDegrees = degreeX * interpolatedTime
        val yDegrees = degreeY * interpolatedTime
        val zDegrees = degreeZ * interpolatedTime
        val matrix = transformation.matrix
        val camera = Camera()
        camera.apply {
            save()
            rotateX(xDegrees)
            rotateY(yDegrees)
            rotateZ(zDegrees)
            getMatrix(matrix)
            restore()
        }

        matrix.preTranslate((-this.width).toFloat(), (-this.height).toFloat())
        matrix.postTranslate(this.width.toFloat(), this.height.toFloat())
    }

    fun startAnimation(view: View) {
        if(view is ViewGroup && builder.target == RotationTarget.CHILDREN) {
            for(i in 0 until view.childCount) {
                view.getChildAt(i).startAnimation(builder.build())
            }
        } else {
            view.startAnimation(builder.build())
        }
    }

    class Builder {

        // delete getter/setter
        @JvmField
        var direction: RotationDirection = RotationDirection.RIGHT

        @JvmField
        var degreeX = 0

        @JvmField
        var degreeZ = 0

        @JvmField
        var turn = 1

        @JvmField
        var speed = 2500

        @JvmField
        var loop = 0

        @JvmField
        var target: RotationTarget = RotationTarget.CHILDREN

        fun setDirection(rotationDirection: RotationDirection): Builder =
            apply { this.direction = rotationDirection }

        fun setDegreeX(degreeX: Int): Builder = apply { this.degreeX = degreeX }
        fun setDegreeZ(degreeZ: Int): Builder = apply { this.degreeZ = degreeZ }
        fun setTurn(turn: Int): Builder = apply { this.turn = turn }
        fun setLoop(loop: Int): Builder = apply { this.loop = loop }
        fun setSpeed(speed: Int): Builder = apply { this.speed = speed }
        fun setTarget(medalTarget: RotationTarget): Builder = apply { this.target = medalTarget}

        fun build(): RotationAnimation {
            return RotationAnimation(this)
        }

    }
}

