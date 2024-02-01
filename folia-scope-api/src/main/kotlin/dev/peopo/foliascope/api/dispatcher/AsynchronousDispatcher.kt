package dev.peopo.foliascope.api.dispatcher

import kotlinx.coroutines.Runnable
import kotlin.coroutines.CoroutineContext

class AsynchronousDispatcher: FoliaDispatcher() {

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        val plugin = getPlugin() ?: throw IllegalStateException("AsynchronousDispatcher can only be used within a plugin context")

        if(!plugin.isEnabled) throw IllegalStateException("${plugin.name} has tried to dispatch a task while it is disabled")

        plugin.server.asyncScheduler.runNow(plugin) { block.run() }
    }
}