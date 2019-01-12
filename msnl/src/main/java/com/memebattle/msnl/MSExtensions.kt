package com.memebattle.msnl

fun <T> ArrayList<in T>.addStack(element: T) {
    remove(element)
    add(element)
}