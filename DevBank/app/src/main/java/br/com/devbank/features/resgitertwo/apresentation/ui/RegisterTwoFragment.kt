package br.com.devbank.features.resgitertwo.apresentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import br.com.devbank.R
import br.com.devbank.base.BaseFragment
import br.com.devbank.databinding.FragmentRegisterTwoBinding
import br.com.devbank.extension.*
import br.com.devbank.features.resgitertwo.apresentation.viewmodel.RegisterTwoViewModel
import br.com.devbank.utils.Command
import br.com.devbank.utils.Mask
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterTwoFragment : BaseFragment() {

    private var binding: FragmentRegisterTwoBinding? = null
    private val viewModel: RegisterTwoViewModel by viewModel()
    override val command: MutableLiveData<Command> = MutableLiveData()

    private var isCepExists = false

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

        viewModel.command = command

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
                    Pair(editTextdAdress, listOf { editTextdAdress.textToString().length >= 10 }),
                    Pair(editTextZipCode, listOf({ editTextZipCode.textToString().length == 9 }, { isCepExists })),
                    Pair(editTextNumber, listOf { editTextNumber.textToString().length >= 2 || editTextNumber.textToString() == "0" }),
                    Pair(editTextDistrict, listOf { editTextDistrict.textToString().length > 2 }),
                    Pair(editTextCity, listOf { editTextCity.textToString().length > 2 }),
                    Pair(completeTextState, listOf { completeTextState.textToString().isNotEmpty() })
                )

                // Setting errors for fields
                textFieldAdress.errorListener(errorMessage = getString(R.string.error_adress), validationConditions[editTextdAdress]?.get(0))
                textFieldZipCode.errorListener(getString(R.string.error_zip_code), validationConditions[editTextZipCode]?.get(0))
                textFieldNumber.errorListener(errorMessage = getString(R.string.error_number), validationConditions[editTextNumber]?.get(0))
                textFieldDistrict.errorListener(errorMessage = getString(R.string.error_district), validationConditions[editTextDistrict]?.get(0))
                textFieldCity.errorListener(errorMessage = getString(R.string.error_city), validationConditions[editTextCity]?.get(0))

                buttonNext.run {

                    // Enable buttonNext by fields validation
                    enableByFieldsValidation(validationConditions)

                    // Going to the next screen from the app
                    setOnClickListener {
                        findNavController().navigate(R.id.action_registerTwoFragment_to_registerThreeFragment)
                    }

                }

                editTextZipCode.doOnTextChanged { _, _, before, _ ->
                    validationConditions[editTextZipCode]?.get(0)?.let { zipCodeValidationCondition ->
                        if (zipCodeValidationCondition() && before == 0) {
                            viewModel.getAddress(editTextZipCode.textToString().justNumber())
                        }
                    }
                }

                setupObservables()
            }
        }
    }

    private fun setupObservables() {

        viewModel.onSuccessGetAddress.observe(viewLifecycleOwner) { addressByCep ->
            binding?.run {
                with(addressByCep) {
                    if (erro) {
                        isCepExists = false
                        textFieldZipCode.sendErrorMessage(getString(R.string.error_zip_code_unknown))
                    } else {
                        isCepExists = true
                    }

                    editTextdAdress.text = logradouro?.toEditable()
                    editTextCity.text = cidade?.toEditable()
                    editTextDistrict.text = bairro?.toEditable()
                    completeTextState.setText(uf, false)
                }
            }
        }

        viewModel.command.observe(viewLifecycleOwner) { command ->
            when (command) {
                is Command.Loading -> {
                    binding?.run {
                        if (command.value) {
                            progressCircularLayout.visibility = View.VISIBLE
                            buttonNext.isEnabled = false
                        } else {
                            progressCircularLayout.visibility = View.GONE
                        }
                    }
                }

                is Command.Error -> {
                    binding?.run {
                        noConnectionLayout.visibility = View.VISIBLE
                        animNoConnection.playAnimation()
                        animNoConnection.doInEnd {
                            noConnectionLayout.visibility = View.GONE
                        }
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