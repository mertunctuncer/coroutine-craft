package dev.peopo.coroutinecraft.folia.dispatcher


import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.isActive
import org.bukkit.Server
import org.bukkit.entity.Entity
import org.bukkit.plugin.Plugin
import kotlin.coroutines.CoroutineContext

internal class EntityDispatcher(
    private val plugin: Plugin,
    private val entity: Entity
) : CoroutineDispatcher() {

    override fun isDispatchNeeded(context: CoroutineContext): Boolean {
        return context.isActive
    }

    override fun dispatch(context: CoroutineContext, block: Runnable) {

        if (!context.isActive) return

        val success = entity.scheduler.execute(plugin, block, block, 1)

        val server: Server = plugin.server

        // If entity is already removed, dispatch to global?
        if (!success) server.globalRegionScheduler.execute(plugin, block)
    }
}