package co.melondev.spigot.crates.crates;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ListIterator;
import java.util.Objects;
import java.util.UUID;

public final class CrateContext {

	private final Player player;
	private final UUID uniqueId;
	private final Crate crate;
	private final ListIterator<Phase> phases;
	private final CrateOpenTask openTask;
	
	private Inventory inventory;
	private Phase currentPhase;
	private CrateReward currentReward;

	private CrateContext(Player player, Crate crate) {
		this.player = player;
		this.uniqueId = player.getUniqueId();
		this.crate = crate;
		
		this.phases = crate.getPhases().listIterator();
		this.currentPhase = this.phases.next();
		
		this.inventory = Bukkit.createInventory(null, 54, "Crate: " + crate.getName());
		
		this.openTask = new CrateOpenTask(this);
	}
	
	public static CrateContext createContext(Player player, Crate crate) {
		Objects.requireNonNull(player, "player");
		Objects.requireNonNull(crate, "crate");
		
		return new CrateContext(player, crate);
	}
	
	public UUID getUniqueId() {
		return this.uniqueId;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public Crate getCrate() {
		return this.crate;
	}
	
	public CrateOpenTask getOpenTask() {
		return this.openTask;
	}
	
	public Phase nextPhase() {
		if (!this.phases.hasNext()) {
			return null;
		}
		
		this.currentPhase = this.phases.next();
		return this.currentPhase;
	}
	
	public Phase currentPhase() {
		return this.currentPhase;
	}
	
	public CrateReward getCurrentReward() {
		return this.currentReward;
	}
	
	public void setCurrentReward(CrateReward currentReward) {
		this.currentReward = currentReward;
	}
	
	public Inventory getInventory() {
		return this.inventory;
	}
	
}
