package co.melondev.spigot.crates.crates;

import org.bukkit.entity.Player;

import java.util.ListIterator;
import java.util.UUID;

public final class CrateContext {

	private final Player player;
	private final UUID uniqueId;
	private final Crate crate;
	private final ListIterator<Phase> phases;
	private final CrateOpenTask openTask;
	
	private Phase currentPhase;
	private CrateReward currentReward;

	public CrateContext(Player player, Crate crate) {
		this.player = player;
		this.uniqueId = player.getUniqueId();
		this.crate = crate;
		
		this.phases = crate.getPhases().listIterator();
		this.currentPhase = this.phases.next();
		
		this.openTask = new CrateOpenTask(this);
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
	
}
