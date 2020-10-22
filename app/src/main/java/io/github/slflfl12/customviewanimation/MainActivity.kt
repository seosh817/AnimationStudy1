package io.github.slflfl12.customviewanimation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.github.slflfl12.rotationlayout.RotationAnimation
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rotationTextView = RotationAnimation.Builder().setSpeed(3000).setTurn(1).build()
        rotationTextView.startAnimation(rotation_text_view)
    }
}