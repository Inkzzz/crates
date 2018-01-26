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
						.build(),
				
				CrateReward.builder()
						.setChance(20)
						.setDisplayItem(new ItemStack(Material.REDSTONE))
						.setName("REDDIT")
						.addCommandReward("eco give %PLAYER% 5200")
						.build(),
				
				CrateReward.builder()
						.setChance(5)
						.setDisplayItem(new ItemStack(Material.EMERALD_BLOCK))
						.setName("EMMY")
						.addCommandReward("eco give %PLAYER% 50000")
						.build(),
				
				CrateReward.builder()
						.setChance(76)
						.setDisplayItem(new ItemStack(Material.WOOD))
						.setName("AVERAGE")
						.addCommandReward("eco give %PLAYER% 10")
						.build(),
				
				CrateReward.builder()
						.setChance(50)
						.setDisplayItem(new ItemStack(Material.COAL_BLOCK))
						.setName("MINER")
						.addCommandReward("eco give %PLAYER% 345")
						.addCommandReward("fly %PLAYER%")
						.build(),
				
				CrateReward.builder()
						.setChance(25)
						.setDisplayItem(new ItemStack(Material.BLAZE_ROD))
						.setName("Blazing")
						.addCommandReward("eco give %PLAYER% 420")
						.build(),
				
				CrateReward.builder()
						.setChance(55)
						.setDisplayItem(new ItemStack(Material.DARK_OAK_STAIRS))
						.setName("Elevate")
						.addCommandReward("eco give %PLAYER% 65")
						.build(),
				
				CrateReward.builder()
						.setChance(75)
						.setDisplayItem(new ItemStack(Material.COBBLESTONE))
						.setName("Stoner")
						.addCommandReward("eco give %PLAYER% 50")
						.build(),
				
				CrateReward.builder()
						.setChance(45)
						.setDisplayItem(new ItemStack(Material.GOLD_BLOCK))
						.setName("GOLDY LOCKS")
						.addCommandReward("eco give %PLAYER% 1750")
						.build(),
				
				CrateReward.builder()
						.setChance(60)
						.setDisplayItem(new ItemStack(Material.WATCH))
						.setName("Watch it")
						.addCommandReward("eco give %PLAYER% 560")
						.build(),
				
				CrateReward.builder()
						.setChance(62)
						.setDisplayItem(new ItemStack(Material.IRON_ORE))
						.setName("IRON IT")
						.addCommandReward("eco give %PLAYER% 345")
						.build(),
				
				CrateReward.builder()
						.setChance(72)
						.setDisplayItem(new ItemStack(Material.BAKED_POTATO))
						.setName("Baked")
						.addCommandReward("eco give %PLAYER% 410")
						.build(),
				
				CrateReward.builder()
						.setChance(81)
						.setDisplayItem(new ItemStack(Material.NETHER_BRICK))
						.setName("Bricked")
						.addCommandReward("eco give %PLAYER% 23")
						.build(),
				
				CrateReward.builder()
						.setChance(47)
						.setDisplayItem(new ItemStack(Material.STONE_AXE))
						.setName("Axed")
						.addCommandReward("eco give %PLAYER% 540")
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
