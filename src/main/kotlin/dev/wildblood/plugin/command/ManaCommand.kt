package dev.wildblood.plugin.command

import com.hypixel.hytale.component.ComponentType
import com.hypixel.hytale.component.Ref
import com.hypixel.hytale.component.Store
import com.hypixel.hytale.server.core.Message
import com.hypixel.hytale.server.core.command.system.CommandContext
import com.hypixel.hytale.server.core.command.system.arguments.system.RequiredArg
import com.hypixel.hytale.server.core.command.system.arguments.types.ArgTypes
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractCommandCollection
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand
import com.hypixel.hytale.server.core.entity.entities.Player
import com.hypixel.hytale.server.core.universe.PlayerRef
import com.hypixel.hytale.server.core.universe.world.World
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import dev.wildblood.plugin.components.ManaComponent
import dev.wildblood.plugin.ui.hud.ManaBar


class ManaCommand(
    manaType: ComponentType<EntityStore, ManaComponent>
) : AbstractCommandCollection("mana", "server.commands.mana") {

    init {
        this.addSubCommand(DrainManaCommand(manaType))
        this.addSubCommand(CheckManaCommand(manaType))
    }

    class DrainManaCommand(
        val manaType: ComponentType<EntityStore, ManaComponent>
    ) : AbstractPlayerCommand("drain", "server.commands.mana.drain") {
        val amountArg: RequiredArg<Float> = this.withRequiredArg<Float>(
            "amount",
            "server.commands.mana.drain.amount",
            ArgTypes.FLOAT
        )

        override fun execute(
            context: CommandContext,
            store: Store<EntityStore>,
            ref: Ref<EntityStore>,
            playerRef: PlayerRef,
            world: World
        ) {
            val amount = amountArg.get(context)

            val manaComponent = store.ensureAndGetComponent(ref, manaType) ?: return

            manaComponent.currentMana -= amount

            context.sendMessage(Message.raw("Drained $amount Mana from yourself and you now have ${manaComponent.currentMana}."))
        }
    }

    class CheckManaCommand(
        val manaType: ComponentType<EntityStore, ManaComponent>
    ) : AbstractPlayerCommand("check", "server.commands.mana.check") {
        override fun execute(
            context: CommandContext,
            store: Store<EntityStore>,
            ref: Ref<EntityStore>,
            playerRef: PlayerRef,
            world: World
        ) {
            val manaComponent = store.ensureAndGetComponent(ref, manaType)

            context.sendMessage(Message.raw("You have ${manaComponent.currentMana} Mana."))

            /**
            val playerComponent: Player = store.getComponent(ref, Player.getComponentType())!!
            val hudManager = playerComponent.hudManager


            val manaBar = ManaBar(playerRef)
            hudManager.setCustomHud(playerRef, manaBar)
            manaBar.updateMana(manaComponent.currentMana / manaComponent.maxMana)
*/

        }
    }
}