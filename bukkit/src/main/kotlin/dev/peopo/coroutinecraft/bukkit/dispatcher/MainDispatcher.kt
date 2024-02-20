package dev.peopo.coroutinecraft.bukkit.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.isActive
import org.bukkit.Bukkit
import org.bukkit.plugin.Plugin
import kotlin.coroutines.CoroutineContext

internal class MainDispatcher(
    private val plugin: Plugin
): CoroutineDispatcher() {

    override fun isDispatchNeeded(context: CoroutineContext): Boolean {
        return !Bukkit.isPrimaryThread() || context.isActive
    }
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        if(Bukkit.isPrimaryThread() || !context.isActive) return

        plugin.server.scheduler.runTask(plugin, block)
    }
}