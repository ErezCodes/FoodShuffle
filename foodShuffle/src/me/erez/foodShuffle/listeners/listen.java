package me.erez.foodShuffle.listeners;
import me.erez.foodShuffle.Main;
import java.util.ArrayList;
import java.util.Arrays;
import org.bukkit.Bukkit;
//import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Server;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.ComponentBuilder;

public class listen implements Listener{
	private Main plugin;
	
	public listen(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	

	
	@EventHandler()
	public void eventus(PlayerItemConsumeEvent event) {
		
		if(!plugin.game) return;
		
		Player player = event.getPlayer();
		Material eaten = event.getItem().getType();
		
		if(player.equals(plugin.player1)){
			
			if(eaten.equals(plugin.p1current)) {
				plugin.p1success = true;
				plugin.p1list.remove(eaten);
				player.sendMessage(ChatColor.GREEN + "You have completed your task!");
			}
			
			
		}
		
		else if(player.equals(plugin.player2)) {
			
			if(eaten.equals(plugin.p2current)) {
				plugin.p2success = true;
				plugin.p2list.remove(eaten);
				player.sendMessage(ChatColor.GREEN + "You have completed your task!");
			}
			
		}
		
		else return;
		
		if(plugin.p1success && plugin.p2success) {
			plugin.p1success = false;
			plugin.p2success = false;
			plugin.timer = 300;
			return;
		}
		
		
		
		
	}
	
	@EventHandler
	public void interact(PlayerInteractEvent event) {
		if(!plugin.game) return;
		
		Player player = event.getPlayer();
		
		if(player.equals(plugin.player1)) {
			
			if(plugin.p1current.equals(Material.CAKE)) {
				
				if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getClickedBlock().getType().equals(Material.CAKE)) {
					plugin.p1success = true;
					plugin.p1list.remove(Material.CAKE);
					player.sendMessage(ChatColor.GREEN + "You have completed your task!");
				}
				
			}
			
		}
		
		if(player.equals(plugin.player2)) {
			
			if(plugin.p2current.equals(Material.CAKE)) {
				
				if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getClickedBlock().getType().equals(Material.CAKE)) {
					plugin.p2success = true;
					plugin.p2list.remove(Material.CAKE);
					player.sendMessage(ChatColor.GREEN + "You have completed your task!");
				}
				
			}
			
		}
		
		
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