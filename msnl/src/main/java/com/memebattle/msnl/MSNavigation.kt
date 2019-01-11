package com.memebattle.msnl

import android.view.MenuItem
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.memebattle.goldextensions.log

class MSNavigation(private val msFragmentManager: MSFragmentManager) {
    private val orderOfStacks = arrayListOf<String>()
    private val mapOfStacks = hashMapOf<String, MutableList<Fragment>>()

    fun setupNavigation(bottomNavigationView: BottomNavigationView) {
        val menu = bottomNavigationView.menu
        val title = menu[0].title.toString()
        mapOfStacks[title] = msFragmentManager.getBackStack()
        orderOfStacks.add(title)
        for (i: Int in 1 until menu.size()) {
            mapOfStacks[menu[i].title.toString()] = mutableListOf()
        }
        bottomNavigationView.setOnNavigationItemSelectedListener { item -> onItemSelected(item) }
        msFragmentManager.addOnBackStackChangedListener {
            log("current backstack ${orderOfStacks.last()} ${msFragmentManager.getBackStack()}")
        }
    }

    private fun onItemSelected(item: MenuItem): Boolean {
        msFragmentManager.setBackStack(mapOfStacks[item.title]!!)
        msFragmentManager.replace(msFragmentManager.getBackStack().last())
        val title = item.title.toString()
        orderOfStacks.remove(title)
        orderOfStacks.add(title)
        return true
    }
}