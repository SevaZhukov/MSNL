package com.memebattle.msnl

import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.memebattle.goldextensions.log

class MSNavigation(private val msFragmentManager: MSFragmentManager) {
    private val orderOfStacks = arrayListOf<String>()
    private val mapOfStacks = hashMapOf<String, MutableList<Fragment>>()

    fun setupNavigation(bottomNavigationView: BottomNavigationView) {
        val menu = bottomNavigationView.menu
        for(i in 1 until menu.size()) {
            mapOfStacks[menu.findItem(i).title.toString()] = mutableListOf()
        }
        val title = menu.findItem(0).title.toString()
        mapOfStacks[title] = msFragmentManager.backStack
        orderOfStacks.add(title)
        log(mapOfStacks.toString())
        log(orderOfStacks.toString())
        bottomNavigationView.setOnNavigationItemSelectedListener { item -> onItemSelected(item, msFragmentManager) }
        msFragmentManager.addOnBackStackChangedListener {
            log("current backstack ${orderOfStacks.last()} ${msFragmentManager.backStack}")
        }
    }

    private fun onItemSelected(item: MenuItem, fragmentManager: MSFragmentManager): Boolean {
        log("onItemSelected")
        fragmentManager.backStack = mapOfStacks[item.title]!!
        fragmentManager.beginTransaction()
                .show(fragmentManager.fragments.last())
        val title = item.title.toString()
        orderOfStacks.remove(title)
        orderOfStacks.add(title)
        return true
    }
}