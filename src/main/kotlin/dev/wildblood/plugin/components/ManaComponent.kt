package dev.wildblood.plugin.components

import com.hypixel.hytale.codec.Codec
import com.hypixel.hytale.codec.KeyedCodec
import com.hypixel.hytale.codec.builder.BuilderCodec
import com.hypixel.hytale.component.Component
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import java.util.function.BiConsumer
import java.util.function.Function
import java.util.function.Supplier


class ManaComponent(
    var currentMana: Float = 100.0f,
    var maxMana: Float = 100.0f,
    var regenRate: Float = 1.0f
) : Component<EntityStore> {
    companion object {
        val CODEC: BuilderCodec<ManaComponent> =
            BuilderCodec.builder(ManaComponent::class.java) { ManaComponent() }
                .append(
                    KeyedCodec("MaxMana", Codec.FLOAT),
                    { c, v -> c.maxMana = v },
                    { c -> c.maxMana }
                ).add()
                .append(
                    KeyedCodec("CurrentMana", Codec.FLOAT),
                    { c, v -> c.currentMana = v },
                    { c -> c.currentMana }
                ).add()
                .append(
                    KeyedCodec("RegenRate", Codec.FLOAT),
                    { c, v -> c.regenRate = v },
                    { c -> c.regenRate }
                ).add()
                .build()
    }

    constructor() : this(100.0f, 100.0f, 1.0f)

    override fun clone(): Component<EntityStore> =
        ManaComponent(this.maxMana, this.currentMana, this.regenRate)
}