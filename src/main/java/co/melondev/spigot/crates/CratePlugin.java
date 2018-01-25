package co.melondev.spigot.crates;

import org.bukkit.plugin.java.JavaPlugin;

import co.melondev.spigot.crates.listener.CrateListener;

public class CratePlugin extends JavaPlugin {
	
	private static CratePlugin instance;
	
	public static CratePlugin getInstance() {
		return instance;
	}
	
	private Crates crates;
	
	@Override
	public void onEnable() {
		instance = this;
		
		this.crates = new Crates();
		this.crates.loadCrates();
		
		getServer().getPluginManager().registerEvents(new CrateListener(), this);
	}
	
	@Override
	public void onDisable() {
		instance = null;
	}
	
	public Crates getCrates() {
		return this.crates;
	}
	
}
