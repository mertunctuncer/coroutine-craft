package dev.peopo.foliascope.core

import dev.peopo.foliascope.api.FoliaDispatcherProvider
import dev.peopo.foliascope.core.dispatcher.AsynchronousDispatcher
import dev.peopo.foliascope.core.dispatcher.EntityDispatcher
import dev.peopo.foliascope.core.dispatcher.GlobalDispatcher
import dev.peopo.foliascope.core.dispatcher.RegionDispatcher
import org.bukkit.World
import org.bukkit.entity.Entity
import kotlin.coroutines.CoroutineContext

class FoliaPluginDispatcherProvider : FoliaDispatcherProvider {

    override val globalDispatcher: CoroutineContext = GlobalDispatcher()
    override val asyncDispatcher: CoroutineContext = AsynchronousDispatcher()
    override fun entityDispatcher(entity: Entity): CoroutineContext = EntityDispatcher(entity)
    override fun regionDispatcher(chunkX: Int, chunkZ: Int, world: World): CoroutineContext = RegionDispatcher(world, chunkX, chunkZ)
}