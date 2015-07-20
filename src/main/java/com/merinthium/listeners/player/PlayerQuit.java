package com.merinthium.listeners.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

import com.merinthium.GameState;
import com.merinthium.Main;
import com.merinthium.handlers.Game;
import com.merinthium.handlers.Team;
import com.merinthium.listeners.MGListener;

public class PlayerQuit extends MGListener
{
	public PlayerQuit(Main pl)
	{
		super(pl);
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		if(GameState.isState(GameState.IN_LOBBY))
			Game.setCanStart(Bukkit.getOnlinePlayers().size() - 1 >= 8);
		
		Player player = event.getPlayer();
		if(Game.hasStarted())
			Team.getTeam(player).remove(player);
	}

}
