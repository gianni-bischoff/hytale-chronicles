package dev.wildblood.plugin.systems

import com.hypixel.hytale.component.ArchetypeChunk
import com.hypixel.hytale.component.CommandBuffer
import com.hypixel.hytale.component.ComponentType
import com.hypixel.hytale.component.Store
import com.hypixel.hytale.component.query.Query
import com.hypixel.hytale.component.system.tick.EntityTickingSystem
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import dev.wildblood.plugin.ChroniclesPlugin
import dev.wildblood.plugin.components.ManaComponent

class ManaRegenSystem(
    val manaType: ComponentType<EntityStore, ManaComponent>
) : EntityTickingSystem<EntityStore>() {

    private val query: Query<EntityStore> = manaType

    override fun tick(
        dt: Float,
        index: Int,
        chunk: ArchetypeChunk<EntityStore>,
        store: Store<EntityStore>,
        commandBuffer: CommandBuffer<EntityStore>
    ) {
        val manaComponent = chunk.getComponent(index, manaType) ?: return

        manaComponent.currentMana += (manaComponent.regenRate * dt)
            .coerceAtMost(manaComponent.maxMana - manaComponent.currentMana)
    }

    override fun getQuery(): Query<EntityStore> = query

}