package me.calebbfmv.blacksmithgui.enchants.passives;

import me.calebbfmv.blacksmithgui.interfaces.PassiveAbility;
import me.calebbfmv.blacksmithgui.interfaces.Upgrade;
import me.calebbfmv.blacksmithgui.utils.EPlayer;
import org.bukkit.entity.Player;

/**
 * Created by Tim [calebbfmv] on 10/1/2014.
 */
public class SpeedAbility extends PassiveAbility {

    public SpeedAbility(int cost) {
        super(cost, Upgrade.SPEED);
    }

    @Override
    public void activate(Player player) {
        int level = EPlayer.get(player).getLevel(this.getUpgrade());
        float speed = player.getWalkSpeed();
        float speedToAdd = level / 10;
        float newSpeed = 0.2F * speedToAdd;
        if(speed == newSpeed){
            return;
        }
        player.setWalkSpeed(newSpeed);    }
}
