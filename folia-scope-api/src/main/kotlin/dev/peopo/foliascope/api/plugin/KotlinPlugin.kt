package dev.peopo.foliascope.api.plugin

import dev.peopo.foliascope.api.DispatcherProvider
import dev.peopo.foliascope.core.PluginDispatcherProvider
import dev.peopo.foliascope.core.context.PluginElementImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.runBlocking
import org.bukkit.plugin.java.JavaPlugin
import kotlin.coroutines.EmptyCoroutineContext

abstract class KotlinPlugin: SuspendingPlugin, JavaPlugin() {

    override val coroutineScope: CoroutineScope  = CoroutineScope(EmptyCoroutineContext + PluginElementImpl(this))
    override val dispatcherProvider: DispatcherProvider = PluginDispatcherProvider()

    override suspend fun onEnableSuspending() {}

    override suspend fun onDisableSuspending() {}

    override suspend fun onLoadSuspending() {}

    override fun onEnable() = runBlocking {
        // Wake schedulers?
        onEnableSuspending()
    }

    override fun onDisable() = runBlocking {
        onDisableSuspending()
        coroutineScope.cancel()
    }

    override fun onLoad() = runBlocking {
        onLoadSuspending()
    }

}