package krescere.jipsayobackend.common.handler

import java.math.BigDecimal
import kotlin.math.pow
import kotlin.math.roundToInt

class DecimalPointHandler {
    companion object {
        fun doubleToBigDecimal(double: Double, point: Int=7) : BigDecimal {
            return BigDecimal.valueOf(roundToPoint(double, point))
        }

        fun bigDecimalToDouble(bigDecimal: BigDecimal, point: Int=7) : Double {
            return roundToPoint(bigDecimal.toDouble(), point)
        }

        fun roundToPoint(double: Double, point : Int) : Double {
            return (double * 10.0.pow(point.toDouble())).roundToInt() / 10.0.pow(point.toDouble())
        }
    }
}