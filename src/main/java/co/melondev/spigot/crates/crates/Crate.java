package co.melondev.spigot.crates.crates;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface Crate {
	
	String getName();
	
	Material getBlockType();
	
	ItemStack getKey();
	
	boolean isKey(ItemStack itemKey);
	
	void openCrate(Player player);
	
}
