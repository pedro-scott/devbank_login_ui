package br.com.devbank.features.registerthree.apresentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import br.com.devbank.R
import br.com.devbank.databinding.FragmentRegisterThreeBinding
import br.com.devbank.extension.enableByFieldsValidation
import br.com.devbank.extension.enableOneTime
import br.com.devbank.extension.errorListener
import br.com.devbank.extension.textToString

class RegisterThreeFragment : Fragment() {

    private var binding: FragmentRegisterThreeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterThreeBinding.inflate(inflater, container, false)

        // Setting the progress from progress bar indicator
        binding?.progressIndicatorRegisterThree?.progress = 70

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let {
            with(it) {

                // Setting the behavior for the navigation button from TopAppBar
                titleTopAppBarRegisterThree.setNavigationOnClickListener { activity?.onBackPressed() }

                // Providing the ID's from fields and the validation conditions to enable the buttonRegister
                val validationConditions = mapOf<EditText, () -> Boolean>(
                    Pair(editTextUser) { editTextUser.textToString().length >= 5 },
                    Pair(editTextPassword) { editTextPassword.textToString().length >= 6 },
                    Pair(editTextConfirmPassword) { editTextConfirmPassword.textToString().length >= 6 }
                )

                // Setting errors for fields
                textFieldUser.errorListener(errorMessage = getString(R.string.error_user), validationConditions[editTextUser])
                textFieldPassword.errorListener(errorMessage = getString(R.string.error_password), validationConditions[editTextPassword])
                textFieldConfirmPassword.errorListener(errorMessage = getString(R.string.error_password), validationConditions[editTextConfirmPassword])

                buttonRegister.run {

                    // Enable buttonRegister by fields validation
                    enableByFieldsValidation(validationConditions)

                    setOnClickListener {

                        // Enable LottieAnimation for just one time and hide it
                        animationRegisterDone.enableOneTime(buttonRegister)

                        // Setting the progress from progress bar indicator and the text from TopAppBar
                        progressIndicatorRegisterThree.progress = 100
                        titleTopAppBarRegisterThree.title = getString(R.string.register_done)

                    }

                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

}