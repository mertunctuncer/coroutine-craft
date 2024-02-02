package dev.peopo.foliascope.api

import dev.peopo.foliascope.core.SessionImpl
import kotlinx.coroutines.cancel
import kotlinx.coroutines.runBlocking
import org.bukkit.plugin.java.JavaPlugin

abstract class KotlinPluginAdapter: KotlinPlugin, JavaPlugin() {
    override val session: Session = SessionImpl(this)

    override suspend fun onEnableSuspending() {
    }

    override suspend fun onDisableSuspending() {
    }

    override suspend fun onLoadSuspending() {
    }


    override fun onEnable() = runBlocking {
        // Wake schedulers?
        onEnableSuspending()
    }

    override fun onDisable() = runBlocking {
        onDisableSuspending()
        session.scope.cancel()
    }

    override fun onLoad() = runBlocking {
        onLoadSuspending()
    }

}