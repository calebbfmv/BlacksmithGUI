package me.calebbfmv.blacksmithgui.interfaces;

import org.bukkit.entity.Player;

/**
 * Created by Tim [calebbfmv] on 10/2/2014.
 */
public abstract class PassiveAbility {

    private int cost;
    private Upgrade upgrade;

    public PassiveAbility(int cost, Upgrade upgrade){
        this.cost = cost;
        this.upgrade = upgrade;
    }

    public int getCost() {
        return cost;
    }

    public Upgrade getUpgrade() {
        return upgrade;
    }

    public abstract void activate(Player player);
}
