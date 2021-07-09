package br.com.devbank.modelo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.devbank.databinding.FragmentLoginBinding
import br.com.devbank.extension.disable
import br.com.devbank.extension.enable
import br.com.devbank.feature.Mask
import br.com.devbank.feature.enableButton

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
                editTextdCPF.addTextChangedListener(Mask.mask("###.###.###-##", editTextdCPF))

                editTextdCPF.enableButton(this) //addTextChangedListener(textWatcherEnableButton)
                editTextdPassword.enableButton(this) //addTextChangedListener(textWatcherEnableButton)

                buttonLogin.setOnClickListener { animationLoginDone.enable(buttonLogin) }

                animationLoginDone.disable(buttonLogin)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

}