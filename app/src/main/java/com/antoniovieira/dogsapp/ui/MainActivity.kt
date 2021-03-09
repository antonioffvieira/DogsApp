package com.antoniovieira.dogsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.antoniovieira.dogsapp.R
import com.antoniovieira.dogsapp.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(
                R.id.fragmentContainer, HomeFragment.newInstance(), HomeFragment.TAG)
                .commitNow()

    }

}