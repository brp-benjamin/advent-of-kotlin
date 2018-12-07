import java.io.FileInputStream
import java.util.*

fun isReactive(a: Char, b: Char): Boolean {
    if (a.toLowerCase() == b.toLowerCase())
        return a != b
    return false
}

fun react(trimChar: Char? = null): String {
    var input = Scanner(FileInputStream("day5.in")).use { ios -> ios.nextLine() }
    if (trimChar != null) {
        input = input.replace(trimChar.toUpperCase().toString(), "", true)
    }
    val output = input.toCharArray()
    var i = 1
    var j = 0
    while (i < input.length) {
        if (!isReactive(output[j], input[i])) {
            output[++j] = input[i++]
        } else {
            if(j > 0) {
                i += 1
                j -= 1
            }
            else {
                output[0] = input[i+1]
                j = 0
                i += 2
            }
        }
    }
    return output.sliceArray(IntRange(0, j)).joinToString("")
}

fun day5_1(): String {
    return react().length.toString()
}

fun day5_2(): String {
    var shortest = 1e9.toInt()
    for (c in 'a'.rangeTo('z')) {
        var length = react(c).length
        if (length < shortest) {
            shortest = length
        }
    }
    return shortest.toString()
}