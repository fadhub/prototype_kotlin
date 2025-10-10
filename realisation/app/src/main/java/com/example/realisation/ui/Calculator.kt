package com.example.realisation.ui

import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.min

object Calculator {

    fun toMinutes(time: String): Int {
        val (h, m) = time.split(":").map { it.toInt() }
        return h * 60 + m
    }

    fun calculate(startTime: String, endTime: String, tranches: List<Tranche>, proportional: Boolean): Pair<Double, List<String>> {
        val startMin = toMinutes(startTime)
        var endMin = toMinutes(endTime)
        if (endMin < startMin) endMin += 1440

        var total = 0.0
        val details = mutableListOf<String>()

        for (t in tranches) {
            val overlapStart = max(startMin, t.start)
            val overlapEnd = min(endMin, t.end + 1)
            if (overlapStart < overlapEnd) {
                val minutes = overlapEnd - overlapStart
                val hours = minutes / 60.0
                val amount = if (proportional) t.rate * hours else t.rate * ceil(hours)
                total += amount
                details.add("${t.label} : ${"%.2f".format(amount)} MAD")
            }
        }

        return total to details
    }
}
