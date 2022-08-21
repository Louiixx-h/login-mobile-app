package br.com.luislabs.conversaonline.auth.model

import android.net.Uri

data class UserRegister(
    val email: String,
    val password: String,
    val name: String?,
    val photoUri: Uri?,
)
