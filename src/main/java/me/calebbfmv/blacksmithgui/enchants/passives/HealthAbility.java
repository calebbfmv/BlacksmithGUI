package me.calebbfmv.blacksmithgui.enchants.passives;

import me.calebbfmv.blacksmithgui.interfaces.PassiveAbility;
import me.calebbfmv.blacksmithgui.interfaces.Upgrade;
import me.calebbfmv.blacksmithgui.utils.EPlayer;
import org.bukkit.entity.Player;

/**
 * Created by Tim [calebbfmv] on 10/1/2014.
 */
public class HealthAbility extends PassiveAbility {

    public HealthAbility(int cost) {
        super(cost, Upgrade.HEALTH);
    }

    @Override
    public void activate(Player player) {
        double max = player.getMaxHealth();
        int level = EPlayer.get(player).getLevel(getUpgrade());
        double set = max * (level / 10);
        set += max;
        player.setMaxHealth(set);
    }
}
