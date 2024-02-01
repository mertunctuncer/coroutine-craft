package dev.peopo.foliascope.api.dispatcher

import kotlinx.coroutines.Runnable
import kotlin.coroutines.CoroutineContext

class GlobalDispatcher: FoliaDispatcher() {

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        val plugin = getPlugin() ?: throw IllegalStateException("GlobalDispatcher can only be used within a plugin context")

        if(!plugin.isEnabled) throw IllegalStateException("${plugin.name} has tried to dispatch a task while it is disabled")

        plugin.server.globalRegionScheduler.execute(plugin, block)
    }
}