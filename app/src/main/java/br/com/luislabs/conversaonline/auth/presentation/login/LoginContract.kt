package br.com.luislabs.conversaonline.auth.presentation.login

import android.app.Activity
import java.lang.Exception

interface LoginContract {
    interface View {
        fun sendEmailAndPassword(email: String, password: String)
        fun loginSucessfull()
        fun loginFailed(exception: Exception?)
        fun emptyEmailOrPassword()
        fun nonExistentUser()
        fun emptyEmail()
        fun emptyPassword()
        fun goToRegister()
        fun requireActivity(): Activity
        fun loginWithGoogle()
    }
    interface Presenter {
        fun initializeFirebaseAuth()
        fun isFieldValid(): Boolean
        fun verifyCredentialsAndSubmit(email: String, password: String)
        fun sendEmailAndPassword()
        fun loginWithGoogle()
    }
}