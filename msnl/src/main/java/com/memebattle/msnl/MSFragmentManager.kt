package com.memebattle.msnl

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.memebattle.goldextensions.log

class MSFragmentManager(private val fragmentManager: FragmentManager) {

    var globalContainerId: Int = 0
    var localContainerId: Int = 0

    init {
        instance = this
    }

    companion object {
        lateinit var instance: MSFragmentManager
    }

    fun navigate(fragment: Fragment, args: Bundle?) {
        fragment.arguments = args
        fragmentManager.beginTransaction()
                .replace(localContainerId, fragment)
                .addToBackStack(getCurrentFragmentTag())
                .commit()
    }

    fun navigate(fragment: Fragment) {
        navigate(fragment, null)
    }

    fun replace(fragment: Fragment, args: Bundle?) {
        fragment.arguments = args
        fragmentManager.beginTransaction()
                .replace(localContainerId, fragment)
                .commit()
    }

    fun replace(fragment: Fragment) {
        replace(fragment, null)
    }

    fun navigateGlobal(fragment: Fragment, args: Bundle?) {
        fragment.arguments = args
        fragmentManager.beginTransaction()
                .replace(globalContainerId, fragment)
                .addToBackStack(getCurrentFragmentTag())
                .commit()
    }

    fun navigateGlobal(fragment: Fragment) {
        navigateGlobal(fragment, null)
    }

    fun replaceGlobal(fragment: Fragment, args: Bundle?) {
        fragment.arguments = args
        fragmentManager.beginTransaction()
                .replace(globalContainerId, fragment)
                .commit()
    }

    fun replaceGlobal(fragment: Fragment) {
        replaceGlobal(fragment, null)
    }

    fun add(containerId: Int, fragment: Fragment) {
        fragmentManager.beginTransaction()
                .add(containerId, fragment, fragment.tag)
                .commit()
    }

    private fun getCurrentFragmentTag(): String? {
        return null
    }

    fun addOnBackStackChangedListener(function: () -> Unit) {
        fragmentManager.addOnBackStackChangedListener {
            function()
        }
    }

    fun show() {
        fragmentManager.beginTransaction()
                .show(fragmentManager.fragments.last())
    }

    fun getBackStack(): MutableList<Fragment> {
        return fragmentManager.fragments
    }

    fun setBackStack(backStack: MutableList<Fragment>) {
        fragmentManager.fragments.clear()
        backStack.forEach {
            fragmentManager.fragments.add(it)
        }
    }


}