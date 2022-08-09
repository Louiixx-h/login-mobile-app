package br.com.luislabs.conversaonline.home

import androidx.fragment.app.Fragment
import br.com.luislabs.conversaonline.R

class HomeFragment : Fragment(R.layout.fragment_home) {


    companion object {
        @JvmStatic
        fun newInstance(): Fragment {
            return HomeFragment()
        }
    }
}