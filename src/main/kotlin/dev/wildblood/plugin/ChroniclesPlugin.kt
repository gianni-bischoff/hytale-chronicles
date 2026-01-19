package dev.wildblood.plugin

import com.hypixel.hytale.logger.HytaleLogger
import com.hypixel.hytale.server.core.plugin.JavaPlugin
import com.hypixel.hytale.server.core.plugin.JavaPluginInit

class ChroniclesPlugin(init: JavaPluginInit) : JavaPlugin(init) {

    companion object {
        val LOGGER: HytaleLogger = HytaleLogger.forEnclosingClass()
    }

    override fun setup() {
        LOGGER.atInfo().log("Setting up plugin " + this.name)
        this.commandRegistry.registerCommand(ExampleCommand(this.name, this.manifest.version.toString()))
    }



}