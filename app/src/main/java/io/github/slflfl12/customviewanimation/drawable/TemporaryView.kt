package io.github.slflfl12.customviewanimation.drawable

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Checkable
import androidx.appcompat.widget.AppCompatImageView

class TemporaryView: AppCompatImageView, View.OnClickListener {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs, 0)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context, attrs, defStyleAttr
    ) {
        setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        Log.d("seunghwan", "clicked")
    }
}