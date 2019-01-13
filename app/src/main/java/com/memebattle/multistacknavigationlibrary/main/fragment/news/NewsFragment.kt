package com.memebattle.multistacknavigationlibrary.main.fragment.news


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.memebattle.msnl.MSFragmentManager
import com.memebattle.multistacknavigationlibrary.R
import kotlinx.android.synthetic.main.fragment_news.view.*

class NewsFragment : Fragment() {

    lateinit var msFragmentManager: MSFragmentManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_news, container, false)
        msFragmentManager = MSFragmentManager.instance
        v.button.setOnClickListener {
            msFragmentManager.navigate(PostFragment())
        }
        return v
    }
}
