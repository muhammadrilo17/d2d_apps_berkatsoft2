package com.rilodev.d2dapps.core.utils

import android.app.Activity
import android.content.Intent
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.text.method.TransformationMethod
import android.util.Patterns
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityOptionsCompat
import com.rilodev.d2dapps.R
import com.rilodev.d2dapps.core.utils.Constants.DATETIME_FORMATTER_DD_MM_YYYY
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

object Utils {
    fun Activity.movePageAndRemoveTask(destination: Class<*>?) {
        if (destination != null) {
            val intent = Intent(this, destination)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        }
        finishAndRemoveTask()
    }

    fun isEmailRight(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun Activity.movePage(destination: Class<*>, optionCompat: ActivityOptionsCompat? = null) {
        val intent = Intent(this, destination)

        if (optionCompat != null) {
            startActivity(intent, optionCompat.toBundle())
        } else startActivity(intent)
    }


    fun ComponentActivity.clickOnBackPressed(action: (() -> Unit)) {
        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                action()
            }
        })
    }

    fun setShowHidePassword(
        editText: EditText,
        isPasswordHide: Boolean,
        button: ImageView
    ): Boolean {
        editText.transformationMethod = showHidePassword(isPasswordHide)
        button.setImageResource(
            changeShowHidePasswordIcon(isPasswordHide)
        )
        editText.setSelection(editText.length())
        return !isPasswordHide
    }

    private fun showHidePassword(isPasswordHide: Boolean): TransformationMethod {
        return if (isPasswordHide) {
            HideReturnsTransformationMethod.getInstance()
        } else {
            PasswordTransformationMethod.getInstance()
        }
    }

    private fun changeShowHidePasswordIcon(isPasswordHide: Boolean): Int {
        return if (isPasswordHide) {
            R.drawable.ic_visibility
        } else {
            R.drawable.ic_visibility_off
        }
    }

    fun getCurrentDateTime(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern(DATETIME_FORMATTER_DD_MM_YYYY)

        return current.format(formatter)
    }


    fun dateFormatter(dateTime: String, format: String): String {
        val dateResult = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(format))
        val day = if (dateResult.dayOfMonth < 10) "0${dateResult.dayOfMonth}" else dateResult.dayOfMonth
        val month = if (dateResult.monthValue < 10) "0${dateResult.monthValue}" else dateResult.monthValue

        return "$day-$month-${dateResult.year}"
    }

    fun timeFormatter(dateTime: String, format: String): String {
        val dateResult = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(format))
        val hour = if (dateResult.hour < 10) "0${dateResult.hour}" else dateResult.hour
        val minute = if (dateResult.minute < 10) "0${dateResult.minute}" else dateResult.minute

        return "${hour}:${minute}"
    }

    fun perfectDate(): String {
        val data = LocalDateTime.parse(
            getCurrentDateTime(),
            DateTimeFormatter.ofPattern(DATETIME_FORMATTER_DD_MM_YYYY)
        )
        return "${capitalizeEachWord(data.dayOfWeek.toString().lowercase())}, ${data.dayOfMonth} ${
            capitalizeEachWord(
                data.month.toString().lowercase()
            )
        } ${capitalizeEachWord(data.year.toString())}"
    }


    private fun capitalizeEachWord(str: String): String {
        return str.trim().split("\\s+".toRegex())
            .joinToString(" ") {
                it.replaceFirstChar { ch ->
                    if (ch.isLowerCase()) ch.titlecase(
                        Locale.getDefault()
                    ) else ch.toString()
                }
            }
    }
}