package com.merinthium.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;



import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.merinthium.Main;
import com.merinthium.handlers.Game;
import com.merinthium.listeners.MGListener;
import com.merinthium.utils.InventoryUtilities;
import com.merinthium.utils.LocationUtilities;

public class PlayerJoin extends MGListener
{

	public PlayerJoin(Main pl) 
	{
		super(pl);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		Player player = event.getPlayer();
		Game.setCanStart(Bukkit.getOnlinePlayers().size() >=8);
		LocationUtilities.teleportToSpawn(player);
		InventoryUtilities.clearInventory(player);
		
		ItemStack is = new ItemStack(Material.WOOL);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(ChatColor.GREEN + "Kits!");
		is.setItemMeta(im);
		player.getInventory().addItem(is);
		player.updateInventory();
	}

}
