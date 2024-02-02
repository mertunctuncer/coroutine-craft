package dev.peopo.foliascope.api.plugin

import dev.peopo.foliascope.api.FoliaDispatcherProvider
import dev.peopo.foliascope.api.util.Scoped
import dev.peopo.foliascope.core.FoliaPluginDispatcherProvider
import dev.peopo.foliascope.core.context.PluginElementImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.runBlocking
import org.bukkit.plugin.java.JavaPlugin
import kotlin.coroutines.EmptyCoroutineContext

abstract class KotlinPlugin: SuspendingPlugin, Scoped, JavaPlugin() {

    /**
     * CoroutineScope associated with the plugin, it should stay alive during the lifetime of the plugin
     *
     * @see CoroutineScope
     */
    override val scope: CoroutineScope by lazy {
        CoroutineScope(EmptyCoroutineContext + PluginElementImpl(this@KotlinPlugin))
    }

    /**
     * DispatcherProvider associated with the plugin
     */
    val dispatcherProvider: FoliaDispatcherProvider by lazy { FoliaPluginDispatcherProvider() }

    /**
     * Suspending on enable, runs blocking
     */
    override suspend fun onEnableSuspending() {}

    /**
     * Suspending on disable, runs blocking.
     *
     * Schedulers should not be used here as they will be cancelled, and they won't be guaranteed to complete.
     */
    override suspend fun onDisableSuspending() {}

    /**
     * Suspending on load, runs blocking
     */
    override suspend fun onLoadSuspending() {}

    /**
     * If this method is overridden, they should be called manually
     */
    override fun onEnable() = runBlocking(scope.coroutineContext) {
        // Wake schedulers?
        onEnableSuspending()
    }

    /**
     * If this method is overridden, they should be called manually
     */
    override fun onDisable() = runBlocking(scope.coroutineContext) {
        onDisableSuspending()
        scope.cancel("Plugin shutdown")
    }

    /**
     * If this method is overridden, they should be called manually
     */
    override fun onLoad() = runBlocking(scope.coroutineContext) {
        onLoadSuspending()
    }

}