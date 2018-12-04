import java.io.FileInputStream
import java.util.*

fun day3_2(): String {
    var ios = Scanner(FileInputStream("day3.in"))
    var claimMap = Array(1000, { IntArray(1000) })
    var unclaimed = 0
    var valid = mutableSetOf<Int>()
    while (ios.hasNextLine()) {
        var claim = ios.nextLine()
                .replace("#", "")
                .replace(" @", "")
                .replace(",", " ")
                .replace(":", "")
                .replace("x", " ")
        var ios2 = Scanner(claim)
        var id = ios2.nextInt()
        valid.add(id)
        var x0 = ios2.nextInt()
        var y0 = ios2.nextInt()
        var w = ios2.nextInt()
        var h = ios2.nextInt()
        for (dx in 0 until w) {
            for (dy in 0 until h) {
                var x = x0 + dx
                var y = y0 + dy
                if(claimMap[x][y] != unclaimed) {
                    valid.remove(id)
                    valid.remove(claimMap[x][y])
                }
                else {
                    claimMap[x][y] = id
                }
            }
        }
    }
    return valid.first().toString()
}

fun day3_1(): String {
    var ios = Scanner(FileInputStream("day3.in"))
    var claimCount = Array(1000, { IntArray(1000) })
    while (ios.hasNextLine()) {
        var claim = ios.nextLine()
                .replace("#", "")
                .replace(" @", "")
                .replace(",", " ")
                .replace(":", "")
                .replace("x", " ")
        var ios2 = Scanner(claim)
        var id = ios2.nextInt()
        var x0 = ios2.nextInt()
        var y0 = ios2.nextInt()
        var w = ios2.nextInt()
        var h = ios2.nextInt()
        for (dx in 0 until w) {
            for (dy in 0 until h) {
                var x = x0 + dx
                var y = y0 + dy
                ++claimCount[x][y]
            }
        }
    }
    var collisions = 0
    claimCount.forEach { column -> column.forEach { cell -> if (cell > 1) ++collisions } }
    return collisions.toString()
}