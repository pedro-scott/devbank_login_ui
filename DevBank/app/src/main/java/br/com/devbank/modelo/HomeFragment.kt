package br.com.devbank.modelo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import br.com.devbank.R
import br.com.devbank.databinding.FragmentHomeBinding
import br.com.devbank.feature.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (init) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val fragmentList = listOf(LoginFragment(), RegisterOneFragment())
        val viewPagerAdapter = ViewPagerAdapter(
            fragment = this,
            fragmentList = fragmentList
        )

        binding?.let { bindingNonNull ->
            with(bindingNonNull) {
                themeButtonHomeFragment.setOnCheckedChangeListener { buttonView, isChecked ->
                    init = false

                    if (isChecked) {
                        moonAnim?.visibility = View.VISIBLE
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }
                    else {
                        sunAnim?.visibility = View.VISIBLE
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }
                }

                viewPagerHomeFragment.adapter = viewPagerAdapter

                TabLayoutMediator(tabHomeFragment, viewPagerHomeFragment) { tab, position ->
                    when (position) {
                        0 ->
                            context?.let { contextNonNull ->
                                tab.icon = AppCompatResources.getDrawable(contextNonNull,
                                    R.drawable.ic_dev_bank_login
                                )
                            }
                        1 ->
                            context?.let { contextNonNull ->
                                tab.icon = AppCompatResources.getDrawable(contextNonNull,
                                    R.drawable.ic_dev_bank_register
                                )
                            }
                    }
                }.attach()
            }
        }

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding = null
    }

    companion object {
        private var init = true
    }

}