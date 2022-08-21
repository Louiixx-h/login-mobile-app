package br.com.luislabs.conversaonline.auth.presentation.register

import android.app.Activity
import br.com.luislabs.conversaonline.auth.model.UserRegister
import java.lang.Exception

interface RegisterContract {
    interface View {
        fun registeredSuccessfully()
        fun emptyEmailOrPassword()
        fun errorWhenRegisteringUser(exception: Exception?)
        fun emptyEmail()
        fun emptyPassword()
        fun requireActivity(): Activity
        fun registerUser(userRegister: UserRegister)
        fun getPhotoUri()
    }

    interface Presenter {
        fun initializeFirebaseAuth()
        fun isFieldValid(): Boolean
        fun setProfile()
        fun registerUser(userRegister: UserRegister)
    }
}