package dev.peopo.foliascope.api


interface KotlinPlugin{

    val session: Session
    suspend fun onEnableSuspending()
    suspend fun onDisableSuspending()
    suspend fun onLoadSuspending()
}