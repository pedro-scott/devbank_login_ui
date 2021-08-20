package br.com.devbank.extension

import android.text.Editable

fun String.toEditable() : Editable = Editable.Factory.getInstance().newEditable(this)

fun String.justNumber() : String = this.replace("-", "")