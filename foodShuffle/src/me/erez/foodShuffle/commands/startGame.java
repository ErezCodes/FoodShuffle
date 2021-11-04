package me.erez.foodShuffle.commands;
import me.erez.foodShuffle.Main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;

public class startGame implements CommandExecutor {
    private Main plugin;
    
    public startGame(Main plugin) {
        this.plugin = plugin;
        plugin.getCommand("startGame").setExecutor(this);
    }
    
    public int gameTask;
    
    
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	
    	if(plugin.player1 == null  || plugin.player2 == null) {
    		Player player = (Player) sender;
    		player.sendMessage(ChatColor.RED + "The are not enough players to start the food shuffle games");
    		return false;
    	}
    	
    	plugin.player1.sendMessage(ChatColor.AQUA + "The shuffle food games have started!");
    	plugin.player2.sendMessage(ChatColor.AQUA + "The shuffle food games have started!");
    	Bukkit.broadcastMessage("");
    	
    	plugin.game = true;
    	plugin.round = 0;
    	
    	plugin.p1rerolls = 3;
    	plugin.p2rerolls = 3;
    	
    	Random random = new Random();
    	
    	BukkitScheduler sched = plugin.player1.getServer().getScheduler();
    		       
    	gameTask = sched.scheduleSyncRepeatingTask(plugin, new Runnable() {
    		
    		
    		@Override
    		public void run() {
    			
    			if(plugin.timer == 300) {
	    			plugin.p1current = plugin.p1list.get(random.nextInt(plugin.p1list.size()));
	    			plugin.p2current = plugin.p2list.get(random.nextInt(plugin.p2list.size()));
	    			
	    			plugin.round++;
	    			
	    			plugin.player1.sendMessage(ChatColor.YELLOW + "You need to eat a " + plugin.p1current.name().toString());
	    			plugin.player2.sendMessage(ChatColor.YELLOW + "You need to eat a " + plugin.p2current.name().toString());
    			}
    			
    			else if(plugin.timer == 60) {
    				if(!plugin.p1success)
    					plugin.player1.sendMessage(ChatColor.RED + "You have 1 minute left to complete your task");
    				if(!plugin.p2success)
    					plugin.player2.sendMessage(ChatColor.RED + "You have 1 minute left to complete your task");
    			}
    			
    			else if(plugin.timer == 30) {
    				if(!plugin.p1success)
    					plugin.player1.sendMessage(ChatColor.RED + "You have 30 seconds left to complete your task");
    				if(!plugin.p2success)
    					plugin.player2.sendMessage(ChatColor.RED + "You have 30 seconds left to complete your task");
    			}
    			
    			else if(plugin.timer == 10 || plugin.timer == 9 || plugin.timer == 8
    					|| plugin.timer == 7 || plugin.timer == 6 || plugin.timer == 6
    					|| plugin.timer == 5 || plugin.timer == 4 || plugin.timer == 3
    					|| plugin.timer == 2 || plugin.timer == 1) 	{
    				
    				if(!plugin.p1success)
    					plugin.player1.sendMessage(ChatColor.RED + "" + plugin.timer);
    				if(!plugin.p2success)
    					plugin.player2.sendMessage(ChatColor.RED + "" + plugin.timer);
    			}
    			
    			else if(plugin.timer == 0) {
    				
    				if(!plugin.p1success) {
    					plugin.game = false;
    					if(!plugin.p2success) 
    						Bukkit.broadcastMessage(ChatColor.YELLOW + "Both " 
	    					+ plugin.player1.getName().toString() + " and " + plugin.player2.getName().toString() 
	    					+ " have failed their tasks.");
    					else Bukkit.broadcastMessage(ChatColor.RED + plugin.player1.getName().toString() + " has failed his task.");
    					sched.cancelTask(gameTask);
    					return;
    					
    				}
    				
    				if(!plugin.p2success) {
    					plugin.game = false;
    					if(!plugin.p1success) 
    						Bukkit.broadcastMessage(ChatColor.YELLOW + "Both " 
	    					+ plugin.player1.getName().toString() + " and " + plugin.player2.getName().toString() 
	    					+ " have failed their tasks.");
    					else Bukkit.broadcastMessage(ChatColor.RED + plugin.player2.getName().toString() + " has failed his task.");
    					sched.cancelTask(gameTask);
    					return;
    					
    				}
    				
    				plugin.timer = 300;
    				
    			}
    		

    			
    			plugin.timer--;
    			
    		}
    		
    	}, 20L, 20L);
    	
    	
        
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

