package br.com.devbank.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import br.com.devbank.R
import br.com.devbank.databinding.FragmentHomeBinding
import br.com.devbank.extension.getDrawable2
import br.com.devbank.extension.setupAdapter
import br.com.devbank.extension.setupTabLayout
import br.com.devbank.utils.AppThemeMode
import br.com.devbank.utils.SharedPreferenceConfig

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null

    private val sharedPreference by lazy {
        activity?.getSharedPreferences(
            SharedPreferenceConfig.SHARED_NAME,
            Context.MODE_PRIVATE
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        // Checking shared preference value and setting the theme mode
        with(AppThemeMode) {
            if (initSetup) {
                initSetupDone()

                sharedPreference?.run {
                    getBoolean(
                        SharedPreferenceConfig.SHARED_BUTTON_THEME_MODE,
                        false
                    ).let { nightMode ->
                        if (nightMode) {
                            binding?.themeButtonHomeFragment?.isChecked = true
                            setNightMode()
                        }
                        else setLightMode()
                    }
                }
            }
        }

        binding?.let { bindingNonNull ->
            with(bindingNonNull) {

                // Setting the list for ViewPager2 and the Adapter
                val fragmentList = listOf(LoginFragment(), RegisterOneFragment())
                viewPagerHomeFragment.setupAdapter(this@HomeFragment, fragmentList)

                // Setting a TabLayout with the ViewPager2
                context?.let { contextNonNull ->
                    listOf(
                        contextNonNull.getDrawable2(R.drawable.ic_dev_bank_login),
                        contextNonNull.getDrawable2(R.drawable.ic_dev_bank_register)
                    ).let { iconList ->
                        viewPagerHomeFragment.setupTabLayout(tabHomeFragment, iconList)
                    }
                }

            }
        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Listen Switch Button to change the theme mode and update the shared preference
        binding?.themeButtonHomeFragment?.setOnCheckedChangeListener { _, isChecked ->
            with(AppThemeMode) {
                if (isChecked) {
                    sharedPreference?.edit { putBoolean(SharedPreferenceConfig.SHARED_BUTTON_THEME_MODE, true) }
                    setNightMode()
                }
                else {
                    sharedPreference?.edit { putBoolean(SharedPreferenceConfig.SHARED_BUTTON_THEME_MODE, false) }
                    setLightMode()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

}