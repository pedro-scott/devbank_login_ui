package br.com.devbank.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import br.com.devbank.databinding.FragmentLoginBinding
import br.com.devbank.extension.enableByFieldsValidation
import br.com.devbank.extension.enableOneTime
import br.com.devbank.extension.textToString
import br.com.devbank.utils.Mask

class LoginFragment : Fragment() {

    private var binding: FragmentLoginBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let {
            with(it) {

                // Setting a Mask for EditText
                editTextdCPF.addTextChangedListener(Mask.mask("###.###.###-##", editTextdCPF))

                // Providing the ID's from fields and the validation conditions to enable the buttonLogin
                val validationConditions = mapOf<EditText, () -> Boolean>(
                    Pair(editTextdCPF) { editTextdCPF.textToString().length >= 14 },
                    Pair(editTextdPassword) { editTextdPassword.textToString().length >= 6 }
                )

                buttonLogin.run {

                    // Enable buttonLogin by fields validation
                    enableByFieldsValidation(validationConditions)

                    // Enable LottieAnimation for just one time and hide it
                    setOnClickListener { animationLoginDone.enableOneTime(buttonLogin) }

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

}