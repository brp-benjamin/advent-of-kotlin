fun main(args: Array<String>) {
    var solutions: Array<() -> String> = arrayOf({ day1_1() }, { day1_2() }, { day2_1() }, { day2_2() }, { day3_1() }, { day3_2() })
    solutions.forEachIndexed { index, solution ->
        println("Solution for day %d problem %d".format(index / 2 + 1, index % 2 + 1))
        println(solution())
    }
}