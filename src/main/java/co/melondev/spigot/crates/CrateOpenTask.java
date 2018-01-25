package co.melondev.spigot.crates;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;

public final class CrateOpenTask extends BukkitRunnable {
	
	public static final long RUN_TICKS = 5;
	
	private final CrateContext context;
	
	public CrateOpenTask(CrateContext context) {
		this.context = context;
	}
	
	@Override
	public void run() {
		Player player = this.context.getPlayer();
		
		if (!player.isOnline()) {
			this.terminate();
			return;
		}
		
		this.openInventoryIfClosed(player);
		
		Phase phase = this.context.currentPhase();
		
		if (phase == null) {
			CrateReward reward = this.context.getCurrentReward();
			
			if (reward == null) {
				throw new IllegalStateException("The current reward is invalid");
			}
			
			reward.getCommandRewards(player)
					.forEach(command -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command));
			
			player.closeInventory();
			
			this.terminate();
			return;
		}
		
		if (phase.isPhaseOver()) {
			this.context.nextPhase();
			return;
		}
		
		if (phase.shouldCallNextPhaseExecution()) {
			PhaseExecution execution = phase.getNextPhaseExecution();
			
			if (execution != null) {
				execution.execute(this.context);
			}
		}
		
		phase.incrementExecutionTime();
	}
	
	private void openInventoryIfClosed(Player player) {
		Inventory inventory = player.getOpenInventory().getTopInventory();
		
		if (inventory == null || !inventory.getTitle().equals(this.context.getInventory().getTitle())) {
			player.openInventory(this.context.getInventory());
		}
	}
	
	private void terminate() {
		CratePlugin.getInstance().getCrates().invalidateCrateContext(this.context.getUniqueId());
		this.cancel();
	}
	
}
