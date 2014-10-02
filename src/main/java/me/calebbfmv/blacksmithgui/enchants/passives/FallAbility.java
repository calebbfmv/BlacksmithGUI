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
public class FallAbility extends PassiveAbility {

    public FallAbility(int cost) {
        super(cost, Upgrade.FALL);
    }

    @Override
    public void activate(Player player) {
        EPlayer ePlayer = EPlayer.get(player);
        int level = ePlayer.getLevel(getUpgrade());
        int percentage = 10 * level;
        player.setMetadata("fall", new FixedMetadataValue(BlacksmithGUI.getInstance(), percentage));
    }
}
