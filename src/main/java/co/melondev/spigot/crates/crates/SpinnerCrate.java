package co.melondev.spigot.crates.crates;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SpinnerCrate extends CrateSkeleton {
	
	private static final ItemStack CRATE_KEY = new ItemStack(Material.IRON_HOE, 1);
	
	public SpinnerCrate() {
		super("Spinner", Material.ENDER_CHEST);
	}
	
	@Override
	public ItemStack getKey() {
		return CRATE_KEY;
	}
	
	@Override
	public boolean isKey(ItemStack itemKey) {
		return itemKey != null && itemKey.getType() == CRATE_KEY.getType();
	}
	
	@Override
	public void openCrate(Player player) {
	
	}
	
}
