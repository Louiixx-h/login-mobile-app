package br.com.luislabs.conversaonline.auth.presentation.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import br.com.luislabs.conversaonline.auth.presentation.register.RegisterFragment
import br.com.luislabs.auth.utils.toast
import br.com.luislabs.conversaonline.R
import br.com.luislabs.conversaonline.home.HomeActivity
import br.com.luislabs.conversaonline.home.HomeFragment
import kotlinx.android.synthetic.main.activity_login.*
import java.lang.Exception

class LoginFragment : Fragment(R.layout.activity_login), LoginContract.View {

    private val presenter: LoginContract.Presenter = LoginPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.initializeFirebaseAuth()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        requireContext().startActivity(HomeActivity.newInstance(requireContext()))
    }

    override fun loginFailed(exception: Exception?) {
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
        with(parentFragmentManager.beginTransaction()) {
            replace(R.id.fragmentContainerView, RegisterFragment.newInstance())
            addToBackStack(null)
            commit()
        }
    }

    override fun nonExistentUser() {
        toast(getString(R.string.usuario_inexistente))
    }
}