package br.com.devbank.feature

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(
    fragment: Fragment,
    private val fragmentList: List<Fragment>
) : FragmentStateAdapter(fragment) {
    override fun createFragment(position: Int) = fragmentList[position]

    override fun getItemCount() = fragmentList.size
}