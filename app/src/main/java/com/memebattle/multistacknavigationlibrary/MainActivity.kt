package com.memebattle.multistacknavigationlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import com.memebattle.goldextensions.log
import com.memebattle.msnl.IOnBackPressed
import com.memebattle.msnl.MSFragmentManager
import com.memebattle.multistacknavigationlibrary.main.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var msFragmentManager: MSFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        msFragmentManager = MSFragmentManager(supportFragmentManager)
        msFragmentManager.add(R.id.global_container, MainFragment())
    }

    override fun onBackPressed() {
        log("on back press act")
        val fragment = this.supportFragmentManager.findFragmentById(R.id.global_container)
        (fragment as? IOnBackPressed)?.onBackPressed()?.not()?.let {
            //super.onBackPressed()
        }
    }
}
