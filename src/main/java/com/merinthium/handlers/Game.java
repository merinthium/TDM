package com.merinthium.handlers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.merinthium.utils.LocationUtilities;

public class Game 
{
	private static boolean canStart = false;
	private static boolean hasStarted = false;
	
	private static String[] teams = new String[] { "Red", "Blue"};
	
	public static void start()
	{
		hasStarted = true;
		new Team(teams);
		
		
		int i = 1;
		for(Player player : Bukkit.getOnlinePlayers())
		{
			if(i > Team.getAllTeams().size())
				i = 0;
			Team.getAllTeams().get(i).add(player);
			LocationUtilities.teleportToGame(player, Team.getAllTeams().get(i));
			Kit.getKit(player).giveKit(player);
			i++;
		}
	}
	
	public static void stop(Team team)
	{
		hasStarted = false;
	}
	
	public static boolean canStart() 
	{
		return canStart;
	}
	
	public static boolean hasStarted()
	{
		return hasStarted;
	}
	
	public static void setCanStart(boolean b)
	{
		canStart = b;
	}

}
