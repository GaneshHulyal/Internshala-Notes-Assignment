package com.ganeshhulyal.notesapp.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ganeshhulyal.notesapp.R
import com.ganeshhulyal.notesapp.constants.Constants
import com.ganeshhulyal.notesapp.fragments.HomeFragment
import com.ganeshhulyal.notesapp.fragments.UserLoginFragment
import com.ganeshhulyal.notesapp.repo.SharedPrefsManager
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    private lateinit var sharedPrefsManager: SharedPrefsManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedPrefsManager = SharedPrefsManager((this))
        FirebaseApp.initializeApp(this)
        initViews()
    }

    private fun initViews() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, HomeFragment(), Constants.LOGIN_FRAGMENT)
                .commit()
        } else {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, UserLoginFragment(), Constants.LOGIN_FRAGMENT)
                .commit()
        }
    }


}