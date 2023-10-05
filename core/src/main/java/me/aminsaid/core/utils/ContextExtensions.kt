package me.aminsaid.core.utils

import android.content.Context
import android.widget.Toast
import me.aminsaid.core.R

// context
fun Context.showError(errorMsg: String) {
    Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show()
}

fun Context.handleErrorState(message: String?, errorCode: Int?) {
    val errorMsgRes = ApiErrorHandler.getMsgFromErrorCode(errorCode).errorMsgRes
    val errorMsg = if (!ConnectivityUtils(this).isInternetAvailable()) getString(
        R.string.no_internet
    ) else errorMsgRes?.let {
        getString(it)
    } ?: message
    errorMsg?.let { showError(it) }
}

