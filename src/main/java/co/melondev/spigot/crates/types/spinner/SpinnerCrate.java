package co.melondev.spigot.crates.types.spinner;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

import co.melondev.spigot.crates.CrateReward;
import co.melondev.spigot.crates.Phase;
import co.melondev.spigot.crates.types.CrateSkeleton;

public class SpinnerCrate extends CrateSkeleton {
	
	private static final ItemStack CRATE_KEY = new ItemStack(Material.IRON_HOE, 1);
	
	public SpinnerCrate() {
		super("Spinner", Material.ENDER_CHEST, Arrays.asList(
				CrateReward.builder()
						.setChance(50)
						.setDisplayItem(new ItemStack(Material.DIAMOND))
						.setName("GODDESS DIAMOND")
						.addCommandReward("eco give %PLAYER% 500")
						.build(),
				
				CrateReward.builder()
						.setChance(70)
						.setDisplayItem(new ItemStack(Material.DIRT))
						.setName("DIRTY STUFF")
						.addCommandReward("eco give %PLAYER% 30")
						.build()
				)
		);
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
		return Arrays.asList(new PhaseCountdown(), new StageOneScrollerPhase(), new StageTwoScrollerPhase());
	}
	
}
