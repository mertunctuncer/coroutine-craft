package dev.peopo.foliascope.api.context

import org.bukkit.plugin.Plugin
import kotlin.coroutines.CoroutineContext

interface PluginElement : CoroutineContext.Element{

    val plugin: Plugin
    override val key: CoroutineContext.Key<*> get() = Key
    companion object Key: CoroutineContext.Key<PluginElement>
}