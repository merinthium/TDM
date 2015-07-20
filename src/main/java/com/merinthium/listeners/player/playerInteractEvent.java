package com.merinthium.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import com.merinthium.Main;
import com.merinthium.handlers.Game;
import com.merinthium.handlers.Kit;
import com.merinthium.listeners.MGListener;

public class playerInteractEvent extends MGListener 
{

	public playerInteractEvent(Main pl) 
	{
		super(pl);
	}

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event)
	{
		if(event.getItem() == null)
			return;
		if(event.getItem().getType() == Material.AIR||event.getItem() == null)
		return;
		
		if(event.getItem().getType() == Material.WOOL && !Game.hasStarted())
		{
			Inventory inv = Bukkit.createInventory(null,27, "Kit Selector");
			for(Kit k : Kit.getAllKits())
				inv.addItem(k.getDisplayItem());
			event.getPlayer().openInventory(inv);
		}
	}

}
