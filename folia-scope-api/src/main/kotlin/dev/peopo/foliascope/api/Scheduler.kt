package dev.peopo.foliascope.api

import dev.peopo.foliascope.api.context.getPlugin

import kotlinx.coroutines.launch
import org.bukkit.Chunk
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Entity


import kotlin.coroutines.coroutineContext


suspend fun launchTask(task: () -> Unit) = with(coroutineContext.getPlugin()) {
    scope.launch(globalDispatcher) { task.invoke() }
}

suspend fun launchTask(location: Location, task: () -> Unit) = with(coroutineContext.getPlugin()) {
    scope.launch(regionDispatcher(location)) { task.invoke() }
}
suspend fun launchTask(chunk: Chunk, task: () -> Unit) = with(coroutineContext.getPlugin()) {
    scope.launch(regionDispatcher(chunk)) { task.invoke() }
}
suspend fun launchTask(chunkX: Int, chunkZ: Int, world: World, task: () -> Unit) = with(coroutineContext.getPlugin()) {
    scope.launch(regionDispatcher(chunkX, chunkZ, world)) { task.invoke() }
}
suspend fun Location.launchTask(task: () -> Unit) = launchTask(this, task)
suspend fun Chunk.launchTask(task: () -> Unit) = launchTask(this, task)

suspend fun launchTask(entity: Entity, task: () -> Unit) = with(coroutineContext.getPlugin()) {
    scope.launch(entityDispatcher(entity)) { task.invoke() }
}
suspend fun Entity.launchTask(task: () -> Unit) = launchTask(this, task)


suspend fun runTaskAsync(task: () -> Unit) = with(coroutineContext.getPlugin()) {
    scope.launch(asyncDispatcher) { task.invoke() }
}

