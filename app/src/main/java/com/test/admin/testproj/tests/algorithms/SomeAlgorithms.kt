package com.test.admin.testproj.tests.algorithms

import com.test.admin.testproj.tests.algorithms.trees.Node


/**
 * Message, containing letters A, B, ..., Z, can be encoded like this:
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 *
 * @param s - contains only digits from 1 to 9.
 * @return number of ways to decode the string
 * e.g. "12"  - function will return 2 ("AB"(1 2); "L"(12))
 *      "226" - function will return 3 ("BZ"(2 26); "VF"(22 6); "BBF"(2 2 6))
 */
const val MAX = 26
fun decode(s: String): Int {
    if (s.length == 1) {
        return 1
    }
    val firstDigit = s.substring(0, 1).toInt()
    val secondDigit = s.substring(1, 2).toInt()
    val firstTwoSignDigit = s.substring(0..1).toInt()

    val firstTreeRoot: Node? = if (secondDigit != 0) Node(firstDigit) else null
    val secondTreeRoot: Node? = if (firstTwoSignDigit <= MAX) Node(firstTwoSignDigit) else null

    firstTreeRoot?.let { decodeRecursive(it, s, 1) }
    secondTreeRoot?.let { decodeRecursive(it, s, 2) }

    return getLeafCount(firstTreeRoot) + getLeafCount(secondTreeRoot)
}

fun decodeRecursive(node: Node, s: String, i: Int) {
    if(i >= s.length) {
        return
    }
    val digit = s.substring(i, i + 1).toInt()
    if (digit != 0) {
        node.left = Node(digit)
        decodeRecursive(node.left!!, s, i + 1)
    }

    if(i + 1 >= s.length) {
        return
    }

    val twoSignDigit = s.substring(i..i + 1).toInt()
    if (twoSignDigit <= MAX) {
        node.right = Node(twoSignDigit)
        decodeRecursive(node.right!!, s, i + 2)
    }
}

fun getLeafCount(node: Node?): Int {
    if (node == null) {
        return 0
    }
    return if (node.left == null && node.right == null) 1
           else getLeafCount(node.left) + getLeafCount(node.right)
}