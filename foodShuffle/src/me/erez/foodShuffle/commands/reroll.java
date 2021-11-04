package me.erez.foodShuffle.commands;
import me.erez.foodShuffle.Main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class reroll implements CommandExecutor {
    private Main plugin;
    
    public reroll(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("reroll").setExecutor(this);
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	
    	Player player = (Player) sender;
    	
    	if(!plugin.game) {
    		player.sendMessage(ChatColor.RED + "There's no game running right now");
    		return true;
    	}
    	
    	if(player.equals(plugin.player1)) {
    		
    		if(plugin.p1rerolls == 0) {
    			player.sendMessage(ChatColor.RED + "You do not have any rerolls left");
    			return false;
    		}
    		
    		Random random = new Random();
    		
    		plugin.p1rerolls--;
    		plugin.timer += 30;
    		Material pastMaterial = plugin.p1current;
    		boolean rerolled = false;
    		while (!rerolled) {
    			plugin.p1current = plugin.p1list.get(random.nextInt(plugin.p1list.size()));
    			if(!pastMaterial.equals(plugin.p1current))
    				rerolled = true;
    			if(plugin.p1list.size() == 1) {
    				player.sendMessage(ChatColor.RED + "You cannot use reroll");
    				plugin.p1rerolls++;
    				rerolled = true;
    				return true;
    			}
    		}
    		
    		player.sendMessage(ChatColor.YELLOW + "Your new item is " + plugin.p1current.name().toString());
    		player.sendMessage(ChatColor.GOLD + "You have " + plugin.p1rerolls + " rerolls left");
    		return true;
    	
    		
    		
    	}
    	
    	if(player.equals(plugin.player2)) {
    		
    		if(plugin.p2rerolls == 0) {
    			player.sendMessage(ChatColor.RED + "You do not have any rerolls left");
    			return false;
    		}
    		
    		Random random = new Random();
    		
    		plugin.p2rerolls--;
    		plugin.timer += 30;
    		Material pastMaterial = plugin.p2current;
    		boolean rerolled = false;
    		while (!rerolled) {
    			plugin.p2current = plugin.p2list.get(random.nextInt(plugin.p2list.size()));
    			if(!pastMaterial.equals(plugin.p2current))
    				rerolled = true;
    			if(plugin.p2list.size() == 1) {
    				player.sendMessage(ChatColor.RED + "You cannot use reroll");
    				plugin.p2rerolls++;
    				rerolled = true;
    				return true;
    			}
    		}
    		
    		player.sendMessage(ChatColor.YELLOW + "Your new item is " + plugin.p2current.name().toString());
    		player.sendMessage(ChatColor.GOLD + "You have " + plugin.p2rerolls + " rerolls left");
    		return true;
    	
    		
    		
    	}
    	
    	player.sendMessage("The player slots are full, in order to clear them, type /clearSlots");
    	
        
        return true;
    }
    
    protected ItemStack createGuiItem(final Material material, int amount, final String name, final String... lore) {
        final ItemStack item = new ItemStack(material, amount);
        final ItemMeta meta = item.getItemMeta();

        // Set the name of the item
        meta.setDisplayName(name);

        // Set the lore of the item
        meta.setLore(Arrays.asList(lore));

        item.setItemMeta(meta);

        return item;
    }
    
    
}

