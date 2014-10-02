package me.calebbfmv.blacksmithgui;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Tim [calebbfmv] on 10/1/2014.
 */
public class BlacksmithGUI extends JavaPlugin {

    private static BlacksmithGUI instance;

    public static BlacksmithGUI getInstance(){
        return instance;
    }

    @Override
    public void onEnable(){
        instance = this;
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);
        getServer().getPluginManager().registerEvents(new DamageListener(), this);
    }


}
