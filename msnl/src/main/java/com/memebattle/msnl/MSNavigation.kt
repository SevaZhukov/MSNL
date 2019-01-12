package com.memebattle.msnl

import android.view.MenuItem
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.memebattle.goldextensions.log

class MSNavigation(private val msFragmentManager: MSFragmentManager) {
    private val orderOfStacks = arrayListOf<String>()
    private val mapOfStacks = hashMapOf<String, MutableList<Fragment>>()
    private val mapOfStartFragments = hashMapOf<String, Fragment>()

    fun setupNavigation(bottomNavigationView: BottomNavigationView, fragments: ArrayList<Fragment>) {
        val menu = bottomNavigationView.menu
        val title = menu[0].title.toString()
        mapOfStacks[title] = msFragmentManager.getBackStack()
        orderOfStacks.add(title)
        for (i: Int in 1 until menu.size()) {
            val curTitle = menu[i].title.toString()
            mapOfStacks[curTitle] = mutableListOf()
            mapOfStartFragments[curTitle] = fragments[i]
        }
        msFragmentManager.add(fragments[0])
        bottomNavigationView.setOnNavigationItemSelectedListener { item -> onItemSelected(item) }
        msFragmentManager.addOnBackStackChangedListener {
            log("current backstack ${orderOfStacks.last()} ${msFragmentManager.getBackStack()}")
        }
    }

    private fun onItemSelected(item: MenuItem): Boolean {
        val prevItem = orderOfStacks.last()
        mapOfStacks[prevItem] = msFragmentManager.getBackStack()
        log("prev ${mapOfStacks[prevItem]}")
        if(mapOfStacks[item.title]!!.isEmpty()) {
            log("empty")
            msFragmentManager.navigate(mapOfStartFragments[item.title.toString()]!!)
        } else {
            log("full")
            msFragmentManager.setBackStack(mapOfStacks[item.title.toString()]!!)
        }
        val title = item.title.toString()
        orderOfStacks.remove(title)
        orderOfStacks.add(title)
        log("$orderOfStacks")
        return true
    }
}