package me.calebbfmv.blacksmithgui;

import me.calebbfmv.blacksmithgui.gui.menus.CategoryGUI;
import me.calebbfmv.blacksmithgui.listeners.AbilityListener;
import me.calebbfmv.blacksmithgui.listeners.EnchantListener;
import me.calebbfmv.blacksmithgui.listeners.InventoryListener;
import me.calebbfmv.blacksmithgui.managers.ConfigManager;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Tim [calebbfmv] on 10/1/2014.
 */
public class BlacksmithGUI extends JavaPlugin implements Listener, CommandExecutor {

    private static BlacksmithGUI instance;
    private ConfigManager manager;
    private Economy econ;

    public static BlacksmithGUI getInstance(){
        return instance;
    }

    @Override
    public void onEnable(){
        instance = this;
        this.manager = new ConfigManager(this);
        saveDefaultConfig();
        getLogger().info("is now enabled! Loading values.....");
        manager.load();
        setUpEcon();
        getServer().getPluginManager().registerEvents(new AbilityListener(), this);
        getServer().getPluginManager().registerEvents(new EnchantListener(), this);
        getServer().getPluginManager().registerEvents(new InventoryListener(), this);
        getCommand("openBSgui").setExecutor(this);
    }

    private void setUpEcon(){
        RegisteredServiceProvider<Economy> provider = getServer().getServicesManager().getRegistration(Economy.class);
        if(provider == null){
            return;
        }
        econ = provider.getProvider();
    }

    public ConfigManager getManager(){
        return manager;
    }

    public Economy getEcon(){
        return econ;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        CategoryGUI gui = CategoryGUI.create();
        gui.open((Player) sender);
        return true;
    }

}
