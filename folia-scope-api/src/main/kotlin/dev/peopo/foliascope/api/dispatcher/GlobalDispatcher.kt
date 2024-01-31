package dev.peopo.foliascope.api.dispatcher

import dev.peopo.foliascope.api.util.FoliaDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import org.bukkit.plugin.Plugin
import kotlin.coroutines.CoroutineContext

class GlobalDispatcher (
    override val plugin: Plugin
): CoroutineDispatcher(), FoliaDispatcher {

    override fun isDispatchNeeded(context: CoroutineContext): Boolean = true

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        if(!plugin.isEnabled) throw IllegalStateException("${plugin.name} has tried to dispatch a task while it is disabled")

        plugin.server.globalRegionScheduler.execute(plugin, block)
    }
}