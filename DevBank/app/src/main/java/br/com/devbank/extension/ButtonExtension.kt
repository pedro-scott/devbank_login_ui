package br.com.devbank.extension

import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText

fun Button.enableByFieldsValidation(validationConditions: Map<EditText, () -> Boolean>) {
    val checkFiedlsToEnableButton: () -> Unit = { this.isEnabled = validationConditions.values.all { condition -> condition() } }

    validationConditions.keys.forEach { editText ->
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkFiedlsToEnableButton()
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }
}