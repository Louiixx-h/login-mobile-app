package br.com.luishenrique.login.presentation.login

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginPresenter(private val view: LoginContract.View): LoginContract.Presenter {

    private lateinit var auth: FirebaseAuth
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var user: FirebaseUser

    override fun initializeFirebaseAuth() {
        auth = Firebase.auth
    }

    override fun verifyCredentialsAndSubmit(email: String, password: String) {
        this.email = email
        this.password = password

        if (!isFieldValid()) return

        sendEmailAndPassword()
    }

    override fun sendEmailAndPassword() {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(view.requireActivity()) { task ->
                if (task.isSuccessful) {
                    view.loginSucessfull()
                    val firebaseUser = auth.currentUser
                    if ( firebaseUser != null) {
                        user = firebaseUser
                    }
                } else {
                    view.loginFailed(task.exception)
                }
            }
    }

    override fun isFieldValid(): Boolean {
        if (email.isEmpty() && password.isEmpty()) {
            view.emptyEmailOrPassword()
            return false
        } else if (email.isEmpty()) {
            view.emptyEmail()
            return false
        } else if (password.isEmpty()) {
            view.emptyPassword()
            return false
        }
        return true
    }
}