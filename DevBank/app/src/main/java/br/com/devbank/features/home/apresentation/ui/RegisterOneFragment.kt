package br.com.devbank.features.home.apresentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.devbank.R
import br.com.devbank.databinding.FragmentRegisterOneBinding
import br.com.devbank.extension.enableByFieldsValidation
import br.com.devbank.extension.errorListener
import br.com.devbank.extension.textToString
import br.com.devbank.utils.Mask
import com.google.android.material.datepicker.MaterialDatePicker
import java.text.SimpleDateFormat

class RegisterOneFragment : Fragment() {

    private var binding: FragmentRegisterOneBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterOneBinding.inflate(inflater, container, false)

        // Setting the list for AutoCompleteEditText and the Adapter
        val items = listOf(getString(R.string.option1_menu_register), getString(R.string.option2_menu_register))
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        binding?.completeTextdGender?.setAdapter(adapter)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setting the MaterialDatePicker
        val datePicker =
            MaterialDatePicker.Builder.datePicker()
                .setTitleText(getString(R.string.title_date_picker))
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .build()

        binding?.let {
            with(it) {

                // Setting a Mask for EditText's
                editTextdCPF.addTextChangedListener(Mask.mask(Mask.CPF, editTextdCPF))
                editTextdPhone.addTextChangedListener(Mask.mask(Mask.PHONE, editTextdPhone))

                // Listen the field for date and show the Material DatePicker
                editTextDate.setOnClickListener {
                    activity?.let { act -> datePicker.show(act.supportFragmentManager, "DATE_PICKER") }

                    datePicker.addOnPositiveButtonClickListener {
                        editTextDate.setText(SimpleDateFormat("dd'/'MM'/'yyyy").format(datePicker.selection))
                    }
                }

                // Providing the ID's from fields and the validation conditions to enable the buttonNext
                val validationConditions = mapOf(
                    Pair(editTextdFullName) { editTextdFullName.textToString().length >= 10 },
                    Pair(editTextdCPF) { editTextdCPF.textToString().length == 14 },
                    Pair(completeTextdGender) { completeTextdGender.textToString().isNotEmpty() },
                    Pair(editTextdPhone) { editTextdPhone.textToString().length == 16 },
                    Pair(editTextDate) { editTextDate.textToString().isNotEmpty() },
                    Pair(editTextdEmail) { editTextdEmail.textToString().length >= 14 }
                )

                // Setting errors for fields
                textFieldFullName.errorListener(errorMessage = getString(R.string.error_name), validationConditions[editTextdFullName])
                textFieldCPF.errorListener(errorMessage = getString(R.string.error_cpf), validationConditions[editTextdCPF])
                textFieldPhone.errorListener(errorMessage = getString(R.string.error_phone), validationConditions[editTextdPhone])
                textFieldEmail.errorListener(errorMessage = getString(R.string.error_email), validationConditions[editTextdEmail])

                buttonNext.run {

                    // Enable buttonNext by fields validation
                    enableByFieldsValidation(validationConditions)

                    // Going to the next screen from the app
                    setOnClickListener {
                        findNavController().navigate(R.id.action_homeFragment_to_registerTwoFragment)
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