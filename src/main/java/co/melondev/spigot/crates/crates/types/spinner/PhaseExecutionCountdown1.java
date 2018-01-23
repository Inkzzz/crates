package co.melondev.spigot.crates.crates.types.spinner;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.stream.IntStream;

import co.melondev.spigot.crates.crates.CrateContext;
import co.melondev.spigot.crates.crates.PhaseExecution;

public class PhaseExecutionCountdown1 implements PhaseExecution {
	
	private static final ItemStack GRAY_GLASS = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
	private static final ItemStack GREEN_GLASS = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5);
	
	@Override
	public void execute(CrateContext context) {
		Inventory inventory = context.getInventory();
		
		inventory.clear();
		
		IntStream.of(4, 12, 13, 22, 31, 39, 40, 41).forEach(slot -> inventory.setItem(slot, GREEN_GLASS));
		
		for (int i = 0; i < inventory.getSize(); i++) {
			if (inventory.getItem(i) == null) {
				inventory.setItem(i, GRAY_GLASS);
			}
		}
	}
	
}
