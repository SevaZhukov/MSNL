package com.memebattle.multistacknavigationlibrary.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.memebattle.msnl.MSFragmentManager
import com.memebattle.msnl.MSNavigation
import com.memebattle.multistacknavigationlibrary.R
import com.memebattle.multistacknavigationlibrary.main.fragment.FirstFragment
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val msFragmentManager = MSFragmentManager.instance
        msFragmentManager.add(R.id.local_container, FirstFragment())
        msFragmentManager.globalContainerId = R.id.global_container
        msFragmentManager.localContainerId = R.id.local_container
        val msNavigation = MSNavigation(msFragmentManager)
        msNavigation.setupNavigation(view.bottomNavigationView)
    }
}