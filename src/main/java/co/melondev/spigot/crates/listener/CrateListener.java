package co.melondev.spigot.crates.listener;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import co.melondev.spigot.crates.CratePlugin;
import co.melondev.spigot.crates.crates.Crates;

public class CrateListener implements Listener {
	
	@EventHandler
	private void on(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		
		if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
			return;
		}
		
		Crates crates = CratePlugin.getInstance().getCrates();
		
		Block blockClicked = event.getClickedBlock();
		
		if (!crates.isCrateBlock(blockClicked)) {
			return;
		}
		
		event.setCancelled(true);
		event.setUseInteractedBlock(Event.Result.DENY);
		event.setUseItemInHand(Event.Result.DENY);
		
		ItemStack itemKey = player.getItemInHand();
		
		crates.getCrate(itemKey).ifPresent(crate -> {
			player.getInventory().removeItem(crate.getKey());
			crate.openCrate(player);
		});
	}
	
}
