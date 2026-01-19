package dev.wildblood.plugin.ui.hud

import com.hypixel.hytale.server.core.entity.entities.player.hud.CustomUIHud
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder
import com.hypixel.hytale.server.core.universe.PlayerRef

class ManaBar(playerRef: PlayerRef) : CustomUIHud(playerRef) {
    var manaPercent: Float = 0.0f;

    override fun build(builder: UICommandBuilder) {
        builder.append("#hud-root", "ui/custom/mana/mana.ui");
        //builder.set("#health-bar-fill", manaPercent);
    }

    fun updateMana(manaPercent: Float) {
        this.manaPercent = manaPercent;
        //val builder = UICommandBuilder();
        //builder.set("#health-bar-fill", manaPercent);
        //update(false, builder);
    }
}