package com.rilodev.d2dapps.core.ui.customview

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.View
import com.google.android.material.textfield.TextInputEditText
import com.rilodev.d2dapps.R

class EditTextCommon : TextInputEditText {
    val value get() = text.toString().trim()

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        setBackgroundResource(R.drawable.bg_text_field)
        maxLines = 1
        textSize = 14f
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }

    private fun showError(value: Int) {
        error = context.getString(value)
    }

    fun triggerEmptyField() {
        if (text.toString().isEmpty()) {
            showError(R.string.field_cannot_empty)
        }
    }
}