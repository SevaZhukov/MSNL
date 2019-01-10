package com.memebattle.msnl

import android.view.MenuItem
import androidx.core.view.get
import androidx.core.view.size
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.memebattle.goldextensions.log

class MSNavigation(private val msFragmentManager: MSFragmentManager) {
    private val orderOfStacks = arrayListOf<String>()
    private val mapOfStacks = hashMapOf<String, MutableList<Fragment>>()

    fun setupNavigation(bottomNavigationView: BottomNavigationView) {
        val menu = bottomNavigationView.menu
        log("setupNavigation ${menu.size}")
        val title = menu[0].title.toString()
        mapOfStacks[title] = msFragmentManager.backStack
        orderOfStacks.add(title)
        for (i: Int in 1 until menu.size()) {
            log("setupNavigation i $i")
            mapOfStacks[menu[i].title.toString()] = mutableListOf()
        }
        log(mapOfStacks.toString())
        log(orderOfStacks.toString())
        bottomNavigationView.setOnNavigationItemSelectedListener { item -> onItemSelected(item) }
        msFragmentManager.addOnBackStackChangedListener {
            log("current backstack ${orderOfStacks.last()} ${msFragmentManager.backStack}")
        }
    }

    private fun onItemSelected(item: MenuItem): Boolean {
        log("onItemSelected")
        log("${msFragmentManager.backStack}")
        msFragmentManager.backStack = mapOfStacks[item.title]!!
        log("${msFragmentManager.backStack}")
        msFragmentManager.replace(msFragmentManager.backStack.last())
        val title = item.title.toString()
        orderOfStacks.remove(title)
        orderOfStacks.add(title)
        return true
    }
}