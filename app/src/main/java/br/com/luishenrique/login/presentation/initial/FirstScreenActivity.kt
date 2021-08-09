package br.com.luishenrique.login.presentation.initial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.luishenrique.login.R
import br.com.luishenrique.login.presentation.login.LoginActivity
import br.com.luishenrique.login.presentation.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_main.*

class FirstScreenActivity : AppCompatActivity(), FirstScreenContract {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initial_register.setOnClickListener {
            goToRegister()
        }
        initial_login.setOnClickListener {
            goToLogin()
        }
    }

    override fun goToRegister() {
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    override fun goToLogin() {
        startActivity(Intent(this, LoginActivity::class.java))
    }
}