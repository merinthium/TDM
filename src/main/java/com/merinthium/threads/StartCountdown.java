package com.merinthium.threads;

import org.bukkit.scheduler.BukkitRunnable;

import com.merinthium.Main;
import com.merinthium.handlers.Game;
import com.merinthium.utils.ChatUtilities;

public class StartCountdown extends BukkitRunnable 
{
	Main plugin;
	
	public StartCountdown(Main pl)
	{
		plugin = pl;
	}
	
	public static int timeUntilStart;
	
	public void run()
	{
		if(timeUntilStart == 0)
		{
			if(!Game.canStart())
			{
				plugin.restartCountdown();
				ChatUtilities.broadcast("Cannot Start Game, Restarting Countdown");
				return;
			}
			Game.start();
		}			
		if(timeUntilStart % 10==0 || timeUntilStart < 10)
		{
			ChatUtilities.broadcast(String.valueOf(timeUntilStart)+ " Seconds until the game starts!");
		}
		timeUntilStart -= 1;
	}
}
				




