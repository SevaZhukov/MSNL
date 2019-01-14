package com.memebattle.msnl

import android.view.MenuItem
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

abstract class MSNavigation {

    companion object {
        lateinit var msFragmentManager: MSFragmentManager

        private val orderOfStacks = arrayListOf<MenuItem>()
        private val mapOfStacks = hashMapOf<String, MutableList<Fragment>>()
        private val mapOfStartFragments = hashMapOf<String, Fragment>()

        lateinit var bottomNavigationView: BottomNavigationView

        fun setupNavigation(msFragmentManager: MSFragmentManager, bottomNavigationView: BottomNavigationView, fragments: ArrayList<Fragment>) {
            this.bottomNavigationView = bottomNavigationView
            this.msFragmentManager = msFragmentManager
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
            val startFragmentOfThisStack = mapOfStartFragments[orderOfStacks.last().title.toString()]
            val currentFragment = msFragmentManager.getBackStack().last()
            if (startFragmentOfThisStack == currentFragment) {
                if (orderOfStacks.size == 1) {
                    msFragmentManager.exit()
                } else {
                    orderOfStacks.removeLast()
                    bottomNavigationView.menu[orderOfStacks.last().title.toString()]!!.isChecked = true
                    msFragmentManager.setBackStack(mapOfStacks[orderOfStacks.last().title.toString()]!!)
                }
            } else {
                msFragmentManager.back()
            }
        }
    }
}
