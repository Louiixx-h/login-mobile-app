package br.com.luislabs.conversaonline.initial

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.luislabs.conversaonline.R

class FirstScreenActivity : AppCompatActivity(), FirstScreenContract {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        fun newInstance(context: Context): Intent {
            return Intent(context, FirstScreenActivity::class.java)
        }
    }
}