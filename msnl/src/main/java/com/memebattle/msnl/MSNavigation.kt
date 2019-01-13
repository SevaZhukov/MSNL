package com.memebattle.msnl

import android.view.Menu
import android.view.MenuItem
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.memebattle.goldextensions.log

class MSNavigation(private val msFragmentManager: MSFragmentManager) {

    private val orderOfStacks = arrayListOf<MenuItem>()
    private val mapOfStacks = hashMapOf<String, MutableList<Fragment>>()
    private val mapOfStartFragments = hashMapOf<String, Fragment>()

    lateinit var bottomNavigationView: BottomNavigationView

    fun setupNavigation(bottomNavigationView: BottomNavigationView, fragments: ArrayList<Fragment>) {
        this.bottomNavigationView = bottomNavigationView
        val menu = bottomNavigationView.menu
        val title = menu[0].title.toString()
        mapOfStacks[title] = msFragmentManager.getBackStack()
        orderOfStacks.add(menu[0])
        for (i: Int in 1 until menu.size()) {
            val curTitle = menu[i].title.toString()
            mapOfStacks[curTitle] = mutableListOf()
            mapOfStartFragments[curTitle] = fragments[i]
        }
        msFragmentManager.add(fragments[0])
        mapOfStartFragments[menu[0].title.toString()] = fragments[0]
        bottomNavigationView.setOnNavigationItemSelectedListener { item -> onItemSelected(item) }
    }

    private fun onItemSelected(item: MenuItem): Boolean {
        val prevItem = orderOfStacks.last().title.toString()
        mapOfStacks[prevItem] = msFragmentManager.getBackStack()
        if (mapOfStacks[item.title]!!.isEmpty()) {
            msFragmentManager.navigate(mapOfStartFragments[item.title.toString()]!!)
        } else {
            msFragmentManager.setBackStack(mapOfStacks[item.title.toString()]!!)
        }
        orderOfStacks.addStack(item)
        item.order
        return true
    }

    fun onBackPressed() {
        log("on back press msnav ${msFragmentManager.getBackStack()}")
        log("${msFragmentManager.getBackStack().last()}")
        log("${mapOfStartFragments[orderOfStacks.last().title.toString()]}")
        val startFragmentOfThisStack = mapOfStartFragments[orderOfStacks.last().title.toString()]
        val currentFragment = msFragmentManager.getBackStack().last()
        if(startFragmentOfThisStack == currentFragment) {
            log("==")
            log("$orderOfStacks")
            orderOfStacks.removeLast()
            log("$orderOfStacks")
            bottomNavigationView.menu[orderOfStacks.last().title.toString()]!!.isChecked = true
            msFragmentManager.pop()
        }
    }

}
