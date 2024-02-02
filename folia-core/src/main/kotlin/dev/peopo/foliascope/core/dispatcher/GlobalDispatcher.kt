package dev.peopo.foliascope.core.dispatcher

import dev.peopo.foliascope.api.context.getPlugin
import kotlinx.coroutines.Runnable
import kotlin.coroutines.CoroutineContext

class GlobalDispatcher: FoliaDispatcher() {

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        val plugin = context.getPlugin()

        if(!plugin.isEnabled) throw IllegalStateException("${plugin.name} has tried to dispatch a task while it is disabled")

        plugin.server.globalRegionScheduler.execute(plugin, block)
    }
}