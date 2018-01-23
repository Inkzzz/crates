package co.melondev.spigot.crates.crates;

import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public final class Crates {
	
	private final Map<String, Crate> crates = new HashMap<>();
	
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
	
}
