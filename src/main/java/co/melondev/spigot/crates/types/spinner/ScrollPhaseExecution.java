package co.melondev.spigot.crates.types.spinner;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

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
			
			inventory = Bukkit.createInventory(null, 27, "Crate: " + context.getCrate().getName());
			context.setInventory(inventory);
			
			for (int i = 0; i < inventory.getSize(); i++) {
				if (i == 4 || i == 22) {
					inventory.setItem(i, new ItemStack(Material.REDSTONE_TORCH_ON));
				} else {
					inventory.setItem(i, GRAY_GLASS);
				}
			}
			
			context.getPlayer().openInventory(inventory);
		} else {
			// scroll rewards
			for (int i = 0; i <= 8; i++) {
				if (i == 0) {
					// first reward, ignore as its the one to remove!
					continue;
				}
				
				if (i == 8) {
					// new reward
					context.setReward(i, this.selectReward(context));
				} else {
					// shift rewards
					context.setReward(i, context.getReward(i + 1).orElseGet(() -> this.selectReward(context)));
				}
			}
		}
		
		// update final reward item
		context.setCurrentReward(context.getReward(4).orElseThrow(IllegalStateException::new));
		
		// update display items in gui
		for (Map.Entry<Integer, CrateReward> entry : context.getCrateRewardSlots().entrySet()) {
			inventory.setItem(entry.getKey() + 9, entry.getValue().getDisplayItem());
		}
	}
	
	private CrateReward selectReward(CrateContext context) {
		return context.getCrate().selectRandomReward();
	}
	
}
