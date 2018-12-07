import java.io.FileInputStream
import java.util.*

data class Node(val id: Int, var dependencyCount: Int, val children: MutableList<Node>, var endTime: Int = 0) {
    fun addChild(child: Node) {
        ++child.dependencyCount
        children.add(child)
    }
}

fun day7_1(): String {
    val nodes = MutableList('Z'.minus('A') + 1, { Node(it, 0, mutableListOf()) })
    Scanner(FileInputStream("day7.in")).use { ios ->
        while (ios.hasNextLine()) {
            val line = ios.nextLine()
            val from = line[5].minus('A')
            val to = line[36].minus('A')
            nodes[from].addChild(nodes[to])
        }
    }
    val q = PriorityQueue<Node>({ lhs, rhs -> lhs.id.compareTo(rhs.id) })
    q.addAll(nodes.filter({ it.dependencyCount == 0 && it.children.size > 0 }))
    val ans = mutableListOf<Char>()
    while (!q.isEmpty()) {
        val top = q.remove()
        ans.add('A'.plus(top.id))
        top.children.forEach { child ->
            if (--child.dependencyCount == 0) {
                q.add(child)
            }
        }
    }
    return ans.joinToString("")
}

fun day7_2(): String {
    val workerCount = 5
    val extraTime = 60
    val nodes = MutableList('Z'.minus('A') + 1, { Node(it, 0, mutableListOf()) })
    Scanner(FileInputStream("day7.in")).use { ios ->
        while (ios.hasNextLine()) {
            val line = ios.nextLine()
            val from = line[5].minus('A')
            val to = line[36].minus('A')
            nodes[from].addChild(nodes[to])
        }
    }
    val workSchedule = IntArray(workerCount)
    val schedule = PriorityQueue<Node>({ lhs, rhs ->
        if (lhs.endTime == rhs.endTime)
            lhs.id.compareTo(rhs.id)
        else
            lhs.endTime.compareTo(rhs.endTime)
    })
    schedule.addAll(nodes.filter({ it.dependencyCount == 0 && it.children.size > 0 }))
    var i = 0
    while (schedule.isNotEmpty()) {
        val node = schedule.peek()
        if (node.endTime > i) {
            i += 1
            continue
        }
        val worker = workSchedule.indexOfFirst({ it <= i })
        if (worker == -1) {
            i += 1
            continue
        }
        schedule.remove()
        val endTime = i + extraTime + node.id + 1
        workSchedule[worker] = endTime
        for (child in node.children) {
            --child.dependencyCount
            child.endTime = Math.max(child.endTime, endTime)
            if (child.dependencyCount == 0) {
                schedule.add(child)
            }
        }
    }
    return workSchedule.max().toString()
}