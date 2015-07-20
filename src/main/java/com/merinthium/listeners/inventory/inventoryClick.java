package com.merinthium.listeners.inventory;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import com.merinthium.Main;
import com.merinthium.handlers.Kit;
import com.merinthium.listeners.MGListener;

public class inventoryClick extends MGListener 
{

	public inventoryClick(Main pl)
	{
		super(pl);	
	}
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event)
	{
		if(!(event.getWhoClicked() instanceof Player))
			return;
		if(!event.getInventory().getName().equalsIgnoreCase("Kit Selector"))
			return;
		
		event.setCancelled(true);
			Player player = (Player) event.getWhoClicked();
			
			ItemStack i = event.getCurrentItem();
			
			if(i == null)
				return;
			if(i.getType() == null||i.getType() == Material.AIR) 
				return;
			
			Kit kit = Kit.getKit(i.getItemMeta().getDisplayName());
			Kit.setKit(player, kit);
			player.sendMessage(ChatColor.GREEN + "Kit " + kit.getName() + " Selected");
			
	}

}
