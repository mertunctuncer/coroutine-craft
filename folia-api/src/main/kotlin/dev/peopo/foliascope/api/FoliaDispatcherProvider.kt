package dev.peopo.foliascope.api

import org.bukkit.World
import org.bukkit.entity.Entity
import kotlin.coroutines.CoroutineContext


/**
 * Provider for folia dispatchers
 */
interface FoliaDispatcherProvider {

    /**
     * Cached global dispatcher
     */
    val globalDispatcher: CoroutineContext

    /**
     * Cached asynchronous dispatcher
     */
    val asyncDispatcher: CoroutineContext

    /**
     * Cached asynchronous dispatcher
     */
    val asynchronousDispatcher: CoroutineContext get() = asyncDispatcher

    /**
     * Initializes a new entity dispatcher and returns it
     */
    fun entityDispatcher(entity: Entity): CoroutineContext

    /**
     * Initializes a new region dispatcher and returns it
     */
    fun regionDispatcher(chunkX: Int, chunkZ: Int, world: World): CoroutineContext
}