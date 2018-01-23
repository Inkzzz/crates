package co.melondev.spigot.crates.crates;

import org.bukkit.Material;

abstract class CrateSkeleton implements Crate {
	
	private final String name;
	private final Material blockType;
	
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
	
}
