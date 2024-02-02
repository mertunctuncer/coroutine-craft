package dev.peopo.foliascope.api.plugin


interface SuspendingPlugin{
    suspend fun onEnableSuspending()
    suspend fun onDisableSuspending()
    suspend fun onLoadSuspending()
}