package com.memebattle.multistacknavigationlibrary.main

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.memebattle.goldextensions.log
import com.memebattle.msnl.IOnBackPressed
import com.memebattle.msnl.MSFragmentManager
import com.memebattle.msnl.MSNavigation
import com.memebattle.multistacknavigationlibrary.R
import com.memebattle.multistacknavigationlibrary.main.fragment.FirstFragment
import com.memebattle.multistacknavigationlibrary.main.fragment.SecondFragment
import com.memebattle.multistacknavigationlibrary.main.fragment.ThirdFragment
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : Fragment(), IOnBackPressed {

    lateinit var msNavigation: MSNavigation

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val msFragmentManager = MSFragmentManager.instance
        msFragmentManager.globalContainerId = R.id.global_container
        msFragmentManager.localContainerId = R.id.local_container
        msNavigation = MSNavigation(msFragmentManager)
        val fragments = arrayListOf(FirstFragment(), SecondFragment(), ThirdFragment())
        msNavigation.setupNavigation(view.bottomNavigationView, fragments)
    }

    override fun onBackPressed(): Boolean {
        log("on back press fr")
        msNavigation.onBackPressed()
        return true
    }
}