package dev.peopo.coroutinecraft.folia

import dev.peopo.coroutinecraft.shared.dispatcherProvider
import dev.peopo.coroutinecraft.shared.util.PluginContext
import kotlinx.coroutines.Dispatchers
import org.bukkit.Chunk
import org.bukkit.Location
import org.bukkit.block.Block
import org.bukkit.entity.Entity
import org.bukkit.plugin.Plugin
import kotlin.coroutines.CoroutineContext


// From plugin
public val Plugin.globalDispatcher: CoroutineContext
    get() = this.dispatcherProvider.getDispatcher()

public val Plugin.asyncDispatcher: CoroutineContext
    get() = this.dispatcherProvider.getAsynchronousDispatcher()

public fun Plugin.entityDispatcher(entity: Entity): CoroutineContext {
    return this.dispatcherProvider.getDispatcher(entity)
}

public fun Plugin.regionDispatcher(chunk: Chunk): CoroutineContext {
    return this.dispatcherProvider.getDispatcher(chunk)
}

public fun Plugin.regionDispatcher(location: Location): CoroutineContext {
    return this.dispatcherProvider.getDispatcher(location)
}

public fun Plugin.regionDispatcher(block: Block): CoroutineContext {
    return this.dispatcherProvider.getDispatcher(block)
}

public fun Dispatchers.global(plugin: Plugin): CoroutineContext = plugin.globalDispatcher
public fun Dispatchers.async(plugin: Plugin): CoroutineContext = plugin.asyncDispatcher
public fun Dispatchers.entity(plugin: Plugin, entity: Entity): CoroutineContext = plugin.entityDispatcher(entity)
public fun Dispatchers.region(plugin: Plugin, chunk: Chunk): CoroutineContext = plugin.regionDispatcher(chunk)
public fun Dispatchers.region(plugin: Plugin, location: Location): CoroutineContext = plugin.regionDispatcher(location)
public fun Dispatchers.region(plugin: Plugin, block: Block): CoroutineContext = plugin.regionDispatcher(block)

context(PluginContext<*>)
public val globalDispatcher: CoroutineContext
    get() = plugin.globalDispatcher

context(PluginContext<*>)
public val asyncDispatcher: CoroutineContext
    get() = plugin.asyncDispatcher

context(PluginContext<*>)
public fun entityDispatcher(entity: Entity): CoroutineContext {
    return plugin.entityDispatcher(entity)
}

context(PluginContext<*>)
public fun regionDispatcher(chunk: Chunk): CoroutineContext {
    return plugin.regionDispatcher(chunk)
}

context(PluginContext<*>)
public fun regionDispatcher(location: Location): CoroutineContext {
    return plugin.regionDispatcher(location)
}

context(PluginContext<*>)
public fun regionDispatcher(block: Block): CoroutineContext {
    return plugin.regionDispatcher(block)
}