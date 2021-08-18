package br.com.devbank.utils

import androidx.appcompat.app.AppCompatDelegate

object AppThemeMode {

    var initSetup = true
        private set

    fun initSetupDone() { initSetup = false }

    fun setNightMode() = AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
    fun setLightMode() = AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

}