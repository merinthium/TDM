package com.merinthium.listeners.player;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;

import com.merinthium.Main;
import com.merinthium.handlers.Team;
import com.merinthium.listeners.MGListener;

public class PlayerDeath extends MGListener {

	public PlayerDeath(Main pl) {
		super(pl);
	}
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event)
	{
		Player player = event.getEntity();
		Team.getTeam(player).remove(player);
		
		player.kickPlayer(ChatColor.RED + "You Died");
	}

}
