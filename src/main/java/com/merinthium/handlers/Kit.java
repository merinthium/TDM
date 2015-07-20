package com.merinthium.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.Permission;

import com.merinthium.utils.ChatUtilities;
import com.merinthium.utils.InventoryUtilities;

public class Kit 
{
	private static List<Kit> allKits = new ArrayList<Kit>();
	private static HashMap<String, Kit> playerKits = new HashMap<String,Kit>();

	
	private List<ItemStack> items = new ArrayList<ItemStack>();
	

	private String name;
	@SuppressWarnings("unused")
	private String permission;
	
	private int displayItem;
	
	
	@SuppressWarnings("deprecation")
	public Kit(String name, List<String> items, int displayItem) 
	{
		this.name = name;
		this.displayItem = displayItem;
		this.permission = "TDM.kit" + name;
		
		for(String s : items)
		{
			int id = 0, ammount = 1;
			if(s.contains(":"))
			{
				String[] splitItem = s.split(":");
				id = Integer.valueOf(splitItem[0].trim());
				ammount = Integer.valueOf(splitItem[1].trim());	
			}
			else
			id = Integer.valueOf(s.trim());
			this.items.add(new ItemStack(id,ammount));
		
		}
		allKits.add(this);
	}
	public static boolean isKit(String name)
	{
		for(Kit k : allKits)
			if(name.equalsIgnoreCase(k.getName()))
		return true;			
	return false;
	}
	
	public static Kit getKit(String name)
	{
		for(Kit k :allKits)
			if(k.getName().equalsIgnoreCase(name))
				return k;
			return null;
	}
	
	public static void setKit(Player player, Kit kit)
	{
		if(!player.hasPermission(kit.getPermissionNode()))
		{
			ChatUtilities.sendMessage(player, "You don't have the permisison to use this kit!");
		return;
		}
		playerKits.put(player.getName(), kit);
	}
	
	public static Kit getKit(Player player)
	{
		return playerKits.get(player.getName()) == null ? allKits.get(0) : playerKits.get(player.getName());
	}
	
	public void setKit(Player player)
	{
		setKit(player, this);
	}
	
	public String getName()
	{
		return name;
	}
	
	public static List<Kit> getAllKits()
	{
		return allKits;
	}
	
	@SuppressWarnings("deprecation")
	public ItemStack getDisplayItem()
	{
		ItemStack is = new ItemStack(displayItem);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(name);
		
		is.setItemMeta(im);
		return is;
	}
	
	public Permission getPermissionNode()
	{
		return new Permission("permission");
	}
	
	public void giveKit(Player player)
	{
		InventoryUtilities.clearInventory(player);
		for(ItemStack is : items)
		{
			player.getInventory().addItem(is);
		}
	}
}
