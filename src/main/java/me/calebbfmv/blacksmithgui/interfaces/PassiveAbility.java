package me.calebbfmv.blacksmithgui.interfaces;

import org.apache.commons.lang.StringUtils;
import org.bukkit.entity.Player;

import java.util.HashMap;

/**
 * Created by Tim [calebbfmv] on 10/2/2014.
 */
public abstract class PassiveAbility {

    private int cost;
    private String name;
    private Upgrade upgrade;
    private static HashMap<String, PassiveAbility> abilities = new HashMap<>();

    public PassiveAbility(int cost, Upgrade upgrade){
        this.name = StringUtils.capitalize(upgrade.name()) + " Ability";
        this.cost = cost;
        this.upgrade = upgrade;
        abilities.put(name.toLowerCase(), this);
    }

    public String getName(){
        return name;
    }

    public int getCost() {
        return cost;
    }

    public Upgrade getUpgrade() {
        return upgrade;
    }

    public abstract void activate(Player player);

    public static HashMap<String, PassiveAbility> getAbilities(){
        return abilities;
    }
}
