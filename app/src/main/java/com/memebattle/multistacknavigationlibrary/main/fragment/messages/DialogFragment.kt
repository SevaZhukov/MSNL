package com.memebattle.multistacknavigationlibrary.main.fragment.messages


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.memebattle.multistacknavigationlibrary.R

class DialogFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_dialog, container, false)
        return v
    }
}
