package me.calebbfmv.blacksmithgui.listeners;

import me.calebbfmv.blacksmithgui.BlacksmithGUI;
import me.calebbfmv.blacksmithgui.ability.Ability;
import me.calebbfmv.blacksmithgui.ability.AbilityType;
import me.calebbfmv.blacksmithgui.enchant.CustomEnchant;
import me.calebbfmv.blacksmithgui.enchant.EnchantmentType;
import me.calebbfmv.blacksmithgui.utils.EPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;


/**
 * Created by Tim [calebbfmv] on 10/6/2014.
 */
public class AbilityListener implements Listener {

    @EventHandler
    public void onFall(EntityDamageEvent event){
        if(!(event.getEntity() instanceof Player)){
            return;
        }
        Player player = (Player) event.getEntity();
        DamageCause cause = event.getCause();
        if(cause != DamageCause.FALL){
            return;
        }
        EPlayer ePlayer = EPlayer.get(player);
        if(ePlayer == null){
            return;
        }
        if(!ePlayer.hasFallAbility()){
            return;
        }
        int level = player.getMetadata("fall").get(0).asInt();
        float percentage = level / 100;
        event.setDamage(event.getDamage() * percentage);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        CustomEnchant enchant = CustomEnchant.getFromItem(player);
        if(enchant == null){
            return;
        }
        if(event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK){
            return;
        }
        if(!enchant.isRightClick()){
            return;
        }
        enchant.action(player);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        Player player = event.getEntity();
        player.removeMetadata("fall", BlacksmithGUI.getInstance());
        player.removeMetadata("str", BlacksmithGUI.getInstance());
        player.getActivePotionEffects().clear();
        player.setMaxHealth(20.0D);
        for(AbilityType abilityType :  AbilityType.values()){
            Ability.get(abilityType).setLevel(player, 0);
        }
        for(EnchantmentType enchantmentType : EnchantmentType.values()){
            CustomEnchant.get(enchantmentType).setLevel(player, 0);
        }

    }
}
