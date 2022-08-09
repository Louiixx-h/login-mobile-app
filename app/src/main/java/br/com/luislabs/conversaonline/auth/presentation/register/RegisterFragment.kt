package br.com.luislabs.conversaonline.auth.presentation.register

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import br.com.luislabs.auth.utils.toast
import br.com.luislabs.conversaonline.R
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.editEmail
import kotlinx.android.synthetic.main.activity_register.editPassword
import java.lang.Exception

class RegisterFragment : Fragment(R.layout.activity_register), RegisterContract.View {

    private val presenter: RegisterContract.Presenter = RegisterPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.initializeFirebaseAuth()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        toast(getString(R.string.erro_ao_cadastrar_usuario))
    }

    companion object {
        fun newInstance(): RegisterFragment {
            return RegisterFragment()
        }
    }
}