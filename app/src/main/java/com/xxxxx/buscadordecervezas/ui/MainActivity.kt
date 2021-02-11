package com.xxxxx.buscadordecervezas.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.xxxxx.buscadordecervezas.R
import com.xxxxx.buscadordecervezas.ui.beerlist.BeerListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainerView, BeerListFragment()).commit()
        }
    }
}