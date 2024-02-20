package dev.peopo.coroutinecraft.folia

import dev.peopo.coroutinecraft.folia.dispatcher.AsynchronousDispatcher
import dev.peopo.coroutinecraft.folia.dispatcher.EntityDispatcher
import dev.peopo.coroutinecraft.folia.dispatcher.GlobalDispatcher
import dev.peopo.coroutinecraft.folia.dispatcher.RegionDispatcher
import dev.peopo.coroutinecraft.shared.lifecycle.DispatcherProvider
import org.bukkit.Chunk
import org.bukkit.Location
import org.bukkit.block.Block
import org.bukkit.entity.Entity
import org.bukkit.plugin.Plugin
import kotlin.coroutines.CoroutineContext

internal class FoliaDispatcherProvider(
    private val plugin: Plugin
) : DispatcherProvider {

    private val globalDispatcher = GlobalDispatcher(plugin)
    private val asynchronousDispatcher = AsynchronousDispatcher(plugin)
    override fun getDispatcher(): CoroutineContext = globalDispatcher
    override fun <T> getDispatcher(context: T): CoroutineContext {
        return when(context) {
            is Chunk -> RegionDispatcher(plugin, context.world, context.x, context.z)
            is Block -> RegionDispatcher(plugin, context.world, context.chunk.x, context.chunk.z)
            is Location -> RegionDispatcher(plugin, context.world, context.chunk.x, context.chunk.z)
            is Entity -> EntityDispatcher(plugin, context)
            else -> globalDispatcher
        }
    }

    override fun getAsynchronousDispatcher(): CoroutineContext = asynchronousDispatcher
}

public fun DispatcherProvider.Companion.of(plugin: Plugin): DispatcherProvider = FoliaDispatcherProvider(plugin)

