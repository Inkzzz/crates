package co.melondev.spigot.crates.crates;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import co.melondev.spigot.crates.CratePlugin;

public final class CrateOpenTask extends BukkitRunnable {
	
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
	
	private void terminate() {
		CratePlugin.getInstance().getCrates().invalidateCrateContext(this.context.getUniqueId());
		this.cancel();
	}
	
}
