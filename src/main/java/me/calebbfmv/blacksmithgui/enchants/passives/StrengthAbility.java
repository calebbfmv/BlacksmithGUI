package me.calebbfmv.blacksmithgui.enchants.passives;

import me.calebbfmv.blacksmithgui.BlacksmithGUI;
import me.calebbfmv.blacksmithgui.interfaces.PassiveAbility;
import me.calebbfmv.blacksmithgui.interfaces.Upgrade;
import me.calebbfmv.blacksmithgui.utils.EPlayer;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

/**
 * Created by Tim [calebbfmv] on 10/1/2014.
 */
public class StrengthAbility extends PassiveAbility {

    public StrengthAbility(int cost) {
        super(cost, Upgrade.STRENGTH);
    }

    @Override
    public void activate(Player player) {
        int level = EPlayer.get(player).getLevel(getUpgrade());
        double percentage = level / 10;
        player.setMetadata("str", new FixedMetadataValue(BlacksmithGUI.getInstance(), percentage));

    }
}
