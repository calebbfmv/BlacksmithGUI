package me.calebbfmv.blacksmithgui.utils;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Tim [calebbfmv] on 10/1/2014.
 */
public class EPlayer {

    private Player player;
    private UUID uuid;
    private ItemStack chosenItem;
    private boolean isPromptedForItem = true;
    private static HashMap<UUID, EPlayer> players = new HashMap<>();

    public EPlayer(Player player) {
        this.player = player;
        this.uuid = player.getUniqueId();
        players.put(uuid, this);
    }

    public static EPlayer get(Player player){
        return players.get(player.getUniqueId());
    }

    public ItemStack getChosenItem(){
        return chosenItem;
    }

    public void setChosenItem(ItemStack item){
        this.chosenItem = item;
    }

    public boolean isPromptedForItem(){
        return isPromptedForItem;
    }

    public void setPromptedForItem(boolean val){
        this.isPromptedForItem = val;
    }

    public boolean hasFallAbility(){
        return player.hasMetadata("fall");
    }

    public boolean hasStrengthAbility(){
        return player.hasMetadata("str");
    }
}
