package dev.peopo.foliascope.api.dispatcher

import kotlinx.coroutines.Runnable
import org.bukkit.World
import kotlin.coroutines.CoroutineContext

class RegionDispatcher(
    private val world: World,
    private val chunkX: Int,
    private val chunkZ: Int
) : FoliaDispatcher() {

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        val plugin = getPlugin() ?: throw IllegalStateException("RegionDispatcher can only be used within a plugin context")

        if(!plugin.isEnabled) throw IllegalStateException("${plugin.name} has tried to dispatch a task while it is disabled")

        plugin.server.regionScheduler.execute(plugin, world, chunkX, chunkZ, block)
    }

}