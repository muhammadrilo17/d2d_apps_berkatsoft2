package com.rilodev.d2dapps.core.ui.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import com.rilodev.d2dapps.R
import com.rilodev.d2dapps.core.utils.Utils

class EditTextEmail: TextInputEditText {
    private lateinit var emailIcon: Drawable

    private var _isEmailRight = false
    val isEmailRight get() = _isEmailRight

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
        hint = context.getString(R.string.enter_your_email)
        emailIcon = ContextCompat.getDrawable(context, R.drawable.ic_email) as Drawable

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                _isEmailRight = if (s.toString().isEmpty()) false
                else {
                    if (!Utils.isEmailRight(s.toString())) {
                        showError(R.string.email_invalid)
                        false
                    } else true
                }
            }

        })
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        setButtonDrawable(startOfTheText = emailIcon)
        setBackgroundResource(R.drawable.bg_text_field)
        maxLines = 1
        textSize = 14f
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
        if (text.toString().isEmpty()) {
            showError(R.string.field_cannot_empty)
        }
    }
}