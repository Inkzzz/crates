package co.melondev.spigot.crates.crates;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.stream.Stream;

public interface Crate {
	
	String getName();
	
	Material getBlockType();
	
	ItemStack getKey();
	
	boolean isKey(ItemStack itemKey);
	
	List<CrateReward> getRewards();
	
	Stream<CrateReward> streamRewards();
	
	CrateReward selectRandomReward();
	
	void openCrate(Player player);
	
	List<Phase> getPhases();
	
}
