package com.antoniovieira.dogsapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.antoniovieira.dogsapp.R
import com.antoniovieira.dogsapp.databinding.ActivityMainBinding
import com.antoniovieira.dogsapp.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(
                R.id.fragmentContainer, HomeFragment.newInstance(), HomeFragment.TAG)
                .commitNow()

    }

}