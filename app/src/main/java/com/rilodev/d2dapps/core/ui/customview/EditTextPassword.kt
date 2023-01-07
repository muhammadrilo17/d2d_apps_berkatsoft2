package com.rilodev.d2dapps.core.ui.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.rilodev.d2dapps.R


class EditTextPassword : TextInputEditText {
    private lateinit var visibilityIcon: Drawable
    private lateinit var keyIcon: Drawable

    private var _isPasswordRight = false
    val isPasswordRight get() = _isPasswordRight

    val value get() = text.toString().trim()

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        visibilityIcon =
            ContextCompat.getDrawable(context, R.drawable.ic_visibility_off) as Drawable
        keyIcon = ContextCompat.getDrawable(context, R.drawable.ic_key) as Drawable

        transformationMethod = PasswordTransformationMethod.getInstance()

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                _isPasswordRight = if(s.toString().isEmpty()) false
                else {
                    if (s.toString().length < 6) {
                        showError(R.string.minimum_pass_6_char)
                        false
                    } else true
                }
            }

        })
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        setButtonDrawable(startOfTheText = keyIcon)
        setBackgroundResource(R.drawable.bg_text_field)
        hint = if(hint.isNullOrEmpty()) context.getString(R.string.enter_your_password) else hint.toString()

        textSize = 14f
        maxLines = 1
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }

    private fun showError(value: Int) {
        error = context.getString(value)
    }

    private fun setButtonDrawable(
        startOfTheText: Drawable? = null,
        topOfTheText: Drawable? = null,
        endOfTheText: Drawable? = null,
        bottomOfTheText: Drawable? = null
    ) {
        setCompoundDrawablesWithIntrinsicBounds(
            startOfTheText,
            topOfTheText,
            endOfTheText,
            bottomOfTheText
        )
    }

    fun triggerEmptyField() {
        if(text.toString().isEmpty()) showError(R.string.field_cannot_empty)
    }
}