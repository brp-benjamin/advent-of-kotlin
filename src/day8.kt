import java.io.FileInputStream
import java.util.*
import kotlin.collections.ArrayList

data class Node(val children: ArrayList<Node> = ArrayList(), val metadata: ArrayList<Int> = ArrayList())

fun parseInput(ios: Scanner): Node {
    val childCount = ios.nextInt()
    val metadataCount = ios.nextInt()
    val ret = Node()
    for (i in 1..childCount) {
        ret.children.add(parseInput(ios))
    }

    for (i in 1..metadataCount) {
        ret.metadata.add(ios.nextInt())
    }
    return ret
}

fun metadataSum(node: Node): Int {
    return node.metadata.sum() + node.children.asSequence().map({ it -> metadataSum(it) }).sum()
}

fun calcValue(node: Node): Int {
    if (node.children.isEmpty()) {
        return node.metadata.sum()
    }
    return node.metadata.asSequence()
            .map({ node.children.getOrNull(it - 1) })
            .filterNotNull()
            .map({ calcValue(it) })
            .sum()
}

fun day8_1(): String {
    return Scanner(FileInputStream("day8.in")).use { metadataSum(parseInput(it)) }.toString()
}

fun day8_2(): String {
    return Scanner(FileInputStream("day8.in")).use { calcValue(parseInput(it)) }.toString()
}