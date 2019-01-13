package com.memebattle.multistacknavigationlibrary.main.fragment.friends


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.memebattle.multistacknavigationlibrary.R

class FriendsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_friends, container, false)
    }
}
