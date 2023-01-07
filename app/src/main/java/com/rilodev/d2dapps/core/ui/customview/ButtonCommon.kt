package com.rilodev.d2dapps.core.ui.customview

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.rilodev.d2dapps.R

class ButtonCommon: AppCompatButton, View.OnTouchListener {
    private var textCustomColor = 0

    constructor(context: Context) : super(context) { init() }
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) { init() }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) { init() }

    private fun init() {
        textCustomColor = ContextCompat.getColor(context, R.color.white)
        setBackgroundResource(R.drawable.bg_button)

        setOnTouchListener(this)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        text = if(text.isNullOrEmpty()) context.getString(R.string.select) else text
        setTextColor(textCustomColor)
    }

    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        when(event.action) {
            MotionEvent.ACTION_UP -> {
                textCustomColor = ContextCompat.getColor(context, R.color.white)
                setTextColor(textCustomColor)
                setBackgroundResource(R.drawable.bg_button)
            }
            MotionEvent.ACTION_DOWN -> {
                textCustomColor = ContextCompat.getColor(context, R.color.black)
                setBackgroundResource(R.drawable.bg_button_selected)
                setTextColor(textCustomColor)
            }
        }
        return false
    }
}