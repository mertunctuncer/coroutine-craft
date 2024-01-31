package dev.peopo.foliascope.api.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import org.bukkit.World
import org.bukkit.plugin.Plugin
import kotlin.coroutines.CoroutineContext

class RegionDispatcher(
    private val plugin: Plugin,
    private val world: World,
    private val chunkX: Int,
    private val chunkZ: Int
) : CoroutineDispatcher() {
    override fun isDispatchNeeded(context: CoroutineContext): Boolean = true

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        if(!plugin.isEnabled) throw IllegalStateException("${plugin.name} has tried to dispatch a task while it is disabled")

        plugin.server.regionScheduler.execute(plugin, world, chunkX, chunkZ, block)
    }

}