package day9

import java.io.FileInputStream
import java.util.*
import kotlin.collections.LinkedHashSet

data class MyNode(var other: Node = this)

data class Node(val value: Int, var _prev: Node? = null, val _next: Node? = null) {
    var prev: Node = _prev ?: this
    var next: Node = _next ?: this

    fun addAfter(nextValue: Int): Node {
        if(next == this) {
            val other = Node(nextValue, this, this)
            next = other
            prev = other
        }
        else {
            val after = Node(nextValue, this, next)
            next.prev = after
            next = after
        }
        return next
    }

    fun remove(): Node {
        prev.next = next
        next.prev = prev
        return next
    }

    fun next(steps: Int): Node {
        var next = this
        for(i in 1..steps) {
            next = next.next
        }
        return next
    }

    fun prev(steps: Int): Node {
        var prev = this
        for(i in 1..steps) {
            prev = prev.prev
        }
        return prev
    }

    fun print() {
        var cur = this
        while(cur.value != 0)
            cur = cur.next
        do {
            System.out.print(cur.value)
            System.out.print(" -> ")
            cur = cur.next
        } while (cur.value != 0)
    }
}


fun String.removeNonDigits(): String {
    return this.replace(Regex("[^\\d]"), "")
}

fun solve(marbleCount: Int, playerCount: Int): Long {
    val circle = Node(0)
    val score = LongArray(playerCount)
    var player = 0
    var current = circle
    for (i in 1 until marbleCount) {
        //current.print()
        //println()
        player = (player + 1) % playerCount
        if (i % 23 == 0) {
            current = current.prev(7)
            score[player] += (i + current.value).toLong()
            current = current.remove()
            continue
        }
        current = current.next(1).addAfter(i)
    }
    return score.max()!!
}

fun day9_1(): String {
    var playerString: String? = null
    var marbleString: String? = null
    Scanner(FileInputStream("day9.in")).use {
        playerString = it.findInLine(".+;").removeNonDigits()
        marbleString = it.nextLine().removeNonDigits()
    }

    val marbleCount = marbleString!!.toInt() + 1
    val playerCount = playerString!!.toInt()
    return solve(marbleCount, playerCount).toString()
}

fun day9_2(): String {
    var playerString: String? = null
    var marbleString: String? = null
    Scanner(FileInputStream("day9.in")).use {
        playerString = it.findInLine(".+;").removeNonDigits()
        marbleString = it.nextLine().removeNonDigits()
    }

    val marbleCount = marbleString!!.toInt() + 1
    val playerCount = playerString!!.toInt()
    return solve(marbleCount*100, playerCount).toString()
}