package dev.peopo.coroutinecraft.shared

import dev.peopo.coroutinecraft.shared.lifecycle.DispatcherProvider
import dev.peopo.coroutinecraft.shared.lifecycle.ScopeHolder
import dev.peopo.coroutinecraft.shared.util.PluginContext
import dev.peopo.coroutinecraft.shared.util.Ticks
import kotlinx.coroutines.*

import kotlin.coroutines.coroutineContext


context(PluginContext<*>)
public fun launchTask(task: CoroutineScope.() -> Unit): Job {
    val scope = ScopeHolder.getScopeHolder(plugin).getScope(plugin)
    val dispatcher = DispatcherProvider.getProvider(plugin).getDispatcher()

    return scope.launch(dispatcher) {
        this.task()
    }
}

context(PluginContext<*>)
public fun launchTaskAsync(task: CoroutineScope.() -> Unit): Job {
    val scope = ScopeHolder.getScopeHolder(plugin).getScope(plugin)
    val dispatcher = DispatcherProvider.getProvider(plugin).getAsynchronousDispatcher()

    return scope.launch(dispatcher) {
        this.task()
    }
}

context(PluginContext<*>)
public fun CoroutineScope.launchTask(task: CoroutineScope.() -> Unit): Job {
    val dispatcher = DispatcherProvider.getProvider(plugin).getDispatcher()

    return this.launch(dispatcher) {
        this.task()
    }
}

context(PluginContext<*>)
public fun <T> T.launchTask(task: CoroutineScope.(T) -> Unit): Job {
    val scope = ScopeHolder.getScopeHolder(plugin).getScope(this)
    val dispatcher = DispatcherProvider.getProvider(plugin).getDispatcher(this)

    return scope.launch(dispatcher) {
        this.task(this@launchTask)
    }
}

context(PluginContext<*>)
public fun launchTaskLater(delay: Ticks, task: CoroutineScope.() -> Unit): Job {
    val scope = ScopeHolder.getScopeHolder(plugin).getScope(plugin)
    val dispatcher = DispatcherProvider.getProvider(plugin).getDispatcher()

    return scope.launch(dispatcher) {
        later(delay.millis, this, task)
    }
}

context(PluginContext<*>)
public fun launchTaskLaterAsync(delay: Ticks, task: CoroutineScope.() -> Unit): Job {
    val scope = ScopeHolder.getScopeHolder(plugin).getScope(plugin)
    val dispatcher = DispatcherProvider.getProvider(plugin).getAsynchronousDispatcher()

    return scope.launch(dispatcher) {
        later(delay.millis, this, task)
    }
}

context(PluginContext<*>)
public fun CoroutineScope.launchTaskLater(delay: Ticks, task: CoroutineScope.() -> Unit): Job {
    val dispatcher = DispatcherProvider.getProvider(plugin).getDispatcher()

    return this.launch(dispatcher) {
        later(delay.millis, this, task)
    }
}

context(PluginContext<*>)
public fun <T> T.launchTaskLater(delay: Ticks, task: CoroutineScope.(T) -> Unit): Job {
    val scope = ScopeHolder.getScopeHolder(plugin).getScope(this)
    val dispatcher = DispatcherProvider.getProvider(plugin).getDispatcher(this)

    return scope.launch(dispatcher) {
        this@launchTaskLater.later(delay.millis, this, task)
    }
}

context(PluginContext<*>)
public fun launchTaskTimer(delay: Ticks, interval: Ticks, task: CoroutineScope.() -> Unit): Job {
    val scope = ScopeHolder.getScopeHolder(plugin).getScope(plugin)
    val dispatcher = DispatcherProvider.getProvider(plugin).getDispatcher()

    return scope.launch(dispatcher) {
        timer(delay.millis, interval.millis, this, task)
    }
}

context(PluginContext<*>)
public fun launchTaskTimerAsync(delay: Ticks, interval: Ticks, task: CoroutineScope.() -> Unit): Job {
    val scope = ScopeHolder.getScopeHolder(plugin).getScope(plugin)
    val dispatcher = DispatcherProvider.getProvider(plugin).getAsynchronousDispatcher()

    return scope.launch(dispatcher) {
        timer(delay.millis, interval.millis, this, task)
    }
}

context(PluginContext<*>)
public fun CoroutineScope.launchTaskTimer(delay: Ticks, interval: Ticks, task: CoroutineScope.() -> Unit): Job {
    val dispatcher = plugin.dispatcherProvider.getDispatcher()

    return this.launch(dispatcher) {
        timer(delay.millis, interval.millis, this, task)
    }
}

context(PluginContext<*>)
public fun <T> T.launchTaskTimer(delay: Ticks, interval: Ticks, task: CoroutineScope.(T) -> Unit): Job {
    val scope = ScopeHolder.getScopeHolder(plugin).getScope(this)
    val dispatcher = plugin.dispatcherProvider.getDispatcher(this)

    return scope.launch(dispatcher) {
        this@launchTaskTimer.timer(delay.millis, interval.millis, this, task)
    }
}

public suspend fun delay(delay: Ticks) {
    delay(delay.millis)
}

// Internal stuff, ignore
internal suspend fun timer(
    delay: Long,
    interval: Long,
    coroutineScope: CoroutineScope,
    task: CoroutineScope.() -> Unit
) {
    delay(delay)
    while (coroutineContext.isActive) {
        coroutineScope.task()
        delay(interval)
    }
}

internal suspend fun <T> T.timer(
    delay: Long,
    interval: Long,
    coroutineScope: CoroutineScope,
    task: CoroutineScope.(T) -> Unit
) {
    delay(delay)
    while (coroutineContext.isActive) {
        coroutineScope.task(this)
        delay(interval)
    }
}

internal suspend fun later(delay: Long, coroutineScope: CoroutineScope, task: CoroutineScope.() -> Unit) {
    delay(delay)
    coroutineScope.task()
}

internal suspend fun <T> T.later(delay: Long, coroutineScope: CoroutineScope, task: CoroutineScope.(T) -> Unit) {
    delay(delay)
    coroutineScope.task(this)
}



