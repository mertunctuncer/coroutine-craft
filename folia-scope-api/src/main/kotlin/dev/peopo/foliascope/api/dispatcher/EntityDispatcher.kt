package dev.peopo.foliascope.api.dispatcher

import kotlinx.coroutines.Runnable
import org.bukkit.entity.Entity
import kotlin.coroutines.CoroutineContext

class EntityDispatcher(
    private val entity: Entity
): FoliaDispatcher() {
    override fun dispatch(context: CoroutineContext, block: Runnable) {
        val plugin = getPlugin() ?: throw IllegalStateException("EntityDispatcher can only be used within a plugin context")

        if(!plugin.isEnabled) throw IllegalStateException("${plugin.name} has tried to dispatch a task while it is disabled")

        val success = entity.scheduler.execute(plugin, block, block, 1)

        // If entity is already removed, dispatch to global?
        if(!success) plugin.server.globalRegionScheduler.execute(plugin, block)
    }
}