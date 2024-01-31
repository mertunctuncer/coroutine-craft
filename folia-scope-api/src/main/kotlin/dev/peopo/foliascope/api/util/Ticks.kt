package dev.peopo.foliascope.api.util



data class Ticks(val amount: Long) {
    val minutes: Long get() = amount / 1200
    val seconds: Long get() = amount / 20
    val millis: Long get() = amount * 50
}

val Int.ticks: Ticks get() = Ticks(toLong())
val Short.ticks: Ticks get() = Ticks(toLong())
val Long.ticks: Ticks get() = Ticks(this)