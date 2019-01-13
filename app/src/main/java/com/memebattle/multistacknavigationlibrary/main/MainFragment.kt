package com.memebattle.multistacknavigationlibrary.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.memebattle.msnl.IOnBackPressed
import com.memebattle.msnl.MSNavigation
import com.memebattle.msnl.msFragmentManager
import com.memebattle.multistacknavigationlibrary.R
import com.memebattle.multistacknavigationlibrary.main.fragment.news.NewsFragment
import com.memebattle.multistacknavigationlibrary.main.fragment.messages.MessagesFragment
import com.memebattle.multistacknavigationlibrary.main.fragment.friends.FriendsFragment
import com.memebattle.multistacknavigationlibrary.main.fragment.profile.ProfileFragment
import kotlinx.android.synthetic.main.fragment_main.view.*

class MainFragment : Fragment(), IOnBackPressed {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        msFragmentManager.localContainerId = R.id.local_container
        val fragments = arrayListOf(NewsFragment(), MessagesFragment(), FriendsFragment(), ProfileFragment())
        MSNavigation.setupNavigation(msFragmentManager, view.bottomNavigationView, fragments)
    }

    override fun onBackPressed(): Boolean {
        MSNavigation.onBackPressed()
        return true
    }
}