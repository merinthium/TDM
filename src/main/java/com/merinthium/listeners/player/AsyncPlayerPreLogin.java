package com.merinthium.listeners.player;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;

import com.merinthium.Main;
import com.merinthium.handlers.Game;
import com.merinthium.listeners.MGListener;

public class AsyncPlayerPreLogin extends MGListener
{
	
	public AsyncPlayerPreLogin(Main pl)
	{
		super(pl);
	}
	
	@EventHandler
	
	public void playerPreLogin(AsyncPlayerPreLoginEvent event)
	{
		if(Game.hasStarted())
			event.disallow(Result.KICK_OTHER, ChatColor.RED + "The Game has Already started");
	}
}
