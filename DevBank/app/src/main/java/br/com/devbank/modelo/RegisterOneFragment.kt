package br.com.devbank.modelo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.devbank.R
import br.com.devbank.databinding.FragmentRegisterOneBinding
import br.com.devbank.extension.removeErrorMessage
import br.com.devbank.extension.sendErrorMessage
import br.com.devbank.feature.Mask
import br.com.devbank.feature.enableButton
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

        val items = listOf(getString(R.string.option1_menu_register), getString(R.string.option2_menu_register))
        val adapter = ArrayAdapter(requireContext(), R.layout.list_item, items)

        binding?.completeTextdGender?.setAdapter(adapter)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText(getString(R.string.title_date_picker))
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()

        binding?.let {
            with(it) {
                editTextdCPF.addTextChangedListener(Mask.mask(Mask.CPF, editTextdCPF))
                editTextdPhone.addTextChangedListener(Mask.mask(Mask.PHONE, editTextdPhone))

                editTextDate.setOnClickListener {
                    activity?.let { act -> datePicker.show(act.supportFragmentManager, "DATE_PICKER") }

                    datePicker.addOnPositiveButtonClickListener {
                        editTextDate.setText(SimpleDateFormat("dd'/'MM'/'yyyy").format(datePicker.selection))
                    }
                }

                editTextdFullName.apply {
                    enableButton(this@with)
                    sendErrorMessage(textFieldFullName, this@with, requireContext())
                    removeErrorMessage(textFieldFullName, this@with)
                }

                editTextdCPF.apply {
                    enableButton(this@with)
                    sendErrorMessage(textFieldCPF, this@with, requireContext())
                    removeErrorMessage(textFieldCPF, this@with)
                }

                completeTextdGender.enableButton(this@with)

                editTextdPhone.apply {
                    enableButton(this@with)
                    sendErrorMessage(textFieldPhone, this@with, requireContext())
                    removeErrorMessage(textFieldPhone, this@with)
                }

                editTextDate.enableButton(this@with)

                editTextdEmail.apply {
                    enableButton(this@with)
                    sendErrorMessage(textFieldEmail, this@with, requireContext())
                    removeErrorMessage(textFieldEmail, this@with)
                }

                buttonNext.setOnClickListener {
                    findNavController().navigate(R.id.action_homeFragment_to_registerTwoFragment)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

}