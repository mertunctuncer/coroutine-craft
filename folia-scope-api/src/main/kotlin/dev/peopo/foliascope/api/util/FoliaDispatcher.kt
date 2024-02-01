package dev.peopo.foliascope.api.util

import io.papermc.paper.threadedregions.scheduler.ScheduledTask

import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.plugin.Plugin
import java.util.concurrent.TimeUnit
import java.util.function.Consumer

interface FoliaDispatcher {
    val plugin: Plugin
    fun runTask(onRun: Consumer<ScheduledTask>) = plugin.server.globalRegionScheduler.run(plugin, onRun)
    fun runTask(location: Location, onRun: Consumer<ScheduledTask>) = plugin.server.regionScheduler.run(plugin, location, onRun)
    fun runTask(entity: Entity, onRetire: Runnable? = null, onRun: Consumer<ScheduledTask>) = entity.scheduler.run(plugin, onRun, onRetire)
    fun runTaskAsync(onRun: Consumer<ScheduledTask>) = plugin.server.asyncScheduler.runNow(plugin, onRun)
    fun runTaskAsyncronously(onRun: Consumer<ScheduledTask>) = runTaskAsync(onRun)


    fun runTaskLater(delay: Ticks, onRun: Consumer<ScheduledTask>) = plugin.server.globalRegionScheduler.runDelayed(plugin, onRun, delay.amount)
    fun runTaskLater(location: Location, delay: Ticks, onRun: Consumer<ScheduledTask>) = plugin.server.regionScheduler.runDelayed(plugin, location, onRun, delay.amount)
    fun runTaskLater(entity: Entity, delay: Ticks, onRetire: Runnable? = null, onRun: Consumer<ScheduledTask>) = entity.scheduler.runDelayed(plugin, onRun, onRetire, delay.amount)
    fun runTaskLaterAsync(delay: Ticks, onRun: Consumer<ScheduledTask>) = plugin.server.asyncScheduler.runDelayed(plugin, onRun, delay.millis, TimeUnit.MILLISECONDS)
    fun runTaskLaterAsyncronously(delay: Ticks, onRun: Consumer<ScheduledTask>) = runTaskLaterAsync(delay, onRun)


    fun runTaskTimer(delay: Ticks = 0.ticks, period: Ticks, onRun: Consumer<ScheduledTask>) = plugin.server.globalRegionScheduler.runAtFixedRate(plugin, onRun, delay.amount, period.amount)
    fun runTaskTimer(location: Location, delay: Ticks = 0.ticks, period: Ticks, onRun: Consumer<ScheduledTask>) = plugin.server.regionScheduler.runAtFixedRate(plugin, location, onRun, delay.amount, period.amount)
    fun runTaskTimer(entity: Entity, delay: Ticks = 0.ticks, period: Ticks, onRetire: Runnable? = null, onRun: Consumer<ScheduledTask>) = entity.scheduler.runAtFixedRate(plugin, onRun, onRetire, delay.amount, period.amount)
    fun runTaskAsync(delay: Ticks = 0.ticks, period: Ticks, onRun: Consumer<ScheduledTask>) = plugin.server.asyncScheduler.runAtFixedRate(plugin, onRun, delay.millis, period.millis, TimeUnit.MILLISECONDS)
    fun runTaskAsyncronously(delay: Ticks = 0.ticks, period: Ticks, onRun: Consumer<ScheduledTask>) = runTaskAsync(delay, period, onRun)
}