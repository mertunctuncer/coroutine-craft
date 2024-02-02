package dev.peopo.foliascope.api.context

import dev.peopo.foliascope.api.plugin.toKotlinPlugin
import dev.peopo.foliascope.api.util.IllegalContextException
import kotlinx.coroutines.CoroutineScope
import org.bukkit.plugin.Plugin
import kotlin.coroutines.CoroutineContext

/**
 * Plugin context element
 *
 * @see CoroutineContext
 */
interface PluginElement : CoroutineContext.Element{

    /**
     * Plugin instance that is associated with the context
     */
    val plugin: Plugin

    override val key: CoroutineContext.Key<*> get() = Key
    companion object Key: CoroutineContext.Key<PluginElement>
}

/**
 * Gets the plugin associated with the context
 *
 * @return the plugin
 * @throws IllegalContextException if the context does not contain a plugin
 */
@Suppress("unused")
fun CoroutineContext.getPlugin() = this[PluginElement]?.plugin ?: throw IllegalContextException("Context does not contain PluginElement")

/**
 * Gets the plugin associated with the context and casts it to a KotlinPlugin
 *
 * @return the kotlin plugin
 * @throws IllegalContextException if the context does not contain a plugin
 * @throws ClassCastException if the plugin is not a KotlinPlugin
 */
@Suppress("unused")
fun CoroutineContext.getKotlinPlugin() = getPlugin().toKotlinPlugin()

/**
 * Gets the plugin associated with the CoroutineContext
 * @return the plugin
 * @throws IllegalContextException if the context does not contain a plugin
 */
@Suppress("unused")
fun CoroutineScope.getPlugin() = this.coroutineContext.getPlugin()

/**
 * Gets the plugin associated with the CoroutineContext and casts it to a KotlinPlugin
 * @return the kotlin plugin
 * @throws IllegalContextException if the context does not contain a plugin
 * @throws ClassCastException if the plugin is not a KotlinPlugin
 */
@Suppress("unused")
fun CoroutineScope.getKotlinPlugin() = this.coroutineContext.getKotlinPlugin()