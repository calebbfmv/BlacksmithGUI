package me.calebbfmv.blacksmithgui.gui.guis;

import me.calebbfmv.blacksmithgui.gui.Button;
import me.calebbfmv.blacksmithgui.gui.GUI;
import me.calebbfmv.blacksmithgui.interfaces.Enchant;
import me.calebbfmv.blacksmithgui.interfaces.Upgrade;
import me.calebbfmv.blacksmithgui.utils.EPlayer;
import me.calebbfmv.blacksmithgui.utils.EnchantedItem;
import me.calebbfmv.blacksmithgui.utils.RomanNumeral;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Tim [calebbfmv] on 10/1/2014.
 */
public class UpgradeGUI extends GUI{

    public UpgradeGUI(String name, Button[] buttons) {
        super(name, buttons);
    }

    public static UpgradeGUI create(){
        Button[] buttons = new Button[27];
        Enchant[] enchants = Enchant.getEnchants().values().toArray(new Enchant[Enchant.getEnchants().size()]);
        for(int i = 1; i < enchants.length; i++){
            if(i == 9 || i == 10 || i == 11){
                continue;
            }
            final Enchant enchant = enchants[i];
            final ItemStack item = create(Material.ENCHANTED_BOOK, ChatColor.LIGHT_PURPLE + enchant.getName());
            buttons[i] = create(item, new Button.ClickExecutor() {
                @Override
                public void click(Player player) {
                    EPlayer ePlayer = EPlayer.get(player);
                    if(ePlayer.getChosenItem() == null){
                        return;
                    }
                    ItemStack itemStack = ePlayer.getChosenItem();
                    EnchantedItem enchantedItem = new EnchantedItem(itemStack);
                    enchantedItem.withEnchant(enchant);
                    enchantedItem.withUpgrade(1);
                    enchantedItem.give(player);
                    player.closeInventory();
                }
            });
        }
        buttons[1] = create(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14), ChatColor.BLACK + " ");
        buttons[9] = create(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14), ChatColor.BLACK + " ");
        return new UpgradeGUI("Enchants", buttons);
    }

    public static UpgradeGUI create(final Upgrade upgrade){
        Button[] buttons = new Button[27];
        buttons[1] = create(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14), ChatColor.BLACK + " ");
        buttons[9] = create(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14), ChatColor.BLACK + " ");
        for(int i = 1; i <= 10;i++){
            final ItemStack item = create(Material.GOLD_INGOT, ChatColor.GOLD + "Upgrade: " + RomanNumeral.get(i).name());
            final int fI = i;
            Button button = create(item, new Button.ClickExecutor() {
                @Override
                public void click(Player player) {
                    EPlayer ePlayer = EPlayer.get(player);
                    if(ePlayer.getChosenItem() == null){
                        return;
                    }
                    ItemStack itemStack = ePlayer.getChosenItem();
                    EnchantedItem enchantedItem = new EnchantedItem(itemStack);
                    enchantedItem.withEnchant(Enchant.getFromItem(itemStack));
                    enchantedItem.withUpgrade(1);
                    enchantedItem.give(player);
                    player.closeInventory();
                    ePlayer.setLevel(upgrade, fI);
                }
            });
            if(i + 10 <= 17) {
                buttons[i + 10] = button;
            }
            if(i + 10 > 17){
                buttons[18 + (i % 10)] = button;
            }
        }
        return new UpgradeGUI("Upgrades", buttons);
    }

}
