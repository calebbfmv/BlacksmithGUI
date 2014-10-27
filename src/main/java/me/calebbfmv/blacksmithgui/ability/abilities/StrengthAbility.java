package me.calebbfmv.blacksmithgui.ability.abilities;

import me.calebbfmv.blacksmithgui.BlacksmithGUI;
import me.calebbfmv.blacksmithgui.ability.Ability;
import me.calebbfmv.blacksmithgui.ability.AbilityType;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

/**
 * Created by Tim [calebbfmv] on 10/6/2014.
 */
public class StrengthAbility extends Ability {

    public StrengthAbility() {
        super(AbilityType.STRENGTH);
    }

    @Override
    public void apply(Player player) {
        int level = getLevel(player);
        double percentage = level / 10;
        player.setMetadata("str", new FixedMetadataValue(BlacksmithGUI.getInstance(), percentage));
    }
}
