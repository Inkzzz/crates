package co.melondev.spigot.crates.types.spinner;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import co.melondev.spigot.crates.CrateContext;
import co.melondev.spigot.crates.CrateReward;
import co.melondev.spigot.crates.PhaseExecution;

public class ScrollPhaseExecution implements PhaseExecution {
	
	private static final ItemStack GRAY_GLASS = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
	
	@Override
	public void execute(CrateContext context) {
		Inventory inventory = context.getInventory();
		
		if (!context.isReward(0)) {
			// no rewards, fill up
			for (int i = 0; i <= 8; i++) {
				context.setReward(i, this.selectReward(context));
			}
			
			inventory.clear();
			for (int i = 0; i < inventory.getSize(); i++) {
				inventory.setItem(i, GRAY_GLASS);
			}
			
		} else {
			for (int i = 0; i <= 8; i++) {
				if (i == 0) {
					continue;
				}
				
				if (i == 8) {
					context.setReward(i, this.selectReward(context));
				} else {
					context.setReward(i, context.getReward(i + 1).orElseGet(() -> this.selectReward(context)));
				}
			}
		}
		
		context.setCurrentReward(context.getReward(4).orElseThrow(IllegalStateException::new));
		
		context.getCrateRewardSlots().forEach((slot, reward) -> inventory.setItem(slot, reward.getDisplayItem()));
	}
	
	private CrateReward selectReward(CrateContext context) {
		return context.getCrate().selectRandomReward();
	}
	
}
