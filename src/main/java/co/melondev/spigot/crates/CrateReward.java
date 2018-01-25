package co.melondev.spigot.crates;

import com.google.common.collect.ImmutableList;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class CrateReward {
	
	private static final String NAME_PLACEHOLDER = "%PLAYER%";
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static final class Builder {
		
		private String name;
		private int chance;
		private ItemStack displayItem;
		private final ImmutableList.Builder<String> commandRewards = ImmutableList.builder();
		
		private Builder() {
		
		}
		
		public CrateReward build() {
			this.validate();
			return new CrateReward(this.name, this.chance, this.displayItem, this.commandRewards.build());
		}
		
		private void validate() {
			Objects.requireNonNull(this.name, "name");
			Objects.requireNonNull(this.displayItem, "displayItem");
			
			if (this.chance <= 0) {
				throw new IllegalArgumentException("Chance has not been set");
			}
		}
		
		public Builder setName(String name) {
			this.name = name;
			return this;
		}
		
		public Builder setChance(int chance) {
			this.chance = chance;
			return this;
		}
		
		public Builder setDisplayItem(ItemStack displayItem) {
			this.displayItem = displayItem;
			return this;
		}
		
		public Builder addCommandReward(String commandReward) {
			Objects.requireNonNull(commandReward, "commandReward");
			this.commandRewards.add(commandReward);
			return this;
		}
		
		public Builder addCommandRewards(String... commandRewards) {
			for (String commandReward : commandRewards) {
				this.addCommandReward(commandReward);
			}
			return this;
		}
		
	}
	
	private final String name;
	private final int chance;
	private final ItemStack displayItem;
	private final ImmutableList<String> commandRewards;
	
	private CrateReward(String name, int chance, ItemStack displayItem, ImmutableList<String> commandRewards) {
		this.name = name;
		this.chance = chance;
		this.displayItem = displayItem;
		this.commandRewards = commandRewards;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getChance() {
		return this.chance;
	}
	
	public ItemStack getDisplayItem() {
		return this.displayItem;
	}
	
	public ImmutableList<String> getCommandRewards() {
		return this.commandRewards;
	}
	
	public List<String> getCommandRewards(Player player) {
		return this.commandRewards.stream()
				.map(command -> command.replaceAll(NAME_PLACEHOLDER, player.getName()))
				.collect(Collectors.toList());
	}
	
}
