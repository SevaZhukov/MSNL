package com.memebattle.multistacknavigationlibrary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.memebattle.msnl.IOnBackPressed
import com.memebattle.msnl.MSFragmentManager
import com.memebattle.multistacknavigationlibrary.main.MainFragment

class MainActivity : AppCompatActivity() {

    private lateinit var msFragmentManager: MSFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        msFragmentManager = MSFragmentManager(supportFragmentManager)
        msFragmentManager.globalContainerId = R.id.global_container
        msFragmentManager.addGlobal(MainFragment())
    }

    override fun onBackPressed() {
        val fragment = this.supportFragmentManager.findFragmentById(R.id.global_container)
        (fragment as? IOnBackPressed)?.onBackPressed()
    }
}
