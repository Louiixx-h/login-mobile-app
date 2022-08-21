package br.com.luislabs.conversaonline.auth.presentation.register

import br.com.luislabs.conversaonline.auth.model.UserRegister
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterPresenter(private val view: RegisterContract.View): RegisterContract.Presenter {

    private lateinit var auth: FirebaseAuth
    private lateinit var userRegister: UserRegister
    private lateinit var user: FirebaseUser

    override fun initializeFirebaseAuth() {
        auth = Firebase.auth
    }

    override fun registerUser(userRegister: UserRegister) {
        this.userRegister = userRegister

        if (!isFieldValid()) return

        auth.createUserWithEmailAndPassword(userRegister.email, userRegister.password)
            .addOnCompleteListener(view.requireActivity()) { task ->
                if (task.isSuccessful) {
                    view.registeredSuccessfully()
                    val firebaseUser = auth.currentUser
                    if ( firebaseUser != null) {
                        user = firebaseUser
                        setProfile()
                    }
                } else {
                    view.errorWhenRegisteringUser(task.exception)
                }
            }
    }

    override fun setProfile() {
        val profileUpdates = UserProfileChangeRequest.Builder()
            .setDisplayName(userRegister.name)
            .setPhotoUri(userRegister.photoUri)
            .build()

        user.updateProfile(profileUpdates)
    }

    override fun isFieldValid(): Boolean {
        if (this.userRegister.email.isEmpty() && this.userRegister.password.isEmpty()) {
            view.emptyEmailOrPassword()
            return false
        } else if (this.userRegister.email.isEmpty()) {
            view.emptyEmail()
            return false
        } else if (this.userRegister.password.isEmpty()) {
            view.emptyPassword()
            return false
        }
        return true
    }
}