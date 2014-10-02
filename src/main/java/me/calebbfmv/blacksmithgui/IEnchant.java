package me.calebbfmv.blacksmithgui;

import org.bukkit.entity.Player;

/**
 * Created by Tim [calebbfmv] on 9/12/2014.
 */
public interface IEnchant {

    public void action(Player player);

    public String[] getLore();

    public int getTradeValue();

    public boolean doOnRight();

    public String getName();

}
