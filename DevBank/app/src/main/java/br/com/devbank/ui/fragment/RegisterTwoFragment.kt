package br.com.devbank.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.devbank.R
import br.com.devbank.databinding.FragmentRegisterTwoBinding
import br.com.devbank.extension.enableByFieldsValidation
import br.com.devbank.extension.errorListener
import br.com.devbank.extension.textToString
import br.com.devbank.utils.Mask

class RegisterTwoFragment : Fragment() {

    private var binding: FragmentRegisterTwoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterTwoBinding.inflate(inflater, container, false)

        binding?.run {

            // Setting the list for AutoCompleteEditText and the Adapter
            val items = listOf(
                "AC", "AL", "AP", "AM",
                "BA", "CE", "DF", "ES",
                "GO", "MA", "MT", "MS",
                "MG", "PA", "PB", "PR",
                "PE", "PI", "RJ", "RN",
                "RS", "RO", "RR", "SC",
                "SP", "SE", "TO"
            )
            val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
            completeTextState.setAdapter(adapter)

            // Setting the progress from progress bar indicator
            progressIndicatorRegisterTwo.progress = 35

        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let {
            with(it) {

                // Setting the behavior for the navigation button from TopAppBar
                titleTopAppBarRegisterTwo.setNavigationOnClickListener { activity?.onBackPressed() }

                // Setting a Mask for EditText
                editTextZipCode.addTextChangedListener(Mask.mask(Mask.CEP, editTextZipCode))

                // Providing the ID's from fields and the validation conditions to enable the buttonNext
                val validationConditions = mapOf(
                    Pair(editTextdAdress) { editTextdAdress.textToString().length >= 10 },
                    Pair(editTextZipCode) { editTextZipCode.textToString().length == 9 },
                    Pair(editTextNumber) { editTextNumber.textToString().length >= 2 || editTextNumber.textToString() == "0" },
                    Pair(editTextDistrict) { editTextDistrict.textToString().length > 2 },
                    Pair(editTextCity) { editTextCity.textToString().length > 2 },
                    Pair(completeTextState) { completeTextState.textToString().isNotEmpty() }
                )

                // Setting errors for fields
                textFieldAdress.errorListener(errorMessage = getString(R.string.error_adress), validationConditions[editTextdAdress])
                textFieldZipCode.errorListener(errorMessage = getString(R.string.error_zip_code), validationConditions[editTextZipCode])
                textFieldNumber.errorListener(errorMessage = getString(R.string.error_number), validationConditions[editTextNumber])
                textFieldDistrict.errorListener(errorMessage = getString(R.string.error_district), validationConditions[editTextDistrict])
                textFieldCity.errorListener(errorMessage = getString(R.string.error_city), validationConditions[editTextCity])

                buttonNext.run {

                    // Enable buttonNext by fields validation
                    enableByFieldsValidation(validationConditions)

                    // Going to the next screen from the app
                    setOnClickListener {
                        findNavController().navigate(R.id.action_registerTwoFragment_to_registerThreeFragment)
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