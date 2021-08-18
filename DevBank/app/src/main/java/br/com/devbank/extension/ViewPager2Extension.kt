package br.com.devbank.extension

import android.graphics.drawable.Drawable
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import br.com.devbank.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

fun ViewPager2.setupAdapter(fragment: Fragment, fragmentList: List<Fragment>) =
    ViewPagerAdapter(fragment, fragmentList).let { this.adapter = it }

fun ViewPager2.setupTabLayout(
    tabLayout: TabLayout,
    icons: List<Drawable?>,
    titles: List<String?>
) {
    TabLayoutMediator(tabLayout, this) { tab, position ->
        tab.icon = icons[position]
        tab.text = titles[position]
    }.attach()
}

@JvmName("setupTabLayoutIcons")
fun ViewPager2.setupTabLayout(
    tabLayout: TabLayout,
    icons: List<Drawable?>
) {
    TabLayoutMediator(tabLayout, this) { tab, position -> tab.icon = icons[position] }.attach()
}

@JvmName("setupTabLayoutTitles")
fun ViewPager2.setupTabLayout(
    tabLayout: TabLayout,
    titles: List<String?>
) {
    TabLayoutMediator(tabLayout, this) { tab, position -> tab.text = titles[position] }.attach()
}