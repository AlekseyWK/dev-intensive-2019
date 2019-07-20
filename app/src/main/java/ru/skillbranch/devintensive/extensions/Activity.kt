package ru.skillbranch.devintensive.extensions

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager


fun Activity.hideKeyboard()
{
    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val view = currentFocus
    if (view != null) {
        imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}

fun Activity.isKeyboardOpen(): Boolean {
    val r = Rect()

    val rootView = (findViewById<ViewGroup>(android.R.id.content)).getChildAt(0)
    val visibleThreshold = (100 * Resources.getSystem().displayMetrics.density).toInt()


    rootView.getWindowVisibleDisplayFrame(r)

    val heightDiff = rootView.rootView.height - r.height()

    return heightDiff > visibleThreshold;
}
fun Activity.isKeyboardClosed(): Boolean =!this.isKeyboardOpen()



