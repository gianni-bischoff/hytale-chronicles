package dev.wildblood.plugin

import com.hypixel.hytale.component.ComponentType
import com.hypixel.hytale.logger.HytaleLogger
import com.hypixel.hytale.server.core.plugin.JavaPlugin
import com.hypixel.hytale.server.core.plugin.JavaPluginInit
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import dev.wildblood.plugin.command.ManaCommand
import dev.wildblood.plugin.components.ManaComponent
import dev.wildblood.plugin.systems.ManaRegenSystem

class ChroniclesPlugin(init: JavaPluginInit) : JavaPlugin(init) {

    companion object {
        val LOGGER: HytaleLogger = HytaleLogger.forEnclosingClass()

        lateinit var MANA_COMPONENT_TYPE: ComponentType<EntityStore, ManaComponent>;
    }

    override fun setup() {
        LOGGER.atInfo().log("Setting up plugin " + this.name)

        MANA_COMPONENT_TYPE = entityStoreRegistry.registerComponent(
            ManaComponent::class.java,
            "Mana",
            ManaComponent.CODEC
        )

        entityStoreRegistry.registerSystem(ManaRegenSystem(MANA_COMPONENT_TYPE))
        commandRegistry.registerCommand(ManaCommand(MANA_COMPONENT_TYPE))
    }
}