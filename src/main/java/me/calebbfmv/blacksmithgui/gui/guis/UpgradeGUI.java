package me.calebbfmv.blacksmithgui.gui.guis;

import me.calebbfmv.blacksmithgui.gui.Button;
import me.calebbfmv.blacksmithgui.gui.GUI;
import me.calebbfmv.blacksmithgui.interfaces.Enchant;
import me.calebbfmv.blacksmithgui.interfaces.Upgrade;
import me.calebbfmv.blacksmithgui.utils.EPlayer;
import me.calebbfmv.blacksmithgui.utils.RomanNumeral;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by Tim [calebbfmv] on 10/1/2014.
 */
public class UpgradeGUI extends GUI{

    public UpgradeGUI(String name, Button[] buttons) {
        super(name, buttons);
    }

    public static UpgradeGUI create(final Player player){
        final EPlayer ePlayer = EPlayer.get(player);
        Button[] buttons = new Button[27];
        Enchant[] enchants = Enchant.getEnchants().values().toArray(new Enchant[Enchant.getEnchants().size()]);
        for(int i = 0; i < enchants.length; i++){
            Enchant enchant = enchants[i];
        }
        return new UpgradeGUI("Enchants", buttons);
    }

    public static UpgradeGUI create(final Upgrade upgrade, Player player){
        Button[] buttons = new Button[27];
        buttons[1] = create(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14), ChatColor.BLACK + " ", new Button.ClickExecutor() {
            @Override
            public void click(Player player) {

            }
        });
        buttons[9] = create(new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14), ChatColor.BLACK + " ", new Button.ClickExecutor() {
            @Override
            public void click(Player player) {

            }
        });
        final EPlayer ePlayer = EPlayer.get(player);
        for(int i = 1; i <= 10;i++){
            final ItemStack item = create(Material.GOLD_INGOT, ChatColor.GOLD + "Upgrade: " + RomanNumeral.get(i).name());
            final int fI = i;
            Button button = create(item, new Button.ClickExecutor() {
                @Override
                public void click(Player player) {
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

    private static ItemStack create(Material mat, String name){
        ItemStack item = new ItemStack(mat);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        item.setItemMeta(meta);
        return item;
    }

    private static Button create(ItemStack item, String name, Button.ClickExecutor clickExecutor){
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        item.setItemMeta(meta);
        return new Button(item, clickExecutor);
    }

    private static Button create(ItemStack item, Button.ClickExecutor ce){
        return new Button(item, ce);
    }

}
