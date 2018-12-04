import java.io.FileInputStream
import java.util.*

const val undefined = -1

fun day4_1(): String {
    var sleepMap = makeSleepMap()

    var sleepiestGuard = undefined
    var sleepiestSum = undefined
    var sleepiestMinute = undefined
    for ((guard, sleepLog) in sleepMap) {
        var sum = sleepLog.sum()
        if (sum > sleepiestSum) {
            sleepiestGuard = guard
            sleepiestSum = sum
            sleepiestMinute = sleepLog.indices.maxBy { sleepLog[it] } ?: undefined
        }
    }

    return (sleepiestGuard * sleepiestMinute).toString()
}

fun day4_2(): String {
    var sleepMap = makeSleepMap()
    var mostSleptVal = undefined
    var mostSleptMinute = undefined
    var mostSleptGuard = undefined
    for((guard, sleepLog) in sleepMap) {
        var mostSlept = sleepLog.max() ?: undefined
        if(mostSlept <= mostSleptVal) {
            continue
        }
        mostSleptVal = mostSlept
        mostSleptGuard = guard
        mostSleptMinute = sleepLog.indexOf(mostSlept)
    }
    return (mostSleptMinute * mostSleptGuard).toString()
}

private fun makeSleepMap(): Map<Int, IntArray> {
    val ios = Scanner(FileInputStream("day4.in"))
    val inputRows = mutableListOf<String>()
    while (ios.hasNextLine()) {
        inputRows.add(ios.nextLine())
    }
    inputRows.sort()
    val map = mutableMapOf<Int, IntArray>()
    var currentGuard = undefined
    var asleepSince = undefined
    var sleepLog = IntArray(0)
    for (row in inputRows) {
        if (row.contains('#')) {
            currentGuard = row.substring(25)
                    .trim({ c -> !c.isDigit() })
                    .toInt()
            if (!map.containsKey(currentGuard)) {
                map[currentGuard] = IntArray(60)
            }
            sleepLog = map[currentGuard] ?: IntArray(60)
            continue
        }
        val minute = row.substring(range = 15..16).toInt()
        if (asleepSince == undefined) {
            asleepSince = minute
        } else {
            for (i in asleepSince until minute) {
                ++sleepLog[i]
            }
            asleepSince = undefined
        }
    }
    return map
}