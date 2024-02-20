package dev.peopo.coroutinecraft.folia.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.isActive
import org.bukkit.Server
import org.bukkit.plugin.Plugin
import kotlin.coroutines.CoroutineContext

internal class AsynchronousDispatcher(
    private val plugin: Plugin
): CoroutineDispatcher() {

    override fun isDispatchNeeded(context: CoroutineContext): Boolean {
        return context.isActive
    }

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        if(!context.isActive) return

        val server: Server = plugin.server

        server.asyncScheduler.runNow(plugin) { block.run() }
    }
}