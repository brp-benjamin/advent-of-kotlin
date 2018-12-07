import java.io.FileInputStream
import java.util.*
import kotlin.collections.HashMap

data class Pt(val x: Int, val y: Int)

fun makeMap(): Pair<Array<IntArray>, Array<Pt>> {
    val pts = mutableListOf<Pt>()
    var maxX = 0
    var maxY = 0

    Scanner(FileInputStream("day6.in")).use { ios ->
        var i = 0
        while (ios.hasNextLine()) {
            var line = ios.nextLine()
            line = line.replace(", ", " ")
            Scanner(line).use { ios2 ->
                val x = ios2.nextInt()
                val y = ios2.nextInt()
                pts.add(Pt(x, y))
                maxX = Math.max(x, maxX)
                maxY = Math.max(y, maxY)
            }
            ++i
        }
    }

    val xRange = IntRange(0, maxX + 1)
    val yRange = IntRange(0, maxY + 1)
    val idMap = Array(xRange.last + 1, { IntArray(yRange.last + 1, { -1 }) })
    val valMap = Array(xRange.last + 1, { IntArray(yRange.last + 1, { 0 }) })

    for (x in xRange) {
        for (y in yRange) {
            for (i in pts.indices) {
                val pt = pts[i]
                val dist = Math.abs(x - pt.x) + Math.abs(y - pt.y)
                when {
                    idMap[x][y] == -1 -> {
                        idMap[x][y] = i
                        valMap[x][y] = dist
                    }
                    dist < valMap[x][y] -> {
                        idMap[x][y] = i
                        valMap[x][y] = dist
                    }
                    dist == valMap[x][y] -> idMap[x][y] = -2
                }
            }
        }
    }

    return Pair(idMap, pts.toTypedArray())
}

fun day6_2(): String {

    val pts = mutableListOf<Pt>()
    var maxX = 0
    var maxY = 0

    Scanner(FileInputStream("day6.in")).use { ios ->
        var i = 0
        while (ios.hasNextLine()) {
            var line = ios.nextLine()
            line = line.replace(", ", " ")
            Scanner(line).use { ios2 ->
                val x = ios2.nextInt()
                val y = ios2.nextInt()
                pts.add(Pt(x, y))
                maxX = Math.max(x, maxX)
                maxY = Math.max(y, maxY)
            }
            ++i
        }
    }

    val xRange = IntRange(0, maxX + 1)
    val yRange = IntRange(0, maxY + 1)
    var count = 0
    for(x in xRange) {
        for(y in yRange) {
            var sum = 0
            for(pt in pts) {
                sum += Math.abs(pt.x - x) + Math.abs(pt.y - y)
            }
            if(sum >= 10000)
                continue
            ++count
        }
    }

    return count.toString()
}

fun day6_1(): String {
    val ret = makeMap()
    val map = ret.first
    val invalids = mutableSetOf<Int>()
    val res = HashMap<Int, Int>()
    val xRange = map.indices
    val yRange = map[0].indices
    for (y in yRange) {
        for (x in xRange) {
            val cell = map[x][y]
            if (cell < 0)
                continue
            if(x == 0 || y == 0 || x == xRange.last || y == yRange.last)
                invalids.add(cell)
            res[cell] = (res[cell] ?: 0) + 1
        }
    }

    for (invalid in invalids) {
        res.remove(invalid)
    }

    return res.values.max().toString()
}