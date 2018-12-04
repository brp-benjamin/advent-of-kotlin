import java.io.FileInputStream
import java.util.*

fun day2_1(): String {
    var ios = Scanner(FileInputStream("day2.in"))
    var tripCount = 0
    var dupCount = 0
    while (ios.hasNextLine()) {
        var line = ios.nextLine()
        var alphabet = IntArray('z'.minus('a') + 1)
        line.forEach { c ->
            ++alphabet[c.minus('a')]
        }
        var hasDuplicate = false
        var hasTriplicate = false
        alphabet.forEach { count ->
            if (count == 2)
                hasDuplicate = true
            if (count == 3)
                hasTriplicate = true
        }

        if (hasDuplicate)
            ++dupCount
        if (hasTriplicate)
            ++tripCount
    }
    return (dupCount * tripCount).toString()
}

fun day2_2(): String {
    var ios = Scanner(FileInputStream("day2.in"))
    var lines = arrayListOf<String>()
    while (ios.hasNextLine())
        lines.add(ios.nextLine())
    var unset = -1
    var invalid = -2
    for(i in 0 until lines.size) {
        var line1 = lines[i]
        for(j in i+1 until lines.size) {
            var line2 = lines[j]
            var diffIndex = unset
            for(k in 0 until line1.length) {
                if (line1[k] == line2[k])
                        continue
                diffIndex = if (diffIndex == unset) k else invalid
            }
            if(diffIndex != invalid && diffIndex != unset) {
                return line1.substring(0, diffIndex) + line1.substring(diffIndex+1)
            }
        }
    }
    return "unknown"
}