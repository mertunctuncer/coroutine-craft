package dev.peopo.foliascope.api.util

import kotlinx.coroutines.CoroutineScope

interface Scoped {

    val scope: CoroutineScope
}