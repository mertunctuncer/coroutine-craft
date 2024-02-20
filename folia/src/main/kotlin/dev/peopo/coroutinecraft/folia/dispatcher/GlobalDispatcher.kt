package dev.peopo.coroutinecraft.folia.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.isActive
import org.bukkit.Server
import org.bukkit.plugin.Plugin
import kotlin.coroutines.CoroutineContext

internal class GlobalDispatcher (
    private val plugin: Plugin
): CoroutineDispatcher() {

    override fun isDispatchNeeded(context: CoroutineContext): Boolean {
        return context.isActive
    }

    override fun dispatch(context: CoroutineContext, block: Runnable) {

        if(!plugin.isEnabled) throw IllegalStateException("${plugin.name} has tried to dispatch a task while it is disabled")

        val server: Server = plugin.server
        server.globalRegionScheduler.execute(plugin, block)
    }
}