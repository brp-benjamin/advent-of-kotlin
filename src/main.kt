import day7.day7_1
import day7.day7_2
import day9.day9_1
import day9.day9_2

fun main(args: Array<String>) {
    val solutions: Array<() -> String> = arrayOf({ day1_1() }, { day1_2() }, { day2_1() }, { day2_2() }, { day3_1() }, { day3_2() }, { day4_1() }, { day4_2() }, { day5_1() }, { day5_2() }, { day6_1() }, { day6_2() }, { day7_1() }, { day7_2() }, { day8_1() }, { day8_2() }, { day9_1() }, { day9_2() })
    solutions.forEachIndexed { index, solution ->
        println("Solution for day %d problem %d".format(index / 2 + 1, index % 2 + 1))
        println(solution())
    }
}