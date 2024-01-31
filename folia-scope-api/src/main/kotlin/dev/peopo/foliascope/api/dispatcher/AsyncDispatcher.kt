package dev.peopo.foliascope.api.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import org.bukkit.plugin.Plugin
import kotlin.coroutines.CoroutineContext

class AsyncDispatcher(
    private val plugin: Plugin
) : CoroutineDispatcher() {

    override fun isDispatchNeeded(context: CoroutineContext): Boolean = true
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        if(!plugin.isEnabled) throw IllegalStateException("${plugin.name} has tried to dispatch a task while it is disabled")

        plugin.server.asyncScheduler.runNow(plugin) { block.run() }
    }
}