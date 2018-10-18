@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import lesson1.task1.sqr
import java.lang.Math.abs
import java.lang.Math.pow
import kotlin.math.max
import kotlin.math.min
import kotlin.math.sqrt

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var digitNumber = 0
    var number = n
    while (number > 0) {
        number /= 10
        digitNumber += 1
    }
    return digitNumber
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int = if (n <= 2) 1
else fib(n - 2) + fib(n - 1)

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var divider = 1
    val min = min(m, n)
    for (i in 1..min / 2) {
        if (m % i == 0 && n % i == 0) divider *= i
    }
    if (max(m, n) % min == 0) divider *= min
    return m * n / divider
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var minDivisor = 1
    for (i in 2..n) {
        if (n % i == 0) {
            minDivisor = i
            break
        }
    }
    return minDivisor
}


/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var maxDivisor = 1
    for (i in n - 1 downTo 2) {
        if (n % i == 0) {
            maxDivisor = i
            break
        }
    }
    return maxDivisor
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    if (max(m, n) % min(m, n) == 0) return false
    for (i in 2..min(m, n) / 2) {
        if (m % i == 0 && n % i == 0) return false
    }
    return true
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    var m1 = (sqrt(m.toDouble())).toInt()
    var n1 = (sqrt(n.toDouble())).toInt()
    if (m1 * m1 == m || m1 != n1) return true
    return false
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var x1 = x
    var count = 0
    while (x1 != 1) {
        count++
        if (x1 % 2 == 0) x1 = x1 / 2
        else
            x1 = 3 * x1 + 1
    }
    return count
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    var sinX = x
    var x1 = x
    var n = 1
    var count = 1
    while (abs(x1) >= eps) {
        n += 2
        count *= -1
        x1 = count * pow(x, n.toDouble()) / factorial(n)
        sinX += x1
    }
    return sinX
}


/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var cosX = 0.0
    var x1 = 0.0
    var n = 0
    var count = 1
    do {
        x1 = count * pow(x, n.toDouble()) / factorial(n)
        cosX += x1
        n += 2
        count *= -1
    } while (abs(x1) >= eps)
    return cosX
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var n1 = n
    var k = 0
    var m = 0
    while (n1 > 0) {
        k = n1 % 10
        n1 /= 10
        m = m * 10 + k
    }
    return m
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    var count = 0
    var m = n
    var k = 1
    while (m > 0) {
        count++
        m /= 10
        k *= 10
    }
    m = n
    k /= 10
    while (count > 1) {
        if (m % 10 != m / k) return false
        m = (m % k) / 10
        count -= 2
        k /= 100
    }
    return true
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var x1 = n % 10
    var x2 = (n / 10) % 10
    var k = n / 100
    while (k > 0) {
        if (x1 != x2) return true
        x1 = x2
        x2 = k % 10
        k /= 10
    }
    if (x1 != x2) return true
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var count = n
    var x = 0
    var xSqr = 0
    while (count > 0) {
        x++
        xSqr = sqr(x)
        while (xSqr > 0) {
            count--
            xSqr /= 10
        }
    }
    xSqr = sqr(x)
    while (count < 0) {
        count++
        xSqr /= 10
    }
    return xSqr % 10
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var k1 = 1
    var k2 = 1
    var count = n - 2
    var k = 1
    if (n in 1..2) return 1
    while (count > 0) {

        k = k1
        k1 = k2
        k2 += k
        while (k2 > 0) {
            count--
            k2 /= 10
        }
        k2 = k + k1
    }
    while (count < 0 ) {
        count++
        k2 /= 10
    }
    return k2 % 10
}
