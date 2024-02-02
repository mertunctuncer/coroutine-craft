package dev.peopo.foliascope.api.plugin

import dev.peopo.foliascope.api.DispatcherProvider
import kotlinx.coroutines.CoroutineScope


interface SuspendingPlugin{

    val coroutineScope: CoroutineScope
    val dispatcherProvider: DispatcherProvider

    suspend fun onEnableSuspending()
    suspend fun onDisableSuspending()
    suspend fun onLoadSuspending()
}