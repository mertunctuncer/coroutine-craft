package dev.peopo.coroutinecraft.shared.lifecycle

import dev.peopo.coroutinecraft.shared.util.LifecycleException
import kotlinx.coroutines.CoroutineScope
import org.bukkit.plugin.Plugin
import java.util.concurrent.ConcurrentHashMap

public interface ScopeHolder : AutoCloseable {
    public fun <T> getScope(context: T): CoroutineScope
    public override fun close()

    public companion object {
        private val scopeHolders: ConcurrentHashMap<Plugin, ScopeHolder> by lazy {
            ConcurrentHashMap<Plugin, ScopeHolder>()
        }

        public fun getScopeHolder(plugin: Plugin): ScopeHolder =
            scopeHolders[plugin] ?: throw LifecycleException("Plugin does not own a scope holder.")

        public fun registerScopeHolder(plugin: Plugin, scopeHolder: ScopeHolder) {
            scopeHolders[plugin] = scopeHolder
        }

        public fun closeScopeHolder(plugin: Plugin) {
            val holder = scopeHolders.remove(plugin)
            holder?.close()
        }
    }
}