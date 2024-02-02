package dev.peopo.foliascope.api

import kotlinx.coroutines.CoroutineScope
import org.bukkit.World
import org.bukkit.entity.Entity
import kotlin.coroutines.CoroutineContext

interface Session {

    val scope: CoroutineScope

    val globalDispatcher: CoroutineContext
    val asyncDispatcher: CoroutineContext
    fun entityDispatcher(entity: Entity): CoroutineContext
    fun regionDispatcher(chunkX: Int, chunkZ: Int, world: World): CoroutineContext
}