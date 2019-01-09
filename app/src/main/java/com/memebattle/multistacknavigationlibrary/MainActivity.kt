package com.memebattle.multistacknavigationlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.memebattle.msnl.add
import com.memebattle.multistacknavigationlibrary.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.add(MainFragment())
    }
}
