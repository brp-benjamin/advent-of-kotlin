import java.io.FileInputStream
import java.util.*

fun isReactive(a: Char, b: Char): Boolean {
    if(a.toLowerCase() == b.toLowerCase())
        return a != b
    return false
}

fun react(trimChar: Char? = null): String {
    val ios = Scanner(FileInputStream("day5.in"))
    var input = ""
    ios.use {
        input = ios.nextLine()
    }
    if(trimChar != null) {
        input = input.replace(trimChar.toUpperCase().toString(), "")
        input = input.replace(trimChar.toLowerCase().toString(), "")
    }
    var i= 1
    while(i < input.length) {
        if(!isReactive(input[i], input[i-1])) {
            ++i
        }
        else {
            input = input.removeRange(i - 1, i + 1);
            i = if(i > 1) i-1 else 1
        }
    }
    return input
}

fun day5_1(): String {
    return react().length.toString()
}

fun day5_2(): String {
    var shortest = 1e9.toInt()
    for(c in 'a'.rangeTo('z')) {
        var length = react(c).length
        if(length < shortest) {
            shortest = length
        }
    }
    return shortest.toString()
}