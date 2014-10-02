package me.calebbfmv.blacksmithgui.managers;

import me.calebbfmv.blacksmithgui.BlacksmithGUI;
import me.calebbfmv.blacksmithgui.enchants.*;
import me.calebbfmv.blacksmithgui.interfaces.Upgrade;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

/**
 * Created by Tim [calebbfmv] on 10/1/2014.
 */
public class ConfigManager {

    private BlacksmithGUI plugin;

    public ConfigManager(BlacksmithGUI plugin){
        this.plugin = plugin;
    }

    private HashMap<Integer, Integer> prices = new HashMap<>();

    public void load(){
        FileConfiguration config = plugin.getConfig();
        ConfigurationSection section = config.getConfigurationSection("prices");
        if(section == null){
            return;
        }
        for(String s : section.getKeys(false)){
            int i = section.getInt(s + ".cost");
            prices.put(Integer.parseInt(s), i);
        }
        int cost = getCost(1);
        new ArrowEnchant(cost, "Arrow", Upgrade.ARROW);
        new BlindnessEnchant(cost, "Blindness", Upgrade.BLINDNESS);
        new CritEnchant(cost, "Critical", Upgrade.CRITICLE);
        new DamageEnchant(cost, "Damage", Upgrade.DAMAGE);
        new FireballEnchant(cost, "Fireball", Upgrade.FIREBALL);
    }

    public int getCost(int level){
        return prices.get(level);
    }
}
