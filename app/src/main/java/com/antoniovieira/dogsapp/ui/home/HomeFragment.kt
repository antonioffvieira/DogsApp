package com.antoniovieira.dogsapp.ui.home

import androidx.fragment.app.Fragment
import com.antoniovieira.dogsapp.R

class HomeFragment : Fragment(R.layout.fragment_home) {

    companion object {
        const val TAG = "HomeFragment"

        fun newInstance() = HomeFragment()
    }

}