package co.melondev.spigot.crates.crates.types;

import org.bukkit.Material;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import co.melondev.spigot.crates.crates.Crate;
import co.melondev.spigot.crates.crates.CrateReward;

public abstract class CrateSkeleton implements Crate {
	
	private final String name;
	private final Material blockType;
	
	protected final List<CrateReward> rewards = new ArrayList<>();
	
	public CrateSkeleton(String name, Material blockType) {
		this.name = name;
		this.blockType = blockType;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public Material getBlockType() {
		return this.blockType;
	}
	
	@Override
	public List<CrateReward> getRewards() {
		return Collections.unmodifiableList(this.rewards);
	}
	
	@Override
	public Stream<CrateReward> streamRewards() {
		return this.rewards.stream();
	}
	
	@Override
	public CrateReward selectRandomReward() {
		return null;
	}
	
}
