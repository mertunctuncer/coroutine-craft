package dev.peopo.coroutinecraft.folia

import dev.peopo.coroutinecraft.shared.lifecycle.ScopeHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.plugin.Plugin
import java.util.concurrent.ConcurrentHashMap

internal class FoliaScopeHolder(
    private val plugin: Plugin
) : ScopeHolder {

    private val quitListener = object : Listener {
        @EventHandler
        fun onPlayerQuit(event: PlayerQuitEvent) {
            val scope = playerScopes.remove(event.player)
            scope?.cancel()
        }
    }

    init {
        plugin.server.pluginManager.registerEvents(quitListener, plugin)
    }


    private val supervisorJob: Job by lazy {
        SupervisorJob()
    }

    private val supervisorScope: CoroutineScope by lazy {
        CoroutineScope(supervisorJob + plugin.globalDispatcher)
    }

    private val playerScopes by lazy {
        ConcurrentHashMap<Player, CoroutineScope>()
    }

    override fun <T> getScope(context: T): CoroutineScope = when(context) {
        is Player -> getPlayerScope(context)
        else -> supervisorScope
    }

    private fun getPlayerScope(player: Player): CoroutineScope {
        val playerSupervisor = SupervisorJob(supervisorJob)

        return playerScopes.getOrPut(player) {
            CoroutineScope(playerSupervisor + plugin.entityDispatcher(player))
        }
    }


    override fun close() {
        HandlerList.unregisterAll(quitListener)
        playerScopes.forEach { it.value.cancel() }
        playerScopes.clear()
        supervisorScope.cancel()
    }
}


public fun ScopeHolder.Companion.of(plugin: Plugin): ScopeHolder = FoliaScopeHolder(plugin)