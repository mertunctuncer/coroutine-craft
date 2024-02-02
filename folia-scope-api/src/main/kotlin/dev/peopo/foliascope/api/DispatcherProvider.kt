package dev.peopo.foliascope.api

import org.bukkit.World
import org.bukkit.entity.Entity
import kotlin.coroutines.CoroutineContext

interface DispatcherProvider {

    val globalDispatcher: CoroutineContext
    val asyncDispatcher: CoroutineContext
    fun entityDispatcher(entity: Entity): CoroutineContext
    fun regionDispatcher(chunkX: Int, chunkZ: Int, world: World): CoroutineContext
}