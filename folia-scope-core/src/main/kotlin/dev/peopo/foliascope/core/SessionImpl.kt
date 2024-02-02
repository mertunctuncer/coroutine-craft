package dev.peopo.foliascope.core

import dev.peopo.foliascope.api.Session
import dev.peopo.foliascope.core.context.PluginElementImpl
import dev.peopo.foliascope.core.dispatcher.AsynchronousDispatcher
import dev.peopo.foliascope.core.dispatcher.EntityDispatcher
import dev.peopo.foliascope.core.dispatcher.GlobalDispatcher
import dev.peopo.foliascope.core.dispatcher.RegionDispatcher
import kotlinx.coroutines.CoroutineScope
import org.bukkit.World
import org.bukkit.entity.Entity
import org.bukkit.plugin.Plugin
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class SessionImpl(plugin: Plugin) : Session {
    override val scope: CoroutineScope = CoroutineScope(EmptyCoroutineContext + PluginElementImpl(plugin))

    override val globalDispatcher: CoroutineContext = GlobalDispatcher()
    override val asyncDispatcher: CoroutineContext = AsynchronousDispatcher()
    override fun entityDispatcher(entity: Entity): CoroutineContext = EntityDispatcher(entity)
    override fun regionDispatcher(chunkX: Int, chunkZ: Int, world: World): CoroutineContext = RegionDispatcher(world, chunkX, chunkZ)

}