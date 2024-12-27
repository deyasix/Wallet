package com.example.wallet.ext

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

fun EditText.requestFocusAndOpenIme() {
    val imm: InputMethodManager =
        context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    requestFocus()
    imm.showSoftInput(this, 0)
}