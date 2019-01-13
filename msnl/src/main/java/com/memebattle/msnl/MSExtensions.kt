package com.memebattle.msnl

import android.view.Menu
import android.view.MenuItem
import androidx.core.view.children
import androidx.fragment.app.Fragment

fun <T> ArrayList<in T>.addStack(element: T) {
    remove(element)
    add(element)
}

fun <T> ArrayList<in T>.removeLast() {
    removeAt(lastIndex)
}

operator fun Menu.get(title: String): MenuItem? {
    children.forEach {
        if (it.title.toString() == title) {
            return it
        }
    }
    return null
}

var Fragment.msFragmentManager: MSFragmentManager
    get() = MSFragmentManager.instance
    set(value) {

    }