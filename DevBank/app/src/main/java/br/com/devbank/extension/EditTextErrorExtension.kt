package br.com.devbank.extension

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.viewbinding.ViewBinding
import br.com.devbank.R
import br.com.devbank.databinding.FragmentRegisterOneBinding
import br.com.devbank.databinding.FragmentRegisterThreeBinding
import br.com.devbank.databinding.FragmentRegisterTwoBinding
import com.google.android.material.textfield.TextInputLayout

fun EditText.contentIsInvalid(binding: ViewBinding) =
    when (binding) {
        is FragmentRegisterOneBinding -> {
            with(binding) {
                when (this@contentIsInvalid) {
                    editTextdFullName -> this@contentIsInvalid.text.toString().length < 10
                    editTextdCPF -> this@contentIsInvalid.text.toString().length < 14
                    editTextdPhone -> this@contentIsInvalid.text.toString().length < 16
                    editTextdEmail -> this@contentIsInvalid.text.toString().length < 14
                    else -> false
                }
            }
        }

        is FragmentRegisterTwoBinding -> {
            with(binding) {
                when (this@contentIsInvalid) {
                    editTextdAdress -> this@contentIsInvalid.text.toString().length < 10
                    editTextZipCode -> this@contentIsInvalid.text.toString().length < 9
                    editTextNumber -> this@contentIsInvalid.text.toString().length < 2 && this@contentIsInvalid.text.toString() != "0"
                    editTextDistrict -> this@contentIsInvalid.text.toString().length < 3
                    editTextCity -> this@contentIsInvalid.text.toString().length < 3
                    else -> false
                }
            }
        }

        is FragmentRegisterThreeBinding -> {
            with(binding) {
                when (this@contentIsInvalid) {
                    editTextUser -> this@contentIsInvalid.text.toString().length < 5
                    editTextPassword -> this@contentIsInvalid.text.toString().length < 6
                    editTextConfirmPassword -> this@contentIsInvalid.text.toString().length < 6
                    else -> false
                }
            }
        }

        else -> false
    }

fun EditText.takeErrorMessage(binding: ViewBinding, context: Context) : String? =
    when (binding) {
        is FragmentRegisterOneBinding -> {
            with(binding) {
                when (this@takeErrorMessage) {
                    editTextdFullName -> context.getString(R.string.error_name)
                    editTextdCPF -> context.getString(R.string.error_cpf)
                    editTextdPhone -> context.getString(R.string.error_phone)
                    editTextdEmail -> context.getString(R.string.error_email)
                    else -> null
                }
            }
        }

        is FragmentRegisterTwoBinding -> {
            with(binding) {
                when (this@takeErrorMessage) {
                    editTextdAdress -> context.getString(R.string.error_adress)
                    editTextZipCode -> context.getString(R.string.error_zip_code)
                    editTextNumber -> context.getString(R.string.error_number)
                    editTextDistrict -> context.getString(R.string.error_district)
                    editTextCity -> context.getString(R.string.error_city)
                    else -> null
                }
            }
        }

        is FragmentRegisterThreeBinding -> {
            with(binding) {
                when (this@takeErrorMessage) {
                    editTextUser -> context.getString(R.string.error_user)
                    editTextPassword -> context.getString(R.string.error_password)
                    editTextConfirmPassword -> context.getString(R.string.error_password)
                    else -> null
                }
            }
        }

        else -> null
    }

fun EditText.sendErrorMessage(inputLayout: TextInputLayout, binding: ViewBinding, context: Context) =
    this.setOnFocusChangeListener { v, hasFocus ->
        if (!hasFocus) {
            if (this.contentIsInvalid(binding)) {
                inputLayout.apply {
                    isErrorEnabled = true
                    error = this@sendErrorMessage.takeErrorMessage(binding, context)
                }
            }
        }
    }

fun EditText.removeErrorMessage(inputLayout: TextInputLayout, binding: ViewBinding) =
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (!this@removeErrorMessage.contentIsInvalid(binding)) {
                inputLayout.apply {
                    isErrorEnabled = false
                    error = null
                }
            }
        }

        override fun afterTextChanged(s: Editable?) {
        }
    })