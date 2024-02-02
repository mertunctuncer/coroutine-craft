package dev.peopo.foliascope.api

import kotlinx.coroutines.CoroutineScope
import org.bukkit.Chunk
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Entity
import org.bukkit.plugin.Plugin
import kotlin.coroutines.CoroutineContext


val Plugin.scope: CoroutineScope
    get() = this.toKotlinPlugin().session.scope
val Plugin.globalDispatcher: CoroutineContext
    get() = this.toKotlinPlugin().session.globalDispatcher
val Plugin.asyncDispatcher: CoroutineContext
    get() = this.toKotlinPlugin().session.asyncDispatcher
fun Plugin.entityDispatcher(entity: Entity) : CoroutineContext {
    return this.toKotlinPlugin().session.entityDispatcher(entity)
}
fun Plugin.regionDispatcher(location: Location) : CoroutineContext {
    with(location.chunk) {
        return this@regionDispatcher.toKotlinPlugin().session.regionDispatcher(x, z, world)
    }
}
fun Plugin.regionDispatcher(chunkX: Int, chunkZ: Int, world: World) : CoroutineContext {
    return this.toKotlinPlugin().session.regionDispatcher(chunkX, chunkZ, world)
}
fun Plugin.regionDispatcher(chunk: Chunk) : CoroutineContext {
    return this.regionDispatcher(chunk.x, chunk.z, chunk.world)
}
fun Plugin.toKotlinPlugin() = this as? KotlinPlugin ?: throw IllegalCallerException("Plugin ${this.name} is not a KotlinPlugin")