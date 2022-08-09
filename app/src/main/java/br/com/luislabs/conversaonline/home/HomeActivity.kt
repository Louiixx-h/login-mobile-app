package br.com.luislabs.conversaonline.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.luislabs.conversaonline.R
import br.com.luislabs.conversaonline.initial.FirstScreenActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(findViewById(R.id.toolbarHome))
    }

    companion object {
        @JvmStatic
        fun newInstance(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }
}