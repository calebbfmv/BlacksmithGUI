package me.calebbfmv.blacksmithgui.gui.menus;

import me.calebbfmv.blacksmithgui.BlacksmithGUI;
import me.calebbfmv.blacksmithgui.enchant.CustomEnchant;
import me.calebbfmv.blacksmithgui.enchant.EnchantmentType;
import me.calebbfmv.blacksmithgui.gui.Button;
import me.calebbfmv.blacksmithgui.gui.GUI;
import me.calebbfmv.blacksmithgui.utils.EPlayer;
import me.calebbfmv.blacksmithgui.utils.EnchantedItem;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tim [calebbfmv] on 10/6/2014.
 */
public class EnchantsGUI extends GUI {

    public EnchantsGUI(String name, Button[] buttons) {
        super(name, buttons);
    }

    public static EnchantsGUI create(){
        final Button[] buttons = new Button[27];
        for(int i = 0; i < 27; i+= 9){
            buttons[i] = create(new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData()), " ");
            if(i != 0){
                buttons[i - 1] = buttons[i];
            }
        }
        for(int i = 0; i < 9; i++){
            buttons[i] = create(new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData()), " ");
        }
        for(int i = 16; i < 27; i++){
            buttons[i] = create(new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData()), " ");
        }
        EnchantmentType[] enchants = EnchantmentType.values();
        for(int i = 0; i < enchants.length; i++){
            final EnchantmentType type = enchants[i];
            final ItemStack item = create(Material.ENCHANTED_BOOK, ChatColor.YELLOW + ChatColor.BOLD.toString() + StringUtils.capitalize(type.name()));
            ItemMeta meta = item.getItemMeta();
            switch(type){
                case ARROW:
                    meta.setLore(getArrowLore());
                    break;
                case BLINDNESS:
                    meta.setLore(getBlindnessLore());
                    break;
                case CRITICAL:
                    meta.setLore(getCriticalLore());
                    break;
                case DAMAGE:
                    meta.setLore(getDamageLore());
                    break;
                case FIREBAL:
                    List<String> fLore = getFireballLore();
                    fLore.add(ChatColor.RED + ChatColor.BOLD.toString() + "DONOR ONLY.");
                    meta.setLore(fLore);
                    break;
                case SLOWNESS:
                    List<String> sLore = getSlownessLore();
                    sLore.add(ChatColor.RED + ChatColor.BOLD.toString() + "DONOR ONLY.");
                    meta.setLore(sLore);
                    break;
                default:
                    break;
            }
            item.setItemMeta(meta);
            buttons[i + 9] = create(item, new Button.ClickExecutor() {
                @Override
                public void click(Player player) {
                    int cost = BlacksmithGUI.getInstance().getManager().getCost(1);
                    double balance = BlacksmithGUI.getInstance().getEcon().getBalance(player);
                    if(cost > balance){
                        player.sendMessage(ChatColor.RED + "You cannot purchase this!");
                        return;
                    }
                    ItemStack item = EPlayer.get(player).getChosenItem();
                    if(item == null){
                        player.getOpenInventory().setItem(16, create(Material.REDSTONE_BLOCK, ChatColor.DARK_RED + "Please select an item!"));
                        player.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "Please select an item! (1)");
                        return;
                    }
                    if(EPlayer.get(player).isPromptedForItem()){
                        player.getOpenInventory().setItem(16, create(Material.REDSTONE_BLOCK, ChatColor.DARK_RED + "Please select an item!"));
                        player.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "Please select an item! (2)");
                        return;
                    }
                    if(type.isDonor() && !player.hasPermission("bs.donor")){
                        player.sendMessage(ChatColor.RED + ChatColor.BOLD.toString() + "You cannot purchase this item! DONORS ONLY");
                        return;
                    }
                    BlacksmithGUI.getInstance().getEcon().withdrawPlayer(player, cost);
                    EnchantedItem enchantedItem = new EnchantedItem(item);
                    enchantedItem.withEnchant(CustomEnchant.get(type), 1);
                    if(CustomEnchant.get(type).getLevel(player) <= 0){//player died
                        CustomEnchant.get(type).setLevel(player, 1);
                    }
                    enchantedItem.give(player);
                    player.closeInventory();
                    player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + StringUtils.capitalize(type.name()) + ChatColor.GRAY + "] " + ChatColor.RED + "is now equiped!");
                    EPlayer.get(player).setChosenItem(null);
                    EPlayer.get(player).setPromptedForItem(true);
                }
            });
        }
        return new EnchantsGUI("Weapon Enchantments", buttons);
    }

    private static List<String> getArrowLore(){
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "-----------------------------------");
        lore.add(ChatColor.GREEN.toString() + ChatColor.BOLD + "Description: ");
        lore.add(ChatColor.DARK_AQUA + "- Shoots an arrow!");
        lore.add(ChatColor.DARK_AQUA + "- Has a 55s cooldown for level 1.");
        lore.add(ChatColor.DARK_AQUA + "  -5s for each level");
        lore.add(ChatColor.GRAY + "-----------------------------------");
        return lore;
    }

    private static List<String> getBlindnessLore(){
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "-----------------------------------");
        lore.add(ChatColor.GREEN.toString() + ChatColor.BOLD + "Description: ");
        lore.add(ChatColor.DARK_AQUA + "- Shoots a snowball!");
        lore.add(ChatColor.DARK_AQUA + "  - Upon being hit, the player will receive blindness");
        lore.add(ChatColor.DARK_AQUA + "  - Every level increases the duration of the effect");
        lore.add(ChatColor.DARK_AQUA + "- Has a 10s cooldown for every level");
        lore.add(ChatColor.GRAY + "-----------------------------------");
        return lore;
    }

    private static List<String> getCriticalLore(){
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "-----------------------------------");
        lore.add(ChatColor.GREEN.toString() + ChatColor.BOLD + "Description: ");
        lore.add(ChatColor.DARK_AQUA + "- Increases your chance to critical on a hit by 10%");
        lore.add(ChatColor.DARK_AQUA + "  - Every level increases the chance by 10%.");
        lore.add(ChatColor.GRAY + "-----------------------------------");
        return lore;
    }

    private static List<String> getDamageLore(){
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "-----------------------------------");
        lore.add(ChatColor.GREEN.toString() + ChatColor.BOLD + "Description: ");
        lore.add(ChatColor.DARK_AQUA + "- Increases your damage done by 5%");
        lore.add(ChatColor.DARK_AQUA + "  +5% every level");
        lore.add(ChatColor.GRAY + "-----------------------------------");
        return lore;
    }

    private static List<String> getFireballLore(){
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "-----------------------------------");
        lore.add(ChatColor.GREEN.toString() + ChatColor.BOLD + "Description: ");
        lore.add(ChatColor.DARK_AQUA + "- Shoots a Fireball!");
        lore.add(ChatColor.DARK_AQUA + "- 55s cooldown");
        lore.add(ChatColor.DARK_AQUA + "  -5s for each level");
        lore.add(ChatColor.GRAY + "-----------------------------------");
        return lore;
    }

    private static List<String> getSlownessLore(){
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "-----------------------------------");
        lore.add(ChatColor.GREEN.toString() + ChatColor.BOLD + "Description: ");
        lore.add(ChatColor.DARK_AQUA + "- Shoots a snowball!");
        lore.add(ChatColor.DARK_AQUA + "  - Adds slowness to");
        lore.add(ChatColor.DARK_AQUA + "    the player hit");
        lore.add(ChatColor.DARK_AQUA + "  +5 duration of the effect");
        lore.add(ChatColor.DARK_AQUA + "   for each level");
        lore.add(ChatColor.DARK_AQUA + "- 10s cooldown");
        lore.add(ChatColor.GRAY + "-----------------------------------");
        return lore;
    }
}
