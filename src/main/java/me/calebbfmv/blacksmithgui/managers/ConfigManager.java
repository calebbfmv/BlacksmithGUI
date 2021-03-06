package me.calebbfmv.blacksmithgui.managers;

import me.calebbfmv.blacksmithgui.BlacksmithGUI;
import me.calebbfmv.blacksmithgui.ability.abilities.FallAbility;
import me.calebbfmv.blacksmithgui.ability.abilities.HealthAbility;
import me.calebbfmv.blacksmithgui.ability.abilities.SpeedAbility;
import me.calebbfmv.blacksmithgui.ability.abilities.StrengthAbility;
import me.calebbfmv.blacksmithgui.enchant.enchants.*;
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
        new ArrowEnchant();
        new BlindnessEnchant();
        new CriticalEnchant();
        new DamageEnchant();
        new FireballEnchant();
        new SlownessEnchant();
        //==============Passives=================//
        new FallAbility();
        new HealthAbility();
        new SpeedAbility();
        new StrengthAbility();
        //========================================//
        FileConfiguration config = plugin.getConfig();
        ConfigurationSection section = config.getConfigurationSection("prices");
        if(section == null){
            return;
        }
        for(String s : section.getKeys(false)){
            int i = section.getInt(s + ".cost");
            prices.put(Integer.parseInt(s), i);
        }
        new ArrowEnchant();
        new BlindnessEnchant();
        new CriticalEnchant();
        new DamageEnchant();
        new FireballEnchant();
        new SlownessEnchant();
        //==============Passives=================//
        new FallAbility();
        new HealthAbility();
        new SpeedAbility();
        new StrengthAbility();
        //========================================//
    }

    public int getCost(int level){
        return prices.get(level);
    }
}
