package dev.peopo.foliascope.core.dispatcher

import dev.peopo.foliascope.api.context.getPlugin
import kotlinx.coroutines.Runnable
import org.bukkit.World
import kotlin.coroutines.CoroutineContext

class RegionDispatcher(
    private val world: World,
    private val chunkX: Int,
    private val chunkZ: Int
) : FoliaDispatcher() {

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        val plugin = context.getPlugin()

        if(!plugin.isEnabled) throw IllegalStateException("${plugin.name} has tried to dispatch a task while it is disabled")

        plugin.server.regionScheduler.execute(plugin, world, chunkX, chunkZ, block)
    }

}