package dev.peopo.foliascope.api.plugin

import dev.peopo.foliascope.api.util.Scoped
import kotlinx.coroutines.CoroutineScope
import org.bukkit.Chunk
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Entity
import org.bukkit.plugin.Plugin
import kotlin.coroutines.CoroutineContext


/**
 * Returns the associated coroutine scope
 */
val Plugin.scope: CoroutineScope
    get() = (this as Scoped).scope

/**
 * Returns the associated global dispatcher
 */
val Plugin.globalDispatcher: CoroutineContext
    get() = this.toKotlinPlugin().dispatcherProvider.globalDispatcher

/**
 * Returns the associated asynchronous dispatcher
 */
val Plugin.asyncDispatcher: CoroutineContext
    get() = this.toKotlinPlugin().dispatcherProvider.asyncDispatcher

/**
 * Returns a new entity dispatcher with the plugin context
 */
fun Plugin.entityDispatcher(entity: Entity) : CoroutineContext {
    return this.toKotlinPlugin().dispatcherProvider.entityDispatcher(entity)
}

/**
 * Returns a new region dispatcher with the plugin context
 */
fun Plugin.regionDispatcher(location: Location) : CoroutineContext {
    with(location.chunk) {
        return this@regionDispatcher.toKotlinPlugin().dispatcherProvider.regionDispatcher(x, z, world)
    }
}

/**
 * Returns a new region dispatcher with the plugin context
 */
fun Plugin.regionDispatcher(chunkX: Int, chunkZ: Int, world: World) : CoroutineContext {
    return this.toKotlinPlugin().dispatcherProvider.regionDispatcher(chunkX, chunkZ, world)
}

/**
 * Returns a new region dispatcher with the plugin context
 */
fun Plugin.regionDispatcher(chunk: Chunk) : CoroutineContext {
    return this.regionDispatcher(chunk.x, chunk.z, chunk.world)
}

/**
 * Casts this plugin to a kotlin plugin
 * @throws IllegalCallerException if this plugin is not a kotlin plugin
 */
fun Plugin.toKotlinPlugin() = this as? KotlinPlugin ?: throw ClassCastException("Plugin is not a KotlinPlugin")