package com.example.izhik_lab6

import android.content.Context
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.google.android.material.R as MaterialR
import com.google.android.material.snackbar.Snackbar

object SnackbarStyler {

    @JvmStatic
    fun configure(context: Context, snackbar: Snackbar) {
        val snackbarView = snackbar.view
        val params = snackbarView.layoutParams as? ViewGroup.MarginLayoutParams
        params?.setMargins(24, 24, 24, 24)
        snackbarView.layoutParams = params
        snackbarView.background = ContextCompat.getDrawable(context, R.drawable.bg_snackbar)
        ViewCompat.setElevation(snackbarView, 12f)

        val textView = snackbarView.findViewById<TextView>(MaterialR.id.snackbar_text)
        textView.setTextColor(ContextCompat.getColor(context, R.color.snackbar_text))
        textView.maxLines = 4
    }
}
