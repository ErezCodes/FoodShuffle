package me.erez.foodShuffle;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.erez.foodShuffle.commands.clearSlots;
import me.erez.foodShuffle.commands.join;
import me.erez.foodShuffle.commands.reroll;
import me.erez.foodShuffle.commands.startGame;
import me.erez.foodShuffle.listeners.listen;

public class Main extends JavaPlugin {
	
	public Player player1;
	public Player player2;
	
	public ArrayList<Material> p1list = new ArrayList<>();
	public ArrayList<Material> p2list = new ArrayList<>();
	
	public boolean p1success;
	public boolean p2success;
	
	public Material p1current;
	public Material p2current;
	
	public int p1rerolls;
	public int p2rerolls;
	
	public int timer = 300;
	public int round;
	public boolean game = false;
	
	@Override
	public void onEnable() {
		fillFoodList(p1list);
		fillFoodList(p2list);
		
		new clearSlots(this);
		new join(this);
		new startGame(this);
		new reroll(this);
		
		new listen(this);
		
	}
	
    public void fillFoodList(ArrayList<Material> foodList) {
    	
    	foodList.add(Material.APPLE);
    	foodList.add(Material.BAKED_POTATO);
    	foodList.add(Material.BEETROOT);
    	foodList.add(Material.BEETROOT_SOUP);
    	foodList.add(Material.BREAD);
    	foodList.add(Material.CAKE);
    	foodList.add(Material.CARROT);
    	foodList.add(Material.COOKED_CHICKEN);
    	foodList.add(Material.COOKED_COD);
    	foodList.add(Material.COOKED_MUTTON);
    	foodList.add(Material.COOKED_PORKCHOP);
    	foodList.add(Material.COOKED_RABBIT);
    	foodList.add(Material.COOKED_SALMON);
    	foodList.add(Material.COOKIE);
    	foodList.add(Material.DRIED_KELP);
    	foodList.add(Material.ENCHANTED_GOLDEN_APPLE); //should I keep it?
    	foodList.add(Material.GOLDEN_APPLE);
    	foodList.add(Material.GOLDEN_CARROT);
    	foodList.add(Material.HONEY_BOTTLE);
    	foodList.add(Material.MELON_SLICE);
    	foodList.add(Material.MUSHROOM_STEW);
    	foodList.add(Material.POISONOUS_POTATO);
    	foodList.add(Material.POTATO);
    	foodList.add(Material.PUFFERFISH);
    	foodList.add(Material.PUMPKIN_PIE);
    	foodList.add(Material.RABBIT_STEW);
    	foodList.add(Material.BEEF);
    	foodList.add(Material.CHICKEN);
    	foodList.add(Material.COD);
    	foodList.add(Material.MUTTON);
    	foodList.add(Material.PORKCHOP);
    	foodList.add(Material.RABBIT);
    	foodList.add(Material.SALMON);
    	foodList.add(Material.ROTTEN_FLESH);
    	foodList.add(Material.SPIDER_EYE);
    	foodList.add(Material.COOKED_BEEF);
    	foodList.add(Material.SUSPICIOUS_STEW);
    	foodList.add(Material.SWEET_BERRIES);
    	foodList.add(Material.TROPICAL_FISH);
    	foodList.add(Material.GLOW_BERRIES);
    	
    	
    	
    }
	
}
