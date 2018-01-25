package co.melondev.spigot.crates.crates.types.spinner;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

import co.melondev.spigot.crates.crates.Phase;
import co.melondev.spigot.crates.crates.types.CrateSkeleton;

public class SpinnerCrate extends CrateSkeleton {
	
	private static final ItemStack CRATE_KEY = new ItemStack(Material.IRON_HOE, 1);
	
	public SpinnerCrate() {
		super("Spinner", Material.ENDER_CHEST, null);
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
	public List<Phase> getPhases() {
		return Arrays.asList(new PhaseCountdown());
	}
	
}
