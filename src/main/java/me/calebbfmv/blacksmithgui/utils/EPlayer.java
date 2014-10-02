package me.calebbfmv.blacksmithgui.utils;

import me.calebbfmv.blacksmithgui.interfaces.Upgrade;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Tim [calebbfmv] on 10/1/2014.
 */
public class EPlayer {

    private Player player;
    private UUID uuid;
    private HashMap<Upgrade, Integer>  upgrades;
    private static HashMap<UUID, EPlayer> players = new HashMap<>();

    public EPlayer(Player player) {
        this.player = player;
        this.upgrades = new HashMap<>();
        this.uuid = player.getUniqueId();
        players.put(uuid, this);
    }

    public static EPlayer get(Player player){
        return players.get(player.getUniqueId());
    }

    public int getLevel(Upgrade upgrade){
        return upgrades.get(upgrade);
    }

    public void setLevel(Upgrade upgrade, int i){
        upgrades.put(upgrade, i);
    }
}
