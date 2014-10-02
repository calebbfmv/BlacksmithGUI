package me.calebbfmv.blacksmithgui.interfaces;

import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by Tim [calebbfmv] on 9/12/2014.
 */
public interface IEnchant {

    public void action(EntityDamageByEntityEvent event);

    public int getCost();

    public boolean doOnRight();

    public String getName();

}
