package dev.peopo.foliascope.api.context

import kotlinx.coroutines.CoroutineScope
import org.bukkit.plugin.Plugin
import kotlin.coroutines.CoroutineContext

interface PluginElement : CoroutineContext.Element{

    val plugin: Plugin
    override val key: CoroutineContext.Key<*> get() = Key
    companion object Key: CoroutineContext.Key<PluginElement>
}

fun CoroutineContext.getPlugin() = this[PluginElement]?.plugin ?: throw IllegalStateException("FoliaDispatcher can only be used within a plugin context")
fun CoroutineScope.getPlugin() = this.coroutineContext.getPlugin()