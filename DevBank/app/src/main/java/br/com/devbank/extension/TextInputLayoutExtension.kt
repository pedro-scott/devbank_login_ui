package br.com.devbank.extension

import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.errorListener(errorMessage: String, validationCondition: (() -> Boolean)?) =
    validationCondition?.let { validationConditionNonNull ->
        this.sendErrorMessageListener(errorMessage, validationConditionNonNull)
        this.removeErrorMessageListener(validationConditionNonNull)
    }

fun TextInputLayout.sendErrorMessageListener(errorMessage: String, validationCondition: () -> Boolean) =
    this.editText?.run {
        setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                if (!validationCondition()) {
                    this@sendErrorMessageListener.sendErrorMessage(errorMessage)
                }
            }
        }
    }

fun TextInputLayout.removeErrorMessageListener(validationCondition: () -> Boolean) =
    this.editText?.run {
        doOnTextChanged { _, _, _, _ ->
            if (validationCondition()) {
                this@removeErrorMessageListener.removeErrorMessage()
            }
        }
    }

fun TextInputLayout.sendErrorMessage(errorMessage: String) {
    with(this) {
        isErrorEnabled = true
        error = errorMessage
    }
}

fun TextInputLayout.removeErrorMessage() {
    with(this) {
        isErrorEnabled = false
        error = null
    }
}