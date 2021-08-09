package br.com.luishenrique.login.presentation.register

import java.lang.Exception

interface RegisterContract {
    interface View {
        fun registerUser(email: String, password: String)
        fun registeredSuccessfully()
        fun emptyEmailOrPassword()
        fun errorWhenRegisteringUser(exception: Exception?)
        fun requireActivity(): RegisterActivity
        fun emptyEmail()
        fun emptyPassword()
    }

    interface Presenter {
        fun initializeFirebaseAuth()
        fun isFieldValid(): Boolean
        fun registerUser(email: String, password: String)
    }
}