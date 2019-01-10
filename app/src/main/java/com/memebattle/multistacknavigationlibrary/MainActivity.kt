package com.memebattle.multistacknavigationlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.memebattle.msnl.MSFragmentManager
import com.memebattle.multistacknavigationlibrary.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val msFragmentManager = MSFragmentManager(supportFragmentManager)
        msFragmentManager.add(R.id.global_container, MainFragment())
    }
}
