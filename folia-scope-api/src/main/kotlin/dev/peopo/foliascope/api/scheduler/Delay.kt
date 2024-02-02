package dev.peopo.foliascope.api.scheduler

import dev.peopo.foliascope.api.context.getPlugin
import dev.peopo.foliascope.api.plugin.entityDispatcher
import dev.peopo.foliascope.api.plugin.globalDispatcher
import dev.peopo.foliascope.api.plugin.regionDispatcher
import dev.peopo.foliascope.api.plugin.scope
import dev.peopo.foliascope.api.util.Ticks
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.bukkit.Chunk
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Entity
import kotlin.coroutines.coroutineContext





suspend fun launchLater(delay: Ticks, task: () -> Unit) = with(coroutineContext.getPlugin()) {
    scope.launch { later(delay, task) }
}
suspend fun launchLater(location: Location, delay: Ticks, task: () -> Unit) = with(coroutineContext.getPlugin()) {
    scope.launch(regionDispatcher(location)) { later(delay, task) }
}
suspend fun launchLater(chunk: Chunk, delay: Ticks, task: () -> Unit) = with(coroutineContext.getPlugin()) {
    scope.launch(regionDispatcher(chunk)) { later(delay, task) }
}
suspend fun launchLater(chunkX: Int, chunkZ: Int, world: World, delay: Ticks, task: () -> Unit) = with(coroutineContext.getPlugin()) {
    scope.launch(regionDispatcher(chunkX, chunkZ, world)) { later(delay, task) }
}
suspend fun Location.launchLater(delay: Ticks, task: () -> Unit) = launchLater(this, delay, task)
suspend fun Chunk.launchLater(delay: Ticks, task: () -> Unit) = launchLater(this, delay, task)


suspend fun launchLater(entity: Entity, delay: Ticks, task: () -> Unit) = with(coroutineContext.getPlugin()) {
    scope.launch(entityDispatcher(entity)) { later(delay, task) }
}
suspend fun Entity.launchLater(delay: Ticks, task: () -> Unit) = launchLater(this, delay, task)


suspend fun launchTimer(delay: Ticks, interval: Ticks, task: () -> Unit) = with(coroutineContext.getPlugin()) {
    scope.launch(globalDispatcher) { timer(delay, interval, task) }
}
suspend fun launchTimer(location: Location, delay: Ticks, interval: Ticks, task: () -> Unit) = with(coroutineContext.getPlugin()) {
    scope.launch(regionDispatcher(location)) { timer(delay, interval, task)}
}
suspend fun launchTimer(chunk: Chunk, delay: Ticks, interval: Ticks, task: () -> Unit) = with(coroutineContext.getPlugin()) {
    scope.launch(regionDispatcher(chunk)) { timer(delay, interval, task) }
}
suspend fun launchTimer(chunkX: Int, chunkZ: Int, world: World, delay: Ticks, interval: Ticks, task: () -> Unit) = with(coroutineContext.getPlugin()) {
    scope.launch(regionDispatcher(chunkX, chunkZ, world)) { timer(delay, interval, task) }
}
suspend fun Location.launchTimer(delay: Ticks, interval: Ticks, task: () -> Unit) = launchTimer(this, delay, interval, task)
suspend fun Chunk.launchTimer(delay: Ticks, interval: Ticks, task: () -> Unit) = launchTimer(this, delay, interval, task)

suspend fun launchTimer(entity: Entity, delay: Ticks, interval: Ticks, task: () -> Unit) = with(coroutineContext.getPlugin()) {
    scope.launch(entityDispatcher(entity)) { timer(delay, interval, task) }
}
suspend fun Entity.launchTimer(delay: Ticks, interval: Ticks, task: () -> Unit) = launchTimer(this, delay, interval, task)

suspend fun delay(ticks: Ticks) = kotlinx.coroutines.delay(ticks.millis)

private suspend fun CoroutineScope.timer(delay: Ticks, interval: Ticks, task: () -> Unit) {
    delay(delay)
    while(isActive) {
        task.invoke()
        delay(interval)
    }
}

private suspend fun later(delay: Ticks, task: () -> Unit) {
    delay(delay)
    task.invoke()
}
