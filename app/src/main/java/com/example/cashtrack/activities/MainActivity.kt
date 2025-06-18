package com.example.cashtrack.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.cashtrack.R
import com.example.cashtrack.SavingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.cashtrack.fragments.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Load default fragment
        loadFragment(DashboardFragment())

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_dashboard -> {
                    loadFragment(DashboardFragment())
                    true
                }
                R.id.nav_tracking -> {
                    loadFragment(TrackingFragment())
                    true
                }
                R.id.nav_savings -> {
                    loadFragment(SavingsFragment())
                    true
                }
                R.id.nav_payroll -> {
                    loadFragment(PayrollFragment())
                    true
                }
                R.id.nav_more -> {
                    loadFragment(MoreFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
