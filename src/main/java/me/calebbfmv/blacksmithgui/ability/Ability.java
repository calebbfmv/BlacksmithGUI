package me.calebbfmv.blacksmithgui.ability;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Tim [calebbfmv] on 10/6/2014.
 */
public abstract class Ability {

    protected AbilityType type;
    private static HashMap<AbilityType, Ability> abilities = new HashMap<>();
    private HashMap<UUID, Integer> levels;

    public Ability(AbilityType type){
        this.type = type;
        this.levels = new HashMap<>();
        abilities.put(type, this);
    }

    public abstract void apply(Player player);

    public int getLevel(Player player){
        if(levels.get(player.getUniqueId()) == null){
            levels.put(player.getUniqueId(), 1);
            return 1;
        }
        return levels.get(player.getUniqueId());
    }

    public void setLevel(Player player, int level){
        levels.put(player.getUniqueId(), level);
    }

    public static Ability get(AbilityType type){
        return abilities.get(type);
    }

    public static Ability[] getAbilities(){
        return abilities.values().toArray(new Ability[abilities.size()]);
    }
}
