package dev.peopo.coroutinecraft.shared

import dev.peopo.coroutinecraft.shared.lifecycle.DispatcherProvider
import dev.peopo.coroutinecraft.shared.util.PluginContext
import dev.peopo.coroutinecraft.shared.lifecycle.ScopeHolder
import kotlinx.coroutines.CoroutineScope
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin


public val Plugin.coroutineScope: CoroutineScope
    get() = ScopeHolder.getScopeHolder(this).getScope(this)

public val Plugin.dispatcherProvider: DispatcherProvider
    get() = DispatcherProvider.getProvider(this)

context(PluginContext<*>)
public val Player.coroutineScope: CoroutineScope
    get() = ScopeHolder.getScopeHolder(plugin).getScope(this)