package br.com.devbank.extension

import android.widget.Button
import android.widget.EditText
import androidx.core.widget.doOnTextChanged

fun Button.enableByFieldsValidation(validationConditions: Map<EditText, () -> Boolean>) {
    val checkFiedlsToEnableButton: () -> Unit = { this.isEnabled = validationConditions.values.all { condition -> condition() } }

    validationConditions.keys.forEach { editText ->
        editText.doOnTextChanged { _, _, _, _ ->
            checkFiedlsToEnableButton()
        }
    }
}

@JvmName("enableByFieldsValidationWithList")
fun Button.enableByFieldsValidation(validationConditions: Map<EditText, List<() -> Boolean>>) {
    val checkFiedlsToEnableButton: () -> Unit = {
        this.isEnabled = validationConditions.values.all { list ->
            list.all { condition -> condition() }
        }
    }

    validationConditions.keys.forEach { editText ->
        editText.doOnTextChanged { _, _, _, _ ->
            checkFiedlsToEnableButton()
        }
    }
}