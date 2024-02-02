package dev.peopo.foliascope.api.plugin

import dev.peopo.foliascope.api.Session


interface KotlinPlugin{

    val session: Session
    suspend fun onEnableSuspending()
    suspend fun onDisableSuspending()
    suspend fun onLoadSuspending()
}