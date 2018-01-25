package co.melondev.spigot.crates;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import co.melondev.spigot.crates.types.spinner.SpinnerCrate;

public final class Crates {
	
	private final Map<String, Crate> crates = new HashMap<>();
	private final Map<UUID, CrateContext> openingCrates = new ConcurrentHashMap<>();
	
	public void loadCrates() {
		this.registerCrate(new SpinnerCrate());
	}
	
	private void registerCrate(Crate crate) {
		this.crates.put(crate.getName(), crate);
	}
	
	public Optional<Crate> getCrate(String name) {
		Objects.requireNonNull(name, "name");
		return Optional.of(this.crates.get(name));
	}
	
	public Optional<Crate> getCrate(ItemStack itemKey) {
		return this.crates.values().stream()
				.filter(crate -> crate.isKey(itemKey))
				.findFirst();
	}
	
	public boolean isCrateBlock(Block block) {
		Objects.requireNonNull(block, "block");
		return this.crates.values().stream()
				.anyMatch(crate -> crate.getBlockType() == block.getType());
	}
	
	public Optional<CrateContext> getCrateContext(Player player) {
		Objects.requireNonNull(player, "player");
		return Optional.of(this.openingCrates.get(player.getUniqueId()));
	}
	
	public void setCrateContext(Player player, CrateContext context) {
		this.openingCrates.put(player.getUniqueId(), context);
	}
	
	public void invalidateCrateContext(UUID uniqueId) {
		Objects.requireNonNull(uniqueId, "uniqueId");
		this.openingCrates.remove(uniqueId);
	}
	
}
