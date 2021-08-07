package com.baptistecarlier.kotlin.datagouvfr.extensions

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

fun View.hideKeyboard() {
    val imm: InputMethodManager =
        context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}