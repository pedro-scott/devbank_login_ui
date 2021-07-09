package br.com.devbank.feature

import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.viewbinding.ViewBinding
import br.com.devbank.databinding.FragmentLoginBinding
import br.com.devbank.databinding.FragmentRegisterOneBinding
import br.com.devbank.databinding.FragmentRegisterThreeBinding
import br.com.devbank.databinding.FragmentRegisterTwoBinding

fun EditText.enableButton(binding: ViewBinding) {
    fun checkFieldsToEnableButton(vararg conditions: Boolean, button: Button) { button.isEnabled = conditions.all { it } }

    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (binding is FragmentLoginBinding) {
                with(binding) {
                    checkFieldsToEnableButton(
                        editTextdCPF.text.toString().length >= 14,
                        editTextdPassword.text.toString().length >= 6,
                        button = buttonLogin
                    )
                }
            }

            if (binding is FragmentRegisterOneBinding) {
                with(binding) {
                    checkFieldsToEnableButton(
                        editTextdFullName.text.toString().length >= 10,
                        editTextdCPF.text.toString().length == 14,
                        completeTextdGender.text.isNotEmpty(),
                        editTextdPhone.text.toString().length == 16,
                        editTextDate.text?.isNotEmpty() ?: false,
                        editTextdEmail.text.toString().length >= 14,
                        button = buttonNext
                    )
                }
            }

            if (binding is FragmentRegisterTwoBinding) {
                with(binding) {
                    checkFieldsToEnableButton(
                        editTextdAdress.text.toString().length >= 10,
                        editTextZipCode.text.toString().length == 9,
                        editTextNumber.text.toString().length >= 2 || editTextNumber.text.toString() == "0",
                        editTextDistrict.text.toString().length > 2,
                        editTextCity.text.toString().length > 2,
                        completeTextState.text.isNotEmpty(),
                        button = buttonNext
                    )
                }
            }

            if (binding is FragmentRegisterThreeBinding) {
                with(binding) {
                    checkFieldsToEnableButton(
                        editTextUser.text.toString().length >= 5,
                        editTextPassword.text.toString().length >= 6,
                        editTextConfirmPassword.text.toString().length >= 6,
                        button = buttonRegister
                    )
                }
            }
        }

        override fun afterTextChanged(s: Editable?) {
        }
    })
}