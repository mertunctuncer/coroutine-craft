package dev.peopo.coroutinecraft.bukkit

import dev.peopo.coroutinecraft.bukkit.dispatcher.AsynchronousDispatcher
import dev.peopo.coroutinecraft.bukkit.dispatcher.MainDispatcher
import dev.peopo.coroutinecraft.shared.lifecycle.DispatcherProvider
import org.bukkit.plugin.Plugin
import kotlin.coroutines.CoroutineContext

internal class BukkitDispatcherProvider(
    plugin: Plugin
) : DispatcherProvider {

    private val mainDispatcher = MainDispatcher(plugin)
    private val asynchronousDispatcher = AsynchronousDispatcher(plugin)

    override fun getDispatcher(): CoroutineContext = mainDispatcher
    override fun <T> getDispatcher(context: T): CoroutineContext = mainDispatcher
    override fun getAsynchronousDispatcher(): CoroutineContext = asynchronousDispatcher
}

public fun DispatcherProvider.Companion.of(plugin: Plugin): DispatcherProvider = BukkitDispatcherProvider(plugin)