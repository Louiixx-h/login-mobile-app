package br.com.luislabs.conversaonline.auth.presentation.register

import android.app.Activity
import java.lang.Exception

interface RegisterContract {
    interface View {
        fun registerUser(email: String, password: String)
        fun registeredSuccessfully()
        fun emptyEmailOrPassword()
        fun errorWhenRegisteringUser(exception: Exception?)
        fun emptyEmail()
        fun emptyPassword()
        fun requireActivity(): Activity
    }

    interface Presenter {
        fun initializeFirebaseAuth()
        fun isFieldValid(): Boolean
        fun registerUser(email: String, password: String)
    }
}