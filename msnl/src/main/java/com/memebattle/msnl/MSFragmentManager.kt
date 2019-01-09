package com.memebattle.msnl

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

abstract class MSFragmentManager : FragmentManager() {
    var globalContainerId: Int = 0
    var localContainerId: Int = 0

    var backStack: MutableList<Fragment> = fragments

    fun navigate(fragment: Fragment, args: Bundle?) {
        fragment.arguments = args
        beginTransaction()
                .replace(localContainerId, fragment)
                .addToBackStack(getCurrentFragment().tag)
                .commit()
    }

    fun navigate(fragment: Fragment) {
        navigate(fragment, null)
    }

    fun replace(fragment: Fragment, args: Bundle?) {
        fragment.arguments = args
        beginTransaction()
                .replace(localContainerId, fragment)
                .commit()
    }

    fun replace(fragment: Fragment) {
        replace(fragment, null)
    }

    fun navigateGlobal(fragment: Fragment, args: Bundle?) {
        fragment.arguments = args
        beginTransaction()
                .replace(globalContainerId, fragment)
                .addToBackStack(getCurrentFragment().tag)
                .commit()
    }

    fun navigateGlobal(fragment: Fragment) {
        navigateGlobal(fragment, null)
    }

    fun replaceGlobal(fragment: Fragment, args: Bundle?) {
        fragment.arguments = args
        beginTransaction()
                .replace(globalContainerId, fragment)
                .commit()
    }

    fun replaceGlobal(fragment: Fragment) {
        replaceGlobal(fragment, null)
    }

    fun add(fragment: Fragment) {
        beginTransaction()
                .add(fragment, fragment.tag)
                .commit()
    }

    private fun getCurrentFragment(): Fragment {
        return backStack.last()
    }
}