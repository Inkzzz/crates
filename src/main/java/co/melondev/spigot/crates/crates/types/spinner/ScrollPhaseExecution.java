package co.melondev.spigot.crates.crates.types.spinner;

import org.bukkit.inventory.Inventory;

import co.melondev.spigot.crates.crates.CrateContext;
import co.melondev.spigot.crates.crates.CrateReward;
import co.melondev.spigot.crates.crates.PhaseExecution;

public class ScrollPhaseExecution implements PhaseExecution {
	
	@Override
	public void execute(CrateContext context) {
		if (!context.isReward(0)) {
			// no rewards, fill up
			for (int i = 0; i <= 8; i++) {
				context.setReward(i, this.selectReward(context));
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
		
		Inventory inventory = context.getInventory();
		
		context.getCrateRewardSlots().forEach((slot, reward) -> inventory.setItem(slot, reward.getDisplayItem()));
	}
	
	private CrateReward selectReward(CrateContext context) {
		return context.getCrate().selectRandomReward();
	}
	
}
