package com.merinthium.utils;

import org.bukkit.entity.Player;


public class InventoryUtilities {

	@SuppressWarnings("deprecation")
	public static void clearInventory(Player player)
	{
		player.getInventory().clear();
		player.getInventory().setArmorContents(null);
		player.updateInventory();
	}
}
