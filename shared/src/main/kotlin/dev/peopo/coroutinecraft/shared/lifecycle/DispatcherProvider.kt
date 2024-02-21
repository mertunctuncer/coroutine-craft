package dev.peopo.coroutinecraft.shared.lifecycle

import dev.peopo.coroutinecraft.shared.util.LifecycleException
import org.bukkit.plugin.Plugin
import java.util.concurrent.ConcurrentHashMap
import kotlin.coroutines.CoroutineContext

public interface DispatcherProvider {
    public fun getDispatcher(): CoroutineContext
    public fun getAsynchronousDispatcher(): CoroutineContext
    public fun <T> getDispatcher(context: T): CoroutineContext

    public companion object {
        private val providers: ConcurrentHashMap<Plugin, DispatcherProvider> by lazy {
            ConcurrentHashMap<Plugin, DispatcherProvider>()
        }

        public fun getProvider(plugin: Plugin): DispatcherProvider =
            providers[plugin] ?: throw LifecycleException("Plugin does not own a dispatcher provider.")

        public fun registerDispatcher(plugin: Plugin, dispatcherProvider: DispatcherProvider) {
            providers[plugin] = dispatcherProvider
        }

        public fun closeDispatcher(plugin: Plugin) {
            providers.remove(plugin)
        }
    }
}