package dev.peopo.foliascope.api.dispatcher

import dev.peopo.foliascope.api.context.PluginElement
import kotlinx.coroutines.CoroutineDispatcher
import kotlin.coroutines.CoroutineContext

abstract class FoliaDispatcher : CoroutineDispatcher(){

    override fun isDispatchNeeded(context: CoroutineContext): Boolean {
        val plugin = context.getPlugin() ?: throw IllegalStateException("FoliaDispatcher can only be used within a plugin context")
        return plugin.isEnabled
    }

    protected fun CoroutineContext.getPlugin() = this[PluginElement]?.plugin
}