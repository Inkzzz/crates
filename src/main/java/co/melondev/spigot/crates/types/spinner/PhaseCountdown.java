package co.melondev.spigot.crates.types.spinner;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.stream.IntStream;

import co.melondev.spigot.crates.CrateContext;
import co.melondev.spigot.crates.Phase;
import co.melondev.spigot.crates.PhaseExecution;

class PhaseCountdown extends Phase {
	
	private static final ItemStack GRAY_GLASS = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 7);
	private static final ItemStack GREEN_GLASS = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 5);
	private static final ItemStack YELLOW_GLASS = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 4);
	private static final ItemStack RED_GLASS = new ItemStack(Material.STAINED_GLASS_PANE, 1, (byte) 14);
	
	public PhaseCountdown() {
		super(12, 4, Arrays.asList(new PhaseExecutionCountdown3(),
				new PhaseExecutionCountdown2(), new PhaseExecutionCountdown1())
		);
	}
	
	private static final class PhaseExecutionCountdown3 implements PhaseExecution {
		
		@Override
		public void execute(CrateContext context) {
			Inventory inventory = context.getInventory();
			
			inventory.clear();
			
			IntStream.of(3, 4, 5, 14, 21, 22, 23, 32, 39, 40, 41).forEach(slot -> inventory.setItem(slot, RED_GLASS));
			
			for (int i = 0; i < inventory.getSize(); i++) {
				if (inventory.getItem(i) == null) {
					inventory.setItem(i, GRAY_GLASS);
				}
			}
		}
		
	}
	
	private static final class PhaseExecutionCountdown2 implements PhaseExecution {
		
		@Override
		public void execute(CrateContext context) {
			Inventory inventory = context.getInventory();
			
			inventory.clear();
			
			IntStream.of(2, 3, 4, 5, 6, 15, 20, 21, 22, 23, 24, 29, 38, 39, 40, 41, 42)
					.forEach(slot -> inventory.setItem(slot, YELLOW_GLASS));
			
			for (int i = 0; i < inventory.getSize(); i++) {
				if (inventory.getItem(i) == null) {
					inventory.setItem(i, GRAY_GLASS);
				}
			}
		}
		
	}
	
	private static final class PhaseExecutionCountdown1 implements PhaseExecution {
		
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
	
}
