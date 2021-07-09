package br.com.devbank.modelo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.devbank.R
import br.com.devbank.databinding.FragmentRegisterTwoBinding
import br.com.devbank.extension.removeErrorMessage
import br.com.devbank.extension.sendErrorMessage
import br.com.devbank.feature.Mask
import br.com.devbank.feature.enableButton

class RegisterTwoFragment : Fragment() {

    private var binding: FragmentRegisterTwoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterTwoBinding.inflate(inflater, container, false)

        val items = listOf(
            "AC",
            "AL",
            "AP",
            "AM",
            "BA",
            "CE",
            "DF",
            "ES",
            "GO",
            "MA",
            "MT",
            "MS",
            "MG",
            "PA",
            "PB",
            "PR",
            "PE",
            "PI",
            "RJ",
            "RN",
            "RS",
            "RO",
            "RR",
            "SC",
            "SP",
            "SE",
            "TO"
        )
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)

        binding?.let {
            it.completeTextState.setAdapter(adapter)
            it.progressIndicatorRegisterTwo.progress = 35
        }


        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let {
            with(it) {
                titleTopAppBarRegisterTwo.setNavigationOnClickListener { activity?.onBackPressed() }

                editTextZipCode.addTextChangedListener(Mask.mask(Mask.CEP, editTextZipCode))

                editTextdAdress.apply {
                    enableButton(this@with)
                    sendErrorMessage(textFieldAdress, this@with, requireContext())
                    removeErrorMessage(textFieldAdress, this@with)
                }
                editTextZipCode.apply {
                    enableButton(this@with)
                    sendErrorMessage(textFieldZipCode, this@with, requireContext())
                    removeErrorMessage(textFieldZipCode, this@with)
                }
                editTextNumber.apply {
                    enableButton(this@with)
                    sendErrorMessage(textFieldNumber, this@with, requireContext())
                    removeErrorMessage(textFieldNumber, this@with)
                }
                editTextDistrict.apply {
                    enableButton(this@with)
                    sendErrorMessage(textFieldDistrict, this@with, requireContext())
                    removeErrorMessage(textFieldDistrict, this@with)
                }
                editTextCity.apply {
                    enableButton(this@with)
                    sendErrorMessage(textFieldCity, this@with, requireContext())
                    removeErrorMessage(textFieldCity, this@with)
                }
                completeTextState.enableButton(this)

                buttonNext.setOnClickListener {
                    findNavController().navigate(R.id.action_registerTwoFragment_to_registerThreeFragment)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

}