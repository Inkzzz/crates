package co.melondev.spigot.crates.crates.types.spinner;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import co.melondev.spigot.crates.crates.Phase;
import co.melondev.spigot.crates.crates.types.CrateSkeleton;

public class SpinnerCrate extends CrateSkeleton {
	
	private static final ItemStack CRATE_KEY = new ItemStack(Material.IRON_HOE, 1);
	
	private static final Phase COUNTDOWN_PHASE = new Phase(3, 1, Arrays.asList(
			new PhaseExecutionCountdown3(),
			new PhaseExecutionCountdown2(),
			new PhaseExecutionCountdown1()).listIterator()
	);
	private static final List<Phase> PHASES = Arrays.asList(COUNTDOWN_PHASE);
	
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
	
	@Override
	public List<Phase> getPhases() {
		return new ArrayList<>(PHASES);
	}
	
}
