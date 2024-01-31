package dev.peopo.foliascope.api.dispatcher

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Runnable
import org.bukkit.entity.Entity
import org.bukkit.plugin.Plugin
import kotlin.coroutines.CoroutineContext

class EntityDispatcher(
    private val plugin: Plugin,
    private val entity: Entity
): CoroutineDispatcher() {

    override fun isDispatchNeeded(context: CoroutineContext): Boolean = true
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        if(!plugin.isEnabled) throw IllegalStateException("${plugin.name} has tried to dispatch a task while it is disabled")

        val success = entity.scheduler.execute(plugin, block, block, 1)

        // If entity is already removed, dispatch to global
        if(!success) plugin.server.globalRegionScheduler.execute(plugin, block)
    }
}