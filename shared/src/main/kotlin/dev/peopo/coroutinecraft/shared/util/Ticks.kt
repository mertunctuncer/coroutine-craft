package dev.peopo.coroutinecraft.shared.util



private const val TICKS_PER_SECOND = 20
private const val TICKS_PER_MINUTE = TICKS_PER_SECOND * 60
private const val TICK_DURATION_MILLIS = 1000 / TICKS_PER_SECOND
public data class Ticks(val amount: Long) {
    val minutes: Long get() = amount / TICKS_PER_MINUTE
    val seconds: Long get() = amount / TICKS_PER_SECOND
    val millis: Long get() = amount * TICK_DURATION_MILLIS
}


public val Byte.ticks: Ticks get() = Ticks(toLong())
public val Short.ticks: Ticks get() = Ticks(toLong())
public val Int.ticks: Ticks get() = Ticks(toLong())
public val Long.ticks: Ticks get() = Ticks(this)