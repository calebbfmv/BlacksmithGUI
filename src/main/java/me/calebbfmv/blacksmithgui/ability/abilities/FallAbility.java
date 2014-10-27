package me.calebbfmv.blacksmithgui.ability.abilities;

import me.calebbfmv.blacksmithgui.BlacksmithGUI;
import me.calebbfmv.blacksmithgui.ability.Ability;
import me.calebbfmv.blacksmithgui.ability.AbilityType;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

/**
 * Created by Tim [calebbfmv] on 10/6/2014.
 */
public class FallAbility extends Ability {

    public FallAbility() {
        super(AbilityType.FALL);
    }

    @Override
    public void apply(Player player) {
        int level = getLevel(player);
        int percentage = 10 * level;
        player.setMetadata("fall", new FixedMetadataValue(BlacksmithGUI.getInstance(), percentage));
    }
}
