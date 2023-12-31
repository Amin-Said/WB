package me.aminsaid.core.utils

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText

// views
fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun EditText.getStringOnTextChanged(block: (string: String) -> Unit) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable) {
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            block(s.toString())
        }
    })
}
