package br.com.luishenrique.login.presentation.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import br.com.luishenrique.login.R
import br.com.luishenrique.login.utils.toast
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.editEmail
import kotlinx.android.synthetic.main.activity_register.editPassword
import java.lang.Exception

class RegisterActivity : AppCompatActivity(), RegisterContract.View {

    private val presenter: RegisterContract.Presenter = RegisterPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        presenter.initializeFirebaseAuth()

        register_button.setOnClickListener {
            registerUser(
                email = editEmail.text.toString(),
                password = editPassword.text.toString()
            )
        }
    }

    override fun registerUser(email: String, password: String) {
        presenter.registerUser(email = email, password = password)
    }

    override fun registeredSuccessfully() {
        Log.d("register", "createUserWithEmail:success")
        toast(getString(R.string.cadastro_realizado_com_sucesso))
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

    override fun errorWhenRegisteringUser(exception: Exception?) {
        Log.w("register", "createUserWithEmail:failure", exception)
        toast(getString(R.string.erro_ao_cadastrar_usuario))
    }

    override fun requireActivity(): RegisterActivity {
        return this
    }
}