import java.io.FileInputStream
import java.util.*

fun day1_1(): String {
    var ios = Scanner(FileInputStream("day1.in"))
    var result = 0
    while (ios.hasNextInt()) {
        result += ios.nextInt()
    }
    return result.toString()
}

fun day1_2(): String {
    var ios = Scanner(FileInputStream("day1.in"))
    var sequence = arrayListOf<Int>()
    while (ios.hasNextInt()) {
        sequence.add(ios.nextInt())
    }
    var visited = mutableSetOf<Int>()
    var current = 0
    var index = 0
    while(visited.add(current)) {
        current += sequence[index]
        index = (index + 1) % sequence.size
    }
    return current.toString()
}