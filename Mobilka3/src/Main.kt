fun main(args: Array<String>) {
    println("Для выполнения 1 задания введите 1, для выполнения второго 2, для третьего 3")
    val cont = readLine()!!.toInt()
    if (cont == 1) {
        mas1()
    } else if (cont == 2) {
        mas2()
    } else if (cont == 3) {
        mas3()
    } else {
        print("вы ошиблись")
    }
}

class Time1(private var hours: Int, private var minutes: Int) {

    init {
        if (minutes < 0 || minutes >= 60) {
            throw IllegalArgumentException("Минуты должны быть от 0 до 59")
        }
    }

    fun toStringInfo(): String {
        return "Время: $hours часов и $minutes минут"
    }
    fun calculateTotalMinutes(): Int {
        return (hours * 60) + minutes
    }
}

fun mas1() {
    println("Введите количество часов:")
    val hoursInput = readLine()?.toIntOrNull() ?: 0

    println("Введите количество минут:")
    val minutesInput = readLine()?.toIntOrNull() ?: 0

    val time = Time1(hoursInput, minutesInput)
    println(time.toStringInfo())

    val totalMinutes = time.calculateTotalMinutes()
    println("Общее количество минут: $totalMinutes")
}

open class Time2(private var hours: Int, private var minutes: Int) {

    init {
        if (minutes < 0 || minutes >= 60) {
            throw IllegalArgumentException("Минуты должны быть от 0 до 59")
        }
    }

    open fun toStringInfo(): String {
        return "Время: $hours часов и $minutes минут"
    }

    fun calculateTotalMinutes(): Int {
        return (hours * 60) + minutes
    }
}

class OperationTime(hours: Int, minutes: Int, private var operationDuration: Int) : Time2(hours, minutes) {

    init {
        if (operationDuration <= 0) {
            throw IllegalArgumentException("Длительность операции должна быть положительным числом.")
        }
    }

    fun calculateOperations(): Int {
        val totalMinutes = calculateTotalMinutes()
        return totalMinutes / operationDuration
    }

    override fun toStringInfo(): String {
        return super.toStringInfo() + ", длительность одной операции: $operationDuration минут"
    }
}

fun mas2() {
    println("Введите количество часов (для Time):")
    val hoursInput = readLine()?.toIntOrNull() ?: 0

    println("Введите количество минут (для Time):")
    val minutesInput = readLine()?.toIntOrNull() ?: 0

    val time = Time2(hoursInput, minutesInput)

    println(time.toStringInfo())
2
    println("\nВведите длительность одной операции в минутах (для OperationTime):")
    val operationDurationInput = readLine()?.toIntOrNull() ?: 0

    val operationTime = OperationTime(hoursInput, minutesInput, operationDurationInput)

    println(operationTime.toStringInfo())

    val operationsCount = operationTime.calculateOperations()
    println("Количество операций, которые можно выполнить за это время: $operationsCount")
}

open class SatelliteAntenna(
    private val diameter: Double,
    private val material: String,
    private val price: Double
) {

    init {
        if (diameter <= 0 || price <= 0) {
            throw IllegalArgumentException("Диаметр и цена должны быть положительными числами.")
        }
    }

    open fun calculateQuality(): Double {
        return diameter / price
    }

    open fun printInfo() {
        println("Спутниковая антенна (Тарелка):")
        println("Материал: $material")
        println("Диаметр: $diameter см")
        println("Цена: $price руб")
        println("Качество Q: ${calculateQuality()}")
    }
}

class AdvancedSatelliteAntenna(
    diameter: Double,
    material: String,
    price: Double,
    private val suspensionType: String
) : SatelliteAntenna(diameter, material, price) {

    override fun calculateQuality(): Double {
        val Q = super.calculateQuality()
        return when (suspensionType.lowercase()) {
            "азимутальный" -> Q
            "полярный" -> 2 * Q
            "тороидальный" -> 2.5 * Q
            else -> throw IllegalArgumentException("Неверный тип подвески: $suspensionType")
        }
    }

     override fun printInfo() {
        super.printInfo()
        println("Тип подвески: $suspensionType")
        println("Качество с учетом подвески: ${calculateQuality()}")
    }
}

fun mas3() {

    println("Введите данные для спутниковой антенны:")

    println("Диаметр (в см):")
    val diameter = readLine()?.toDoubleOrNull() ?: 0.0

    println("Материал:")
    val material = readLine() ?: ""

    println("Цена (в рублях):")
    val price = readLine()?.toDoubleOrNull() ?: 0.0

    val antenna = SatelliteAntenna(diameter, material, price)

    antenna.printInfo()

    println("\nВведите данные для продвинутой спутниковой антенны:")

    println("Тип подвески (азимутальный, полярный, тороидальный):")
    val suspensionType = readLine() ?: ""

    val advancedAntenna = AdvancedSatelliteAntenna(diameter, material, price, suspensionType)

    advancedAntenna.printInfo()
}
