package dev.peopo.coroutinecraft.folia.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.isActive
import org.bukkit.Server
import org.bukkit.World
import org.bukkit.plugin.Plugin
import kotlin.coroutines.CoroutineContext

internal class RegionDispatcher(
    private val plugin: Plugin,
    private val world: World,
    private val chunkX: Int,
    private val chunkZ: Int
) : CoroutineDispatcher() {

    override fun isDispatchNeeded(context: CoroutineContext): Boolean {
        return context.isActive
    }

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        if (!context.isActive) return

        val server: Server = plugin.server

        server.regionScheduler.execute(plugin, world, chunkX, chunkZ, block)
    }
}