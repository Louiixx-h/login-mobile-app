package br.com.luislabs.auth.utils

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun Fragment.toast(text: String) {
    Toast.makeText(this.requireContext(), text, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.toast(text: String) {
    android.widget.Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

