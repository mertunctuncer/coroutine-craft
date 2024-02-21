package dev.peopo.coroutinecraft.shared.util

import org.bukkit.plugin.Plugin

public interface PluginContext<T : Plugin> {
    public val plugin: T
}
