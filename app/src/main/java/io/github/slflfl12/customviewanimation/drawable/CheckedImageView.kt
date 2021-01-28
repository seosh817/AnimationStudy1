package io.github.slflfl12.customviewanimation.drawable

import android.content.Context
import android.util.AttributeSet
import android.widget.Checkable
import androidx.appcompat.widget.AppCompatImageView
import io.github.slflfl12.customviewanimation.R

class CheckedImageView : AppCompatImageView, Checkable {

    private var mChecked: Boolean = false

    private var onCheckedChangeListener: OnCheckedChangeListener? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.CheckedImageView, defStyleAttr, 0)

        val checked = typedArray.getBoolean(R.styleable.CheckedImageView_checked, false)


        typedArray.recycle()

    }

    override fun isChecked(): Boolean = mChecked


    override fun toggle() {
        isChecked = !mChecked
    }

    override fun setChecked(checked: Boolean) {
        if(mChecked != checked) {
            mChecked = checked
        }
    }

    interface OnCheckedChangeListener {

    }
}