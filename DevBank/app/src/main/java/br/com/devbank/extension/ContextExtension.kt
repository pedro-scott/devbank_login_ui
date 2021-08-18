package br.com.devbank.extension

import android.content.Context
import androidx.appcompat.content.res.AppCompatResources

fun Context.getDrawable2(resId: Int) = AppCompatResources.getDrawable(this, resId)