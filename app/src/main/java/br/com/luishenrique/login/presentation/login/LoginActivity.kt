package br.com.luishenrique.login.presentation.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.com.luishenrique.login.R
import br.com.luishenrique.login.presentation.register.RegisterActivity
import br.com.luishenrique.login.utils.toast
import kotlinx.android.synthetic.main.activity_login.*
import java.lang.Exception

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private val presenter: LoginContract.Presenter = LoginPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter.initializeFirebaseAuth()

        login_button.setOnClickListener {
            sendEmailAndPassword(
                email = editEmail.text.toString(),
                password = editPassword.text.toString()
            )
        }

        text_register.setOnClickListener {
            goToRegister()
        }
    }

    override fun sendEmailAndPassword(email: String, password: String) {
        presenter.verifyCredentialsAndSubmit(email, password)
    }

    override fun loginSucessfull() {
        Log.d("login", "signInWithEmail:success")
        toast(getString(R.string.sucesso_no_login))
    }

    override fun loginFailed(exception: Exception?) {
        Log.w("login", "signInWithEmail:failure", exception)
        toast(getString(R.string.falha_no_login))
    }

    override fun emptyEmailOrPassword() {
        editEmail.error = getString(R.string.campo_vazio)
        editPassword.error = getString(R.string.campo_vazio)
    }

    override fun emptyEmail() {
        editEmail.error = getString(R.string.campo_vazio)
    }

    override fun emptyPassword() {
        editPassword.error = getString(R.string.campo_vazio)
    }

    override fun goToRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    override fun nonExistentUser() {
        toast(getString(R.string.usuario_inexistente))
    }

    override fun requireActivity(): LoginActivity {
        return this
    }
}