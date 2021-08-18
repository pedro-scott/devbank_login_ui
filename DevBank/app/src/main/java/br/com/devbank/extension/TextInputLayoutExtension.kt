package br.com.devbank.extension

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.sendErrorMessage(errorMessage: String, validationCondition: () -> Boolean) =
    this.editText?.run {
        setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                if (!validationCondition()) {
                    this@sendErrorMessage.apply {
                        isErrorEnabled = true
                        error = errorMessage
                    }
                }
            }
        }
    }

fun TextInputLayout.removeErrorMessage(validationCondition: () -> Boolean) =
    this.editText?.run {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (validationCondition()) {
                    this@removeErrorMessage.apply {
                        isErrorEnabled = false
                        error = null
                    }
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

fun TextInputLayout.errorListener(errorMessage: String, validationCondition: (() -> Boolean)?) =
    validationCondition?.let { validationConditionNonNull ->
        this.sendErrorMessage(errorMessage, validationConditionNonNull)
        this.removeErrorMessage(validationConditionNonNull)
    }