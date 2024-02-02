package dev.peopo.foliascope.core.dispatcher


import dev.peopo.foliascope.api.context.getPlugin
import kotlinx.coroutines.CoroutineDispatcher
import kotlin.coroutines.CoroutineContext

abstract class FoliaDispatcher : CoroutineDispatcher(){
    override fun isDispatchNeeded(context: CoroutineContext): Boolean {
        val plugin = context.getPlugin()

        return plugin.isEnabled
    }
}

