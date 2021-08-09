package br.com.luishenrique.login.presentation.login

import com.google.firebase.auth.FirebaseUser
import java.lang.Exception

interface LoginContract {
    interface View {
        fun sendEmailAndPassword(email: String, password: String)
        fun loginSucessfull()
        fun loginFailed(exception: Exception?)
        fun emptyEmailOrPassword()
        fun nonExistentUser()
        fun requireActivity(): LoginActivity
        fun emptyEmail()
        fun emptyPassword()
        fun goToRegister()
    }
    interface Presenter {
        fun initializeFirebaseAuth()
        fun isFieldValid(): Boolean
        fun verifyCredentialsAndSubmit(email: String, password: String)
        fun sendEmailAndPassword()
    }
}