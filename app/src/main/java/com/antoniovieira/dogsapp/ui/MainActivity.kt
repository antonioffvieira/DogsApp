package com.antoniovieira.dogsapp.ui

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.antoniovieira.dogsapp.R
import com.antoniovieira.dogsapp.databinding.ActivityMainBinding
import com.antoniovieira.dogsapp.ui.home.HomeFragment
import com.antoniovieira.dogsapp.ui.search.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    private var mCurrentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()

    }

    private fun setupUI() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener(this)
        binding.bottomNavigationView.selectedItemId = R.id.tab_home
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var selectedFragment: Fragment? = null
        var selectedFragmentTag: String? = ""

        when (item.itemId) {
            R.id.tab_home -> {
                selectedFragment = HomeFragment.newInstance()
                selectedFragmentTag = HomeFragment.TAG
            }

            R.id.tab_search -> {
                selectedFragment = SearchFragment.newInstance()
                selectedFragmentTag = SearchFragment.TAG
            }
        }

        var fragment = supportFragmentManager.findFragmentByTag(selectedFragmentTag)

        mCurrentFragment?.takeIf { it.isVisible }?.let {
            hideFragment(it)
        }

        if (fragment == null) {
            fragment = selectedFragment
        }

        showFragment(fragment, selectedFragmentTag)

        mCurrentFragment = fragment

        return true
    }

    private fun showFragment(selectedFragment: Fragment?, selectedFragmentTag: String?) {
        if (selectedFragment == null || selectedFragmentTag == null) return

        val transaction = supportFragmentManager.beginTransaction()

        if (selectedFragment.isAdded) {
            transaction.show(selectedFragment)
        } else {
            transaction.add(R.id.fragmentContainer, selectedFragment, selectedFragmentTag)
        }

        transaction.commitNow()
    }

    private fun hideFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.hide(fragment)
        transaction.commitNow()
    }

}