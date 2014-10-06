package me.calebbfmv.blacksmithgui;

import me.calebbfmv.blacksmithgui.managers.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Tim [calebbfmv] on 10/1/2014.
 */
public class BlacksmithGUI extends JavaPlugin {

    private static BlacksmithGUI instance;
    private ConfigManager manager;

    public static BlacksmithGUI getInstance(){
        return instance;
    }

    @Override
    public void onEnable(){
        instance = this;
        this.manager = new ConfigManager(this);
        manager.load();
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getServer().getPluginManager().registerEvents(new DamageListener(), this);
    }


}
