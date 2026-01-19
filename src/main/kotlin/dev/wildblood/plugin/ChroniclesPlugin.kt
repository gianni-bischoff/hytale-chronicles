package dev.wildblood.plugin

import com.hypixel.hytale.assetstore.AssetRegistry
import com.hypixel.hytale.assetstore.event.LoadedAssetsEvent
import com.hypixel.hytale.assetstore.event.RemovedAssetsEvent
import com.hypixel.hytale.assetstore.map.DefaultAssetMap
import com.hypixel.hytale.builtin.crafting.CraftingPlugin
import com.hypixel.hytale.logger.HytaleLogger
import com.hypixel.hytale.server.core.asset.type.item.config.CraftingRecipe
import com.hypixel.hytale.server.core.asset.type.item.config.Item
import com.hypixel.hytale.server.core.plugin.JavaPlugin
import com.hypixel.hytale.server.core.plugin.JavaPluginInit
import java.util.function.Consumer

class ChroniclesPlugin(init: JavaPluginInit) : JavaPlugin(init) {

    companion object {
        val LOGGER: HytaleLogger = HytaleLogger.forEnclosingClass()
    }

    override fun setup() {
        LOGGER.atInfo().log("Setting up plugin " + this.name)
        this.commandRegistry.registerCommand(ExampleCommand(this.name, this.manifest.version.toString()))

        LOGGER.atInfo().log("Registered RaceAsset")
    }
}