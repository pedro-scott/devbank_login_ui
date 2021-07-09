package br.com.devbank.modelo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.devbank.R
import br.com.devbank.databinding.FragmentRegisterThreeBinding
import br.com.devbank.extension.disable
import br.com.devbank.extension.enable
import br.com.devbank.extension.removeErrorMessage
import br.com.devbank.extension.sendErrorMessage
import br.com.devbank.feature.enableButton

class RegisterThreeFragment : Fragment() {

    private var binding: FragmentRegisterThreeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterThreeBinding.inflate(inflater, container, false)

        binding?.progressIndicatorRegisterThree?.progress = 70

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let {
            with(it) {
                titleTopAppBarRegisterThree.setNavigationOnClickListener { activity?.onBackPressed() }

                editTextUser.apply {
                    enableButton(this@with)
                    sendErrorMessage(textFieldUser, this@with, requireContext())
                    removeErrorMessage(textFieldUser, this@with)
                }
                editTextPassword.apply{
                    enableButton(this@with)
                    sendErrorMessage(textFieldPassword, this@with, requireContext())
                    removeErrorMessage(textFieldPassword, this@with)
                }
                editTextConfirmPassword.apply {
                    enableButton(this@with)
                    sendErrorMessage(textFieldConfirmPassword, this@with, requireContext())
                    removeErrorMessage(textFieldConfirmPassword, this@with)
                }

                buttonRegister.setOnClickListener {
                    animationRegisterDone.enable(buttonRegister)

                    progressIndicatorRegisterThree.progress = 100
                    titleTopAppBarRegisterThree.title = getString(R.string.register_done)
                }

                animationRegisterDone.disable(buttonRegister)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

}