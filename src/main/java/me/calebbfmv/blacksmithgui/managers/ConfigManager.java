package me.calebbfmv.blacksmithgui.managers;

import me.calebbfmv.blacksmithgui.BlacksmithGUI;
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
    }

    public int getCost(int level){
        return prices.get(level);
    }
}
