package dev.peopo.foliascope.api.util

import org.bukkit.Server



private var cachedVersion: String? = null
val Server.serverVersion: String get() {
    cachedVersion?.let { return it }

    this.javaClass.packageName.replace(".", ",").split(",")[3].let {
        cachedVersion = it
        return it
    }
}