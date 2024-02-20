package dev.peopo.coroutinecraft.bukkit

import dev.peopo.coroutinecraft.shared.dispatcherProvider
import kotlinx.coroutines.Dispatchers
import org.bukkit.plugin.Plugin
import kotlin.coroutines.CoroutineContext


public val Plugin.mainDispatcher: CoroutineContext
    get() = this.dispatcherProvider.getDispatcher()

public val Plugin.asyncDispatcher: CoroutineContext
    get() = this.dispatcherProvider.getAsynchronousDispatcher()

public fun Dispatchers.main(plugin: Plugin): CoroutineContext = plugin.mainDispatcher
public fun Dispatchers.async(plugin: Plugin): CoroutineContext = plugin.asyncDispatcher


// TODO ADD CONTEXT RECEIVERS