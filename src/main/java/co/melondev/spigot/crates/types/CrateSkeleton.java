package co.melondev.spigot.crates.types;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import co.melondev.spigot.crates.CratePlugin;
import co.melondev.spigot.crates.Crate;
import co.melondev.spigot.crates.CrateContext;
import co.melondev.spigot.crates.CrateOpenTask;
import co.melondev.spigot.crates.CrateReward;
import co.melondev.spigot.crates.weight.WeightedTable;
import co.melondev.spigot.crates.weight.WeightedValue;

public abstract class CrateSkeleton implements Crate {
	
	private final String name;
	private final Material blockType;
	private final List<CrateReward> crateRewards = new ArrayList<>();
	private final WeightedTable<CrateReward> crateRewardsTable;
	
	public CrateSkeleton(String name, Material blockType, Collection<CrateReward> crateRewards) {
		this.name = name;
		this.blockType = blockType;
		this.crateRewardsTable = WeightedTable.<CrateReward>builder()
				.addAll(
						crateRewards.stream()
								.peek(this.crateRewards::add)
								.map(reward -> new WeightedValue<>(reward, reward.getChance()))
								.collect(Collectors.toList())
				).build();
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
		return Collections.unmodifiableList(this.crateRewards);
	}
	
	@Override
	public Stream<CrateReward> streamRewards() {
		return this.getRewards().stream();
	}
	
	@Override
	public CrateReward selectRandomReward() {
		return this.crateRewardsTable.nextValue();
	}
	
	@Override
	public void openCrate(Player player) {
		CrateContext crateContext = CrateContext.createContext(player, this);
		
		CratePlugin cratePlugin = CratePlugin.getInstance();
		
		cratePlugin.getCrates().setCrateContext(player, crateContext);
		crateContext.getOpenTask().runTaskTimer(cratePlugin, CrateOpenTask.RUN_TICKS, CrateOpenTask.RUN_TICKS);
	}
	
}
